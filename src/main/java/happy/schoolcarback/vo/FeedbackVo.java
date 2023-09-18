package happy.schoolcarback.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 木月丶
 * @Description
 */
@Data
public class FeedbackVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("反馈id")
    private Long feedbackId;

    @ApiModelProperty("用户昵称")
    private String userNickName;

    @ApiModelProperty("联系电话")
    private String phoneNumber;

    @ApiModelProperty("反馈时间的时间戳")
    private Long feedbackTime;

    @ApiModelProperty("反馈内容")
    private String feedbackContent;

    @ApiModelProperty("反馈对象")
    private String serialNumber;

    @ApiModelProperty("位置描述")
    private String description;

    @ApiModelProperty("处理状态(默认未处理)")
    private Long state;

    @ApiModelProperty("管理员的回复")
    private String reply;
}
