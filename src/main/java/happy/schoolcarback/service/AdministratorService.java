package happy.schoolcarback.service;

import happy.schoolcarback.entity.Administrator;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
public interface AdministratorService  extends IService<Administrator>  {

    //查看个人信息
    Administrator showInfo();

    //修改个人信息
    boolean updateInfo(String nickname);

}
