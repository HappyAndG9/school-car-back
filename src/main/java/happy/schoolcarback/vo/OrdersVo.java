package happy.schoolcarback.vo;

import happy.schoolcarback.entity.Orders;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author 木月丶
 * @Description
 */
@Data
public class OrdersVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单号")
    private Long orderId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户昵称")
    private String userNickName;

    @ApiModelProperty("所用充电桩")
    private String serialNumber;

    @ApiModelProperty("订单号")
    private String orderNumber;

    @ApiModelProperty("开始时间的时间戳")
    private Long startTime;

    @ApiModelProperty("结束时间的时间戳")
    private Long endTime;

    @ApiModelProperty("服务时长")
    private String duration;

    @ApiModelProperty("费用")
    private BigDecimal amount;

    @ApiModelProperty("订单评论")
    private String comment;

    @ApiModelProperty("订单状态")
    private Long state;

    @ApiModelProperty("逻辑删除字段（默认不被删除）")
    private Integer isDelete;


}

