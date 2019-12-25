package backEnd.handler.collect;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("t_collect")
public class Collect extends model.JSONBuilder {

    public static final int TYPE_PATENT = 0;
    public static final int TYPE_USER = 1;

    @TableField("collect_id")
    private String collectId;
    @TableField("user_id")
    private String userId;
    @TableField("follow_id")
    private String followId;
    @TableField("collect_type")
    private int collectType;    //收藏类型，0-专利，1-用户
    @TableField("create_time")
    private Timestamp createTime;

}
