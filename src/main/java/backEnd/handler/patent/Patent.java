package backEnd.handler.patent;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("t_patent")
public class Patent extends model.JSONBuilder {

    @TableField("patent_id")
    private String patentId;            // 专利id
    @TableField("patent_code")
    private String patentCode;          // 专利编号
    @TableField("patent_name")
    private String patentName;          // 专利名称
    @TableField("user_id")
    private String userId;              // 用户id
    @TableField("patent_details")
    private String patentDetails;       // 专利说明
    @TableField("patent_area")
    private Object patentArea;          // 专利领域
    @TableField("patent_keyword")
    private String patentKeyword;       // 专利关键词集
    @TableField("patent_deal_types")
    private Object patentDealTypes;     // 专利出售方式
    @TableField("patent_price")
    private BigDecimal patentPrice;     // 专利预售价格
    @TableField("is_sale")
    private boolean isSale;             // 专利是否已卖出
    @TableField("create_time")
    private Timestamp createTime;        // 专利创建时间
    @TableField("update_time")
    private Timestamp updateTime;        // 专利信息更新时间

    public Patent(JSONObject json) {
        super(json);
    }

    public Object getPatentArea() {
        return patentArea;
    }

    public void setPatentArea(String patentArea) {
        this.patentArea = patentArea;
    }

    public void setPatentArea(Object patentArea) {
        this.patentArea = patentArea;
    }

    /**
     * 请通过此方法添加关键词
     * @param kw    关键词
     */
    public void addPatentKeyword(String kw) {
        if (patentKeyword != null) {
            patentKeyword += "," + kw;
        } else patentKeyword = kw;
    }

    /**
     * 获取关键词数组
     * @return  关键词数组
     */
    public String[] getKeywordList() {
        return patentKeyword.split(",");
    }

    public Object getPatentDealTypes() {
        return patentDealTypes;
    }

    /**
     * 添加交易方式，请使用内置参数
     * @param dealType  交易方式
     */
    public void addDealType(String dealType) {
        if (patentDealTypes != null) {
            patentDealTypes += "," + dealType;
        } else patentDealTypes = dealType;
    }

}