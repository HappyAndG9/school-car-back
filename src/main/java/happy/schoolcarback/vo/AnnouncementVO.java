package happy.schoolcarback.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 木月丶
 * @Description 分页获取公告信息后响应前端数据
 */
@Data
public class AnnouncementVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公告id")
    private Long announcementId;

    @ApiModelProperty("发布者的昵称")
    private String username;

    @ApiModelProperty("公告标题")
    private String announcementTitle;

    @ApiModelProperty("公告分类")
    private String announcementClassification;

    @ApiModelProperty("发布时间的时间戳")
    private Long publishTime;

    @ApiModelProperty("公告内容")
    private String publishContent;
}
