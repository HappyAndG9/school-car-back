package happy.schoolcarback;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import happy.schoolcarback.entity.Orders;
import happy.schoolcarback.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
class SchoolCarBackApplicationTests {

    @Resource
    OrdersMapper mapper;

    @Test
    void test1(){
        for (Orders orders : mapper.selectList(new QueryWrapper<Orders>())) {
            LocalDateTime time = LocalDateTime.of(2023, 5, 15, 0, 0, 0);
            if (orders.getStartTime().isBefore(time)){
                LocalDateTime newStartTime = orders.getStartTime().plusDays(14);
                LocalDateTime newEndTime = orders.getEndTime().plusDays(14);
                orders.setStartTime(newStartTime);
                orders.setEndTime(newEndTime);
                mapper.updateById(orders);
            }
        }
    }

//    @Test
//    void test2(){
//        LocalDateTime time1 = LocalDateTime.of(2020, 11, 11, 13, 14, 15);
//        LocalDateTime time2 = LocalDateTime.of(2021, 11, 11, 13, 14, 15);
//        System.out.println(time1.isBefore(time2));
//    }

}
