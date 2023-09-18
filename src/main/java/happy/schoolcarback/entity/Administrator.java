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
 * @author 木月丶
 * @since 2023-02-05
 */
@Getter
@Setter
@TableName("administrator")
@ApiModel(value = "Administrator对象", description = "管理员")
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("管理员id")
    @TableId(value = "administrator_id", type = IdType.AUTO)
    private Long administratorId;

    @ApiModelProperty("管理员账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("管理员密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("管理员昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("角色权限")
    @TableField("role")
    private String role;
}
