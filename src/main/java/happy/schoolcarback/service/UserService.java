package happy.schoolcarback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
public interface UserService extends IService<User> {
    //分页获取全部用户信息
    Page<User> getUsers(String type, String key, Integer pageNum, Integer pageSize);

}
