package happy.schoolcarback.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.Suggest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.schoolcarback.vo.SuggestVo;
import org.apache.ibatis.annotations.Mapper;
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
public interface SuggestMapper extends BaseMapper<Suggest> {
    /**
     * @author 木月丶
     * @description 分页获取全部建议信息
     * @return List<SuggestVo>
     */
    @Select("SELECT suggest_id,user_nick_name,UNIX_TIMESTAMP(suggest_time)*1000 suggest_time,suggest_content " +
            "FROM `suggest` s,`user` u " +
            "WHERE s.`user_id` = u.`user_id` order by suggest_time desc")
    IPage<SuggestVo> getSuggestVoIPage(Page<SuggestVo> page);


    @Select("SELECT suggest_id,user_nick_name,UNIX_TIMESTAMP(suggest_time)*1000 suggest_time,suggest_content " +
            "FROM `suggest` s,`user` u " +
            "WHERE s.`user_id` = u.`user_id` order by suggest_time desc")
    List<SuggestVo> getSuggestVoList();

}
