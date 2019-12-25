package backEnd.handler.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_group")
public class Group extends model.JSONBuilder {

    public static final String ID_DEFAULT = "default";

    @TableField("group_id")
    private String groupId;     // 分组id
    @TableField("right_id")
    private String rightId;     // 权限id
    @TableField("group_name")
    private String groupName;   // 分组名称
    private String notes;       // 备注

}
