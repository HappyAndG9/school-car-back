package happy.schoolcarback.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.ChargingPile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.schoolcarback.vo.ChargingPileVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Mapper
public interface ChargingPileMapper extends BaseMapper<ChargingPile> {
    /**
     * @author 木月丶
     * @description 分页获取全部充电桩信息
     * @return List<ChargingPileVo>
     */
    @Select("SELECT charging_pile_id,serial_number,UNIX_TIMESTAMP(build_time)*1000 build_time,ca.description,current_state,service_state " +
            "FROM `charging_pile` cp " +
            "LEFT JOIN `charging_area` ca " +
            "ON cp.`location_id` = ca.area_id " +
            "order by cp.location_id asc")
    IPage<ChargingPileVo> getChargingPilesVoIPage(Page<ChargingPileVo> page);

    @Select("SELECT charging_pile_id,serial_number,UNIX_TIMESTAMP(build_time)*1000 build_time,ca.description,current_state,service_state " +
            "FROM `charging_pile` cp " +
            "LEFT JOIN `charging_area` ca " +
            "ON cp.`location_id` = ca.area_id " +
            "order by cp.location_id asc")
    List<ChargingPileVo> getChargingPilesVoList();

    @Select("select count(*) from charging_pile")
    int getTotal();
}
