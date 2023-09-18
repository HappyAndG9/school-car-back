package happy.schoolcarback.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 木月丶
 * @Description
 */
@Data
public class SuggestVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("建议id")
    private Long suggestId;

    @ApiModelProperty("建议用户昵称")
    private String userNickName;

    @ApiModelProperty("建议时间的时间戳")
    private Long suggestTime;

    @ApiModelProperty("建议内容")
    private String suggestContent;
}
