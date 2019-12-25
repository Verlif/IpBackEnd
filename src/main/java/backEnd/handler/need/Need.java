package backEnd.handler.need;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("t_need")
public class Need extends model.JSONBuilder {

    @TableField("need_id")
    private String needId;
    @TableField("user_id")
    private String userId;
    @TableField("need_name")
    private String needName;
    @TableField("need_keywords")
    private String needKeywords;
    @TableField("need_type")
    private Object needType;
    @TableField("need_area")
    private Object needArea;                    // 需求领域
    @TableField("need_price")
    private String needPrice;                   // 需求的最高价格,当为-1时表示面议
    @TableField("create_time")
    private Timestamp createTime;

    /**
     * 请通过此方法添加关键词
     * @param kw    关键词
     */
    public void addKeyword(String kw) {
        if (needKeywords != null && !needKeywords.equals("")) {
            needKeywords += "," + kw;
        } else needKeywords = kw;
    }

    public void addType(String type) {
        if (needType != null && !needType.equals("")) {
            needType += "," + type;
        } else needType = type;
    }

}
