package happy.schoolcarback.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import happy.schoolcarback.entity.Administrator;
import happy.schoolcarback.mapper.AdministratorMapper;
import happy.schoolcarback.service.AdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {
    @Resource
    AdministratorMapper mapper;


    @Override
    public Administrator showInfo() {
        //通过上下文用户获取信息
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        QueryWrapper<Administrator> wrapper = new QueryWrapper<>();
        wrapper.eq("username", admin.getUsername());
        Administrator administrator = mapper.selectOne(wrapper);
        return administrator;
    }

    @Override
    public boolean updateInfo(String nickname) {
        //通过上下文用户获取信息
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        Administrator administrator = mapper.selectOne(queryWrapper);

        UpdateWrapper<Administrator> updateWrapper = new UpdateWrapper<>();
        administrator.setNickName(nickname);
        int update = mapper.updateById(administrator);
        return update != 0 ? true : false;
    }


}
