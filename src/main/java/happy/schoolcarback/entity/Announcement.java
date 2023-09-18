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
@TableName("announcement")
@ApiModel(value = "Announcement对象", description = "公告")
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公告id")
    @TableId(value = "announcement_id", type = IdType.AUTO)
    private Long announcementId;

    @ApiModelProperty("发布者")
    @TableField("publisher_id")
    private String publisherId;

    @ApiModelProperty("公告标题")
    @TableField("announcement_title")
    private String announcementTitle;

    @ApiModelProperty("公告分类")
    @TableField("announcement_classification")
    private String announcementClassification;

    @ApiModelProperty("发布时间")
    @TableField(value = "publish_time", fill = FieldFill.INSERT)
    private LocalDateTime publishTime;

    @ApiModelProperty("公告内容")
    @TableField("publish_content")
    private String publishContent;


    public Announcement(String publisherId, String announcementTitle, String announcementClassification, String publishContent) {
        this.publisherId = publisherId;
        this.announcementTitle = announcementTitle;
        this.announcementClassification = announcementClassification;
        this.publishContent = publishContent;
    }

}
