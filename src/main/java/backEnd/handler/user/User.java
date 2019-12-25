package backEnd.handler.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

@Data
@NoArgsConstructor
@TableName("t_user")
public class User extends model.JSONBuilder {

    private static final String BELONG_PUBLIC = "public";

    public static final int AC_STATUS_NO = 0;
    public static final int AC_STATUS_REVIEW = 1;
    public static final int AC_STATUS_OK = 2;
    public static final int AC_STATUS_FAIL = 3;

    public static final int STATUS_ONLINE = 0;              // 用户状态-在线
    public static final int STATUS_OFFLINE = 1;              // 用户状态-离线

    @TableField("user_id")
    private String userId;              // 用户id
    @TableField("group_id")
    private String groupId;             // 用户所在组id
    @TableField("user_name")
    private String userName;            // 用户昵称
    @TableField("user_account")
    private String userAccount;         // 用户手机号
    @TableField("user_addr")
    private Object userAddr;            // 用户所在省市
    @TableField("user_addr_detailed")
    private String userAddrDetailed;    // 用户所在详细地址
    @TableField("user_contact")
    private String userContact;         // 用户联系方式说明
    @TableField("user_area")
    private Object userArea;            // 用户所在领域
    @TableField("user_keyword")
    private String userKeyword;         // 用户相关的关键词集
    @TableField("user_grade")
    private int userGrade;              // 用户等级
    @TableField("user_wechat_account")
    private String userWechatAccount;   // 用户微信账号绑定信息
    @TableField("user_alipay_account")
    private String userAlipayAccount;   // 用户支付宝账号绑定信息
    @TableField("user_code")
    private String userCode;            // 用户邀请码
    @TableField("up_user_id")
    private String upUserId;            // 邀请者的用户
    @TableField("user_info")
    private String userInfo;            // 用户介绍
    @TableField("user_status")
    private int status;                 // 用户状态，如在线、离线等
    @TableField("is_del")
    private int isDel;                  // 用户是否已注销
    @TableField("create_time")
    private Timestamp createTime;       // 用户创建时间
    @TableField("user_cost")
    private BigDecimal cost;            // 获取联系方式所需支付的金额
    @TableField("is_company")
    private int isCompany;          // 企业是否已认证
    @TableField("is_proxy")
    private int isProxy;            // 第三方代理是否已认证
    @TableField("is_specialist")
    private int isSpecialist;       // 个人专家身份是否已认证
    private String belong;              // 用户所属标识，用于标记用户注册时的渠道

    public User(JSONObject json) {
        super(json);
    }

    /**
     * 请通过此方法添加关键词
     * @param kw    关键词
     */
    public void addUserKeyword(String kw) {
        if (userKeyword != null || !userKeyword.equals("")) {
            userKeyword += "," + kw;
        } else userKeyword = kw;
    }

    /**
     * 获取关键词数组
     * @return  关键词数组
     */
    public String[] getKeywordList() {
        if (userKeyword == null)
            return null;
        else return userKeyword.split(",");
    }

    public void setKeywordList(String[] userKeywords) {
        this.userKeyword = Arrays.toString(userKeywords).replace("[", "").replace("]", "").replace(" ", "");
    }

    public boolean isDel() {
        return isDel != 0;
    }

    public void del(boolean isDel) {
        this.isDel = isDel ? 1 : 0;
    }

}
