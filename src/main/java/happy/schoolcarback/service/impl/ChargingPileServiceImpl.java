package happy.schoolcarback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import happy.schoolcarback.entity.ChargingPile;
import happy.schoolcarback.mapper.ChargingPileMapper;
import happy.schoolcarback.service.ChargingPileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import happy.schoolcarback.vo.ChargingPileVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Service
public class ChargingPileServiceImpl extends ServiceImpl<ChargingPileMapper, ChargingPile> implements ChargingPileService {

    @Resource
    ChargingPileMapper mapper;

    @Override
    public boolean addChargingPile(String serialNumber,Long locationId) {
        int insert = mapper.insert(new ChargingPile(serialNumber,locationId));
        return insert != 0;
    }

    @Override
    public boolean updateChargingPileState(String serialNumber,Integer serviceState) {
        QueryWrapper<ChargingPile> wrapper = new QueryWrapper<>();
        wrapper.eq("serial_number", serialNumber);
        ChargingPile chargingPile = mapper.selectOne(wrapper);
        chargingPile.setServiceState(serviceState);
        return mapper.updateById(chargingPile) != 0;
    }

    @Override
    public boolean deleteChargingPile(Integer id) {
        int i = mapper.deleteById(id);
        return i != 0;
    }

}
