package backEnd.handler.reference;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_reference")
public class Reference extends model.JSONBuilder {

    @TableField("ref_id")
    private String refId;       // 参数id
    @TableField("ref_type")
    private String refType;     // 参数附属类型
    @TableField("ref_name")
    private String refName;     // 参数名(内容)
    @TableField("ref_num")
    private String refNum;      // 参数编号
    @TableField("up_id")
    private String upId;        // 父级参数id

}