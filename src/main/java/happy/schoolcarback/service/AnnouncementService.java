package happy.schoolcarback.service;

import happy.schoolcarback.entity.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
public interface AnnouncementService extends IService<Announcement> {

    //发布公告
    boolean publishAnnouncement(String title,String classification,String content);

    //删除公告
    boolean deleteAnnouncement(String id);
}
