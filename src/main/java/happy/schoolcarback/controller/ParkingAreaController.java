package happy.schoolcarback.controller;

import happy.schoolcarback.entity.ParkingArea;
import happy.schoolcarback.mapper.ParkingAreaMapper;
import happy.schoolcarback.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 木月丶
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/parkingArea")
@PreAuthorize("hasAuthority('LJY')")
public class ParkingAreaController {
    @Resource
    ParkingAreaMapper mapper;

    @PostMapping("/addArea")
    @ApiOperation("添加一片停车区域")
    public Result addArea(String description , String pointsJson, Integer ratedQuantity){

        ParkingArea parkingArea = new ParkingArea(description, pointsJson,ratedQuantity);

        int insert = mapper.insert(parkingArea);

        return insert != 0 ? Result.success("添加成功") : Result.error("添加出错");

    }
}
