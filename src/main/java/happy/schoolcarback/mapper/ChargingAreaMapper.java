package happy.schoolcarback.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.ChargingArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.schoolcarback.vo.ChargingPileVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 木月丶
 * @since 2023-03-25
 */
@Mapper
public interface ChargingAreaMapper extends BaseMapper<ChargingArea> {
    @Select("SELECT charging_pile_id,serial_number,UNIX_TIMESTAMP(build_time)*1000 build_time,ca.description,current_state,service_state " +
            "FROM charging_pile cp LEFT JOIN charging_area ca ON cp.location_id = ca.area_id " +
            "WHERE cp.location_id = #{locationId} " +
            "ORDER BY cp.`charging_pile_id` ASC")
    List<ChargingPileVo> getList(Page<ChargingPileVo> page , @Param("locationId") Long locationId);

    @Select("SELECT description FROM charging_area")
    List<String> getChargingAreaDescription();
}
