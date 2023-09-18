package happy.schoolcarback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.mapper.ChargingPileMapper;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.service.ChargingPileService;
import happy.schoolcarback.utils.SearchUtils;
import happy.schoolcarback.vo.ChargingPileVo;
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
@RequestMapping("/chargingPile")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class ChargingPileController {

    @Resource
    ChargingPileService service;

    @Resource
    ChargingPileMapper mapper;

    @GetMapping("/list")
    @ApiOperation("分页获取全部的充电桩信息")
    public Result getChargingPileList(@RequestParam(required = false) String type,
                                      @RequestParam(required = false) String key,
                                      @RequestParam(required = false) String areaId,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize) throws IllegalAccessException {
        Page<ChargingPileVo> page = new Page<>(pageNum, pageSize);
        if (key == null || key == "") {
            return Result.successWithData(mapper.getChargingPilesVoIPage(page));
        } else {
            List<ChargingPileVo> chargingPilesVoList = mapper.getChargingPilesVoList();
            chargingPilesVoList = new SearchUtils<ChargingPileVo>().fuzzyQuery(chargingPilesVoList, type, key);
            page.setRecords(chargingPilesVoList);
            page.setTotal(chargingPilesVoList.size());
            return Result.successWithData(page);
        }
    }


    @PostMapping("/add")
    @ApiOperation("新增充电桩")
    public Result addChargingPile(String serialNumber,Long locationId){
        return service.addChargingPile(serialNumber,locationId) ? Result.success() : Result.error();
    }


    @PutMapping("/update")
    @ApiOperation("修改充电桩服务状态，0为待维修，1为正常服务")
    public Result updateChargingPileServiceState(String serialNumber,Integer serviceState){
        return service.updateChargingPileState(serialNumber,serviceState) ? Result.success() : Result.error();
    }

    @DeleteMapping("/delete")
    @ApiOperation("移除充电桩")
    public Result deleteChargingPile(Integer id){
        return service.deleteChargingPile(id) ? Result.success() : Result.error();
    }

    @GetMapping("/total")
    @ApiOperation("充电桩数量")
    public Result total(){
        return Result.successWithData(mapper.getTotal());
    }
}
