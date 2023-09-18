package happy.schoolcarback.controller;

import happy.schoolcarback.result.Result;
import happy.schoolcarback.service.RecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

/**
 * @author 木月丶
 * @since 2023-02-05
 */
@RestController
@RequestMapping("/record")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class RecordController {
    @Resource
    RecordService service;

    @GetMapping("/list")
    @ApiOperation("分页获取全部进出校园记录信息")
    public Result geiList(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.successWithData(service.getRecords(pageNum, pageSize));
    }
}
