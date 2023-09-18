package happy.schoolcarback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.Orders;
import happy.schoolcarback.mapper.OrdersMapper;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.utils.SearchUtils;
import happy.schoolcarback.vo.OrdersVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 木月丶
 * @since 2023-02-05
 */
@RestController
@RequestMapping("/order")
//@PreAuthorize("hasAuthority('admin')")
@RolesAllowed({"admin","LJY"})
public class OrderController {


    @Resource
    OrdersMapper mapper;



    @GetMapping("/list")
    @ApiOperation("分页获取全部订单信息")
    public Result geiList(@RequestParam(required = false) String type,
                          @RequestParam(required = false) String key,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize) throws IllegalAccessException {


        Page<OrdersVo> page = new Page<>(pageNum, pageSize);
        if (key == null || key == ""){
            return Result.successWithData(mapper.getOrderVoIPage(page));
        }else {
            List<OrdersVo> orderVoList = mapper.getOrderVoList();
            orderVoList = new SearchUtils<OrdersVo>().fuzzyQuery(orderVoList, type, key);
            page.setRecords(orderVoList);
            page.setTotal(orderVoList.size());
            return Result.successWithData(page);
        }
    }

    @GetMapping("/total")
    @ApiOperation("今日使用量")
    public Result getTodayOrdersTotal(){

        return Result.successWithData(mapper.getTodayOrdersTotal());

    }


    @GetMapping("thePastSevenDays")
    @ApiOperation("过去七天")
    public Result thePastSevenDays(){

        return Result.successWithData(mapper.getThePastSevenDaysOrdersTotal());

    }

    @GetMapping("/reports")
    @ApiOperation("周报数据")
    public Result weekReports(){
        HashMap<String, Object> map = new HashMap<>();

        //获取上上周日期和周报数据
        List<Map<String, Object>> lastLastWeekReports = mapper.getLastLastWeekReports();
        map.put("lastLastWeekData", lastLastWeekReports);


        //获取上周日期和周报数据
        List<Map<String, Object>> lastWeekReports = mapper.getLastWeekReports();
        map.put("lastWeekData", lastWeekReports);



        return Result.successWithMap(map);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除用户订单")
    public Result deleteUserOrder(String orderNumber){
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("order_number",orderNumber);
        int delete = mapper.delete(wrapper);
        return delete != 0 ? Result.success() : Result.error();
    }



}
