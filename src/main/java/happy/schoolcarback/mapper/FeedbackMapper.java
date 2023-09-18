package happy.schoolcarback.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.schoolcarback.vo.FeedbackVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 木月丶
 * @since 2023-03-20
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
    /**
     * @author 木月丶
     * @description 分页获取全部反馈信息
     * @return List<FeedbackVo>
     */
    @Select("SELECT feedback_id,u.user_nick_name,u.phone_number," +
                    "UNIX_TIMESTAMP(feedback_time)*1000 feedback_time,feedback_content,f.serial_number,ca.description,state,reply " +
            "FROM `feedback` f,`user` u,`charging_area` ca,`charging_pile` cp " +
            "WHERE f.`user_id` = u.`user_id` " +
            "AND f.`serial_number` = cp.`serial_number` " +
            "AND cp.`location_id` = ca.`area_id` " +
            "order by feedback_time DESC")
    IPage<FeedbackVo> getFeedbackVoIPage(Page<FeedbackVo> page);

    @Select("SELECT feedback_id,u.user_nick_name,u.phone_number," +
            "UNIX_TIMESTAMP(feedback_time)*1000 feedback_time,feedback_content,f.serial_number,ca.description,state,reply " +
            "FROM `feedback` f,`user` u,`charging_area` ca,`charging_pile` cp " +
            "WHERE f.`user_id` = u.`user_id` " +
            "AND f.`serial_number` = cp.`serial_number` " +
            "AND cp.`location_id` = ca.`area_id` " +
            "order by feedback_time DESC")
    List<FeedbackVo> getFeedbackVoList();

}
