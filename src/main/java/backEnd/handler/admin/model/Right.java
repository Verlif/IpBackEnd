package backEnd.handler.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("t_right")
public class Right extends model.JSONBuilder {

    @TableField("right_id")
    private String rightId;     // 权限id
    @TableField("right_num")
    private String rightNum;    // 权限编号
    @TableField("right_desc")
    private String rightDesc;   // 权限描述
    @TableField("right_url")
    private String rightUrl;    // 权限详细
    @TableField("create_time")
    private Timestamp createTime;    // 权限创建时间

}