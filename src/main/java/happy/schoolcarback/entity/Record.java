package happy.schoolcarback.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Getter
@Setter
@TableName("record")
@ApiModel(value = "Record对象", description = "进出记录")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("记录id")
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    @ApiModelProperty("车牌号")
    @TableField("license_number")
    private String licenseNumber;

    @ApiModelProperty("进出时间")
    @TableField(value = "transit_time", fill = FieldFill.INSERT)
    private LocalDateTime transitTime;

    @ApiModelProperty("进出位置")
    @TableField("transit_location")
    private Integer transitLocation;

    @ApiModelProperty("进出方向(1为进学校，2为出学校)")
    @TableField("direction")
    private Integer direction;

}
