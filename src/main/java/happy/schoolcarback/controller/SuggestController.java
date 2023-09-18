package happy.schoolcarback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.mapper.SuggestMapper;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.utils.SearchUtils;
import happy.schoolcarback.vo.SuggestVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author 木月丶
 * @since 2023-02-05
 */
@RestController
@RequestMapping("/suggest")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class SuggestController {
    @Resource
    SuggestMapper mapper;

    @GetMapping("/list")
    @ApiOperation("分页获取全部建议信息")
    public Result geiList(@RequestParam(required = false) String type,
                          @RequestParam(required = false) String key,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize) throws IllegalAccessException {
        Page<SuggestVo> page = new Page<>(pageNum,pageSize);
        if (key == null || key == ""){
            return Result.successWithData(mapper.getSuggestVoIPage(page));
        }else {
            List<SuggestVo> suggestVoList = mapper.getSuggestVoList();
            suggestVoList = new SearchUtils<SuggestVo>().fuzzyQuery(suggestVoList, type, key);
            page.setRecords(suggestVoList);
            page.setTotal(suggestVoList.size());
            return Result.successWithData(page);
        }
    }
}

