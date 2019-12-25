package backEnd.handler.pay;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_pay")
public class Pay {

    public static final int STATUS_UNPAID = 0;          // 支付状态-未支付
    public static final int STATUS_FINISH = 1;          // 支付状态-已支付
    public static final int TYPE_DETAIL = 0;            // 订单类型-订单费用详情
    public static final int TYPE_CONTACT = 1;           // 订单类型-获取联系方式
    public static final int TYPE_PATENT = 2;            // 订单类型-购买订单

    @TableField("pay_id")
    private String payId;           // 支付单id
    @TableField("from_id")
    private String FromId;          // 支付人id
    @TableField("to_id")
    private String toId;            // 收款人id
    @TableField("transaction_id")
    private String transactionId;   // 第三方支付(如微信、支付宝等)的订单id
    private int status;             // 支付状态
    private int type;               // 支付类型
    @TableField("follow_id")
    private String followId;        // 支付单对应的本系统内部订单id
    @TableField("main)sum")
    private BigDecimal mainSum;     // 专利费用
    @TableField("platform_sum")
    private BigDecimal platformSum; // 平台代理费用
    @TableField("proxy_sum")
    private BigDecimal proxySum;    // 第三方代理费用
    @TableField("contact_sum")
    private BigDecimal contactSum;  // 获取联系方式的费用
    private Timestamp timestamp;    // 时间戳

}
