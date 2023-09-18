package happy.schoolcarback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.mapper.AnnouncementMapper;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.service.AnnouncementService;
import happy.schoolcarback.utils.SearchUtils;
import happy.schoolcarback.vo.AnnouncementVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author 木月丶
 * @since 2023-02-05
 */
@RestController
@RequestMapping("/announcement")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class AnnouncementController {

    @Resource
    AnnouncementService service;

    @Resource
    AnnouncementMapper mapper;

//    @GetMapping("/list")
//    @ApiOperation("分页获取全部公告")
//    public Result getAnnouncement(@RequestParam(required = false) String type,
//                                  @RequestParam(required = false) String key,
//                                  @RequestParam(defaultValue = "1") Integer pageNum,
//                                  @RequestParam(defaultValue = "10") Integer pageSize) throws  IllegalAccessException {
//
//        Page<AnnouncementVO> page = new Page<>(pageNum, pageSize);
//
//        if (key == null || key == "") {
//            return Result.successWithData(mapper.getAnnouncementVoIPage(page));
//        } else {
//            List<AnnouncementVO> announcementVoList = mapper.getAnnouncementVoList();
//            announcementVoList = new SearchUtils<AnnouncementVO>().fuzzyQuery(announcementVoList, type, key);
//            page.setRecords(announcementVoList);
//            page.setTotal(announcementVoList.size());
//            return Result.successWithData(page);
//        }
//    }

    @GetMapping("/list")
    @ApiOperation("分页获取全部公告")
    public Result getAnnouncement(@RequestParam(required = false) String type,
                                  @RequestParam(required = false) String key,
                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "2") Integer pageSize) throws  IllegalAccessException {

        QueryWrapper<AnnouncementVO> wrapper = new QueryWrapper<>();

        wrapper.orderByDesc("publish_time");

        Page<AnnouncementVO> page = new Page<>(pageNum, pageSize);

        List<AnnouncementVO> announcementVo = mapper.getAnnouncementVo(page,wrapper);

        if (announcementVo == null || announcementVo.size() == 0){
            return Result.error("暂无公告");
        }

        if (key != null){
            //修改响应的List
            announcementVo = new SearchUtils<AnnouncementVO>().fuzzyQuery(announcementVo, type, key);
        }

        //少了这一句话直接返回数据的话就没有分页的那些数据了（如total、size）
        page.setRecords(announcementVo);
        page.setTotal(announcementVo.size());

        return Result.successWithData(page);

    }



    @PostMapping("/publish")
    @ApiOperation("发布公告")
    public Result publishAnnouncement(String title,String classification,String content){
        boolean flag = service.publishAnnouncement(title,classification,content);
        return flag ? Result.success() : Result.error();
    }


    @DeleteMapping("/delete")
    @ApiOperation("根据公告id删除公告")
    public Result deleteAnnouncement(String id){
        return service.deleteAnnouncement(id) ? Result.success() : Result.error();
    }

}
