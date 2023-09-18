package happy.schoolcarback.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 木月丶
 * @Description 分页获取充电桩信息后响应前端数据
 */
@Data
public class ChargingPileVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("充电桩id")
    private Long chargingPileId;

    @ApiModelProperty("充电桩序列号")
    private String serialNumber;

    @ApiModelProperty("建设时间的时间戳")
    private Long buildTime;

    @ApiModelProperty("位置描述")
    private String description;

    @ApiModelProperty("当前使用状态,默认为0，0为无人使用，1为正在使用")
    private Integer currentState;

    @ApiModelProperty("设备服务状态,默认为1，0为正在维修，1为可正常使用")
    private Integer serviceState;




}
