package happy.schoolcarback.controller;

import happy.schoolcarback.entity.Administrator;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.service.AdministratorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

/**
 * @author 木月丶
 * @since 2023-02-05
 */
@RestController
@RequestMapping("/administrator")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class AdministratorController {

    @Resource
    AdministratorService service;


    @GetMapping("info")
    @ApiOperation("展示管理员个人信息")
    public Result showInfo(){
        Administrator administrator = service.showInfo();
        return Result.successWithData(administrator);
    }

    @PutMapping("/update")
    @ApiOperation("修改管理员昵称")
    public Result update(String nickname){
        return service.updateInfo(nickname) ? Result.success() : Result.error();
    }
}
