package happy.schoolcarback.service;

import happy.schoolcarback.entity.ChargingPile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
public interface ChargingPileService extends IService<ChargingPile> {

    //添加充电桩
    boolean addChargingPile(String serialNumber,Long locationId);

    //修改充电桩服务状态
    boolean updateChargingPileState(String serialNumber,Integer serviceState);

    //删除充电桩
    boolean deleteChargingPile(Integer id);
}
