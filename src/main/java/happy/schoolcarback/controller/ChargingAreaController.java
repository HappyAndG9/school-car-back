package happy.schoolcarback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.ChargingArea;
import happy.schoolcarback.mapper.ChargingAreaMapper;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.vo.ChargingPileVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/chargingPileArea")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class ChargingAreaController {

    @Resource
    ChargingAreaMapper mapper;

    @GetMapping("/list")
    @ApiOperation("获取指定区域的全部充电桩信息")
    public Result getChargingPilesInfo(@RequestParam(defaultValue = "1") Long locationId,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize){


        Page<ChargingPileVo> page = new Page<>(pageNum,pageSize);

        List<ChargingPileVo> piles = mapper.getList(page , locationId);

        if (piles == null || piles.size() == 0){
            return Result.error("该区域暂无充电桩");
        }

        page.setRecords(piles);

        return Result.successWithData(page);
    }

    @PostMapping("/addArea")
    @ApiOperation("添加一片充电区域")
    @PreAuthorize("hasAuthority('LJY')")
    public Result addArea(String description , String pointsJson ){
        ChargingArea chargingArea = new ChargingArea(description, pointsJson);
        int insert = mapper.insert(chargingArea);
        return insert != 0 ? Result.success("添加成功") : Result.error("添加出错");
    }


    @GetMapping("/getLocationDescription")
    @ApiOperation("获取充电区域位置描述")
    public Result getLocationDescription(){
        return Result.successWithData(mapper.getChargingAreaDescription());
    }

}
