package happy.schoolcarback.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import happy.schoolcarback.entity.Administrator;
import happy.schoolcarback.mapper.AdministratorMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 木月丶
 * @Description
 */
@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    AdministratorMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Administrator> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Administrator administrator = mapper.selectOne(wrapper);
        if (administrator == null) {
            throw new UsernameNotFoundException("user" + username + "is not found");
        }

        //获得角色权限列表
        //由于我不需要太多角色，所以就是直接在administrator表中加了role的字段，也就是一对一
        //正常情况下应该是一对多，也就是会查出一个list
        ArrayList<String> roleList = new ArrayList<>();
        roleList.add(administrator.getRole());
        //如果是一对多，上面两行代码换成List<String> roleList =administrator.getRolesList(wrapper1);（getRolesList和wrapper1要自己重写sql和查询条件）
        List<SimpleGrantedAuthority> authorities = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return new User(administrator.getUsername(), new BCryptPasswordEncoder().encode(administrator.getPassword()), authorities);
    }
}
