package happy.schoolcarback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import happy.schoolcarback.entity.Administrator;
import happy.schoolcarback.entity.Announcement;
import happy.schoolcarback.mapper.AdministratorMapper;
import happy.schoolcarback.mapper.AnnouncementMapper;
import happy.schoolcarback.service.AnnouncementService;
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
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Resource
    AnnouncementMapper announcementMapper;

    @Resource
    AdministratorMapper administratorMapper;


    @Override
    public boolean publishAnnouncement(String title,String classification,String content) {
        //通过上下文用户获取信息
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", admin.getUsername());
        Administrator administrator = administratorMapper.selectOne(queryWrapper);
        int insert = announcementMapper.insert(new Announcement(administrator.getAdministratorId().toString(),title,classification,content));
        return insert != 0;
    }

    @Override
    public boolean deleteAnnouncement(String id) {
        int i = announcementMapper.deleteById(id);
        return i != 0;
    }



}
