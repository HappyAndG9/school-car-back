package happy.schoolcarback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("user")
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty("微信用户的openId")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("用户昵称")
    @TableField("user_nick_name")
    private String userNickName;

    @ApiModelProperty("电话号码")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty("学号")
    @TableField("student_number")
    private String studentNumber;

    @ApiModelProperty("车牌号")
    @TableField("license_number")
    private String licenseNumber;

    @ApiModelProperty("充电订单总数量")
    @TableField("user_order_quantity")
    private Integer userOrderQuantity;


}
