package happy.schoolcarback.service.impl;

import happy.schoolcarback.entity.Orders;
import happy.schoolcarback.mapper.OrdersMapper;
import happy.schoolcarback.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
