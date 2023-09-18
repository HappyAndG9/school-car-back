package happy.schoolcarback.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.User;
import happy.schoolcarback.mapper.UserMapper;
import happy.schoolcarback.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public Page<User> getUsers(String type, String key, Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //需要封装的vo
        queryWrapper.select("user_id","name","student_number","user_nick_name","phone_number","license_number","user_order_quantity");
        //升序
        queryWrapper.orderByAsc("user_id");
        //模糊查询要求类型和关键字同时存在
        if (type != null && key != null){
            queryWrapper.like(type, key);
        }
        return userMapper.selectPage(page, queryWrapper);
    }
}
