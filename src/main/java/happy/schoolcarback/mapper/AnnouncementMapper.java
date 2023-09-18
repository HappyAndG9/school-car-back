package happy.schoolcarback.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.schoolcarback.vo.AnnouncementVO;
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
public interface AnnouncementMapper extends BaseMapper<Announcement> {
    /**
     * @author 木月丶
     * @description 分页获取全部公告
     * @return List<AnnouncementVO>
     */
    @Select("select announcement_id,username,UNIX_TIMESTAMP(publish_time)*1000 publish_time,announcement_title,announcement_classification,publish_content " +
            "from announcement left join administrator " +
            "on publisher_id = administrator_id order by publish_time desc")
    IPage<AnnouncementVO> getAnnouncementVoIPage(Page<AnnouncementVO> page);


    @Select("select announcement_id,username,UNIX_TIMESTAMP(publish_time)*1000 publish_time,announcement_title,announcement_classification,publish_content " +
            "from announcement left join administrator " +
            "on publisher_id = administrator_id order by publish_time desc")
    List<AnnouncementVO> getAnnouncementVoList();








    @Select("select announcement_id,username,UNIX_TIMESTAMP(publish_time)*1000 publish_time, " +
            "announcement_title,announcement_classification,publish_content " +
            "from announcement left join administrator " +
            "on publisher_id = administrator_id${ew.customSqlSegment}")
    List<AnnouncementVO> getAnnouncementVo(Page<AnnouncementVO> page , @Param(Constants.WRAPPER)QueryWrapper<AnnouncementVO> queryWrapper);

}
