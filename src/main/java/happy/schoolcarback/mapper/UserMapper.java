package happy.schoolcarback.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.schoolcarback.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * @author 木月丶
     * @description 分页获取全部用户信息
     * @return List<UserVo>
     */
    @Select("SELECT user_id,`name`,user_nick_name,phone_number,student_number,license_number,\n" +
            "(SELECT COUNT(*)FROM `orders` WHERE `orders`.`user_id` = `user`.`user_id`) user_order_quantity\n" +
            "FROM `user` order by user_id asc")
    List<UserVo> getUsersVoList();


    @Select("SELECT user_id,`name`,user_nick_name,phone_number,student_number,license_number,\n" +
            "(SELECT COUNT(*)FROM `orders` WHERE `orders`.`user_id` = `user`.`user_id`) user_order_quantity\n" +
            "FROM `user` order by user_id asc")
    IPage<UserVo> getUsersVoIPage(Page<UserVo> page);

    /**
     * @author 木月丶
     * @description 获取用户总数
     * @return int
     */
    @Select("select count(*) from user")
    int getTotal();

    @Select("select count(*) from orders where orders.user_id = #{userId}")
    int countOrder(@Param("userId") Long userId);
}
