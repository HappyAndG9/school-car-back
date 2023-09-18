package happy.schoolcarback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.mapper.UserMapper;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.utils.SearchUtils;
import happy.schoolcarback.vo.UserVo;
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
@RequestMapping("/user")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class UserController {
    @Resource
    UserMapper mapper;

    @GetMapping("/list")
    @ApiOperation("获取所有用户列表以及模糊查询用户")
    public Result searchUserInfo(@RequestParam(required = false) String type,
                                                            @RequestParam(required = false) String key,
                                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                                            @RequestParam(defaultValue = "10") Integer pageSize) throws IllegalAccessException {
        Page<UserVo> page = new Page<>(pageNum, pageSize);
        if (key == null || key == ""){
            return Result.successWithData(mapper.getUsersVoIPage(page));
        }else {
            List<UserVo> usersVoList = mapper.getUsersVoList();
            usersVoList = new SearchUtils<UserVo>().fuzzyQuery(usersVoList, type, key);
            page.setRecords(usersVoList);
            page.setTotal(usersVoList.size());
            return Result.successWithData(page);
        }

    }


    @GetMapping("/total")
    @ApiOperation("获取用户总数")
    public Result getTotal(){
       return Result.successWithData(mapper.getTotal());
    }
}