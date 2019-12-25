package backEnd.handler.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("t_order")
public class Order extends model.JSONBuilder {

    public static final int STATUS_UNFINISHED = 0;              // 待确定
    public static final int STATUS_UNPAID = 1;                  // 待付款
    public static final int STATUS_NEED_RECEIVE = 2;            // 待收货
    public static final int STATUS_NEED_COMMENT = 3;            // 待评价
    public static final int STATUS_FINISH = 4;                 // 已完成
    public static final int STATUS_CANCEL = 5;                 // 订单被取消

    //订单超时时间
    public static final long OVERTIME = 1000 * 60 * 60 * 24;

    //平台一站式服务
    public static final int TYPE_THE_WHOLE = 0;
    //第三方服务
    public static final int TYPE_ANOTHER = 1;

    @TableField("order_id")
    private String orderId;             // 订单id
    @TableField("order_num")
    private String orderNum;            // 订单号
    @TableField("user_id")
    private String userId;              // 订单用户id
    @TableField("patent_id")
    private String patentId;            // 专利id
    @TableField("patent_price")
    private BigDecimal patentPrice;     // 商议后的专利价格
    @TableField("order_payment")
    private BigDecimal orderPayment;    // 订单支付金额
    @TableField("order_type")
    private int orderType;           // 订单中的服务类型
    @TableField("order_status")
    private int orderStatus;            // 订单状态,0-待确认，1-待付款，2-待收货，3-待评价，4-已完成
    @TableField("order_deal_way")
    private String orderDealWay;           // 交易方式
    @TableField("order_confirm_time")
    private Timestamp orderConfirmTime;      // 订单确认时间
    @TableField("order_pay_time")
    private Timestamp orderPayTime;          // 订单支付时间
    @TableField("order_end_time")
    private Timestamp orderEndTime;          // 交易完成时间
    @TableField("order_close_time")
    private Timestamp orderCloseTime;        // 交易关闭时间
    @TableField("is_del")
    private int isDel;                  // 订单是否被删除，1-删除，0-未删除
    @TableField("create_time")
    private Timestamp createTime;            // 订单创建时间
    @TableField("update_time")
    private Timestamp updateTime;            // 订单更新时间

}