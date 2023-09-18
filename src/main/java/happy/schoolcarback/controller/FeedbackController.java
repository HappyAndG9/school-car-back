package happy.schoolcarback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.ChargingPile;
import happy.schoolcarback.entity.Feedback;
import happy.schoolcarback.mapper.ChargingPileMapper;
import happy.schoolcarback.mapper.FeedbackMapper;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.utils.SearchUtils;
import happy.schoolcarback.vo.FeedbackVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 木月丶
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/feedback")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class FeedbackController {

    @Resource
    FeedbackMapper mapper;

    @Resource
    ChargingPileMapper chargingPileMapper;

    @GetMapping("/list")
    @ApiOperation("分页获取全部反馈信息")
    public Result getList(@RequestParam(required = false) String type,
                          @RequestParam(required = false) String key,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize) throws IllegalAccessException {
        Page<FeedbackVo> page = new Page<>(pageNum,pageSize);
        if (key == null || key == ""){
            return Result.successWithData(mapper.getFeedbackVoIPage(page));
        }else {
            List<FeedbackVo> feedbackVoList = mapper.getFeedbackVoList();
            feedbackVoList = new SearchUtils<FeedbackVo>().fuzzyQuery(feedbackVoList, type, key);
            page.setRecords(feedbackVoList);
            page.setTotal(feedbackVoList.size());
            return Result.successWithData(page);
        }

    }

    @PutMapping("/reply")
    @ApiOperation("回复用户的反馈以及改变反馈状态")
    public Result replyTheFeedback(Integer feedbackId , String reply){
        Feedback feedback = mapper.selectById(feedbackId);
        feedback.setReply(reply);
        feedback.setState(1L);
        mapper.updateById(feedback);
        ChargingPile chargingPile = chargingPileMapper.selectOne(new QueryWrapper<ChargingPile>().eq("serial_number", feedback.getSerialNumber()));
        chargingPile.setServiceState(0);
        chargingPileMapper.updateById(chargingPile);
        return Result.success("操作成功！");
    }

}
