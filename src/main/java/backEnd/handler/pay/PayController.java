package backEnd.handler.pay;

import backEnd.filter.RequestFilter;
import backEnd.model.Results;
import backEnd.model.ext.FailResults;
import backEnd.model.ext.SusResults;
import backEnd.utils.JSONGet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @PostMapping("pay/add")
    public Results addPay(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Pay pay = JSONGet.getValue(data, "pay", Pay.class);
        if (payService.addPay(pay)) {
            return new SusResults();
        } else return new FailResults("添加支付订单失败");
    }

    @PostMapping("pay/delete")
    public Results removePay(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        final String payId = JSONGet.getString(data, "payId");
        if (payService.removePay(payId)) {
            return new SusResults();
        } else return new FailResults("删除支付订单失败: " + payId);
    }

    @PostMapping("pay/update")
    public Results updatePay(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Pay pay = JSONGet.getValue(data, "pay", Pay.class);
        if (payService.updatePay(pay)) {
            return new SusResults();
        } else return new FailResults("更新支付订单失败: " + pay.getPayId());
    }

    @GetMapping("pay/get")
    public Results getPay(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam String payId
    ) {
        Pay pay = payService.getPayById(payId);
        if (pay != null) {
            return new SusResults(pay);
        } else return new FailResults("未能查到支付订单: " + payId);
    }

    @PostMapping("pay/getList")
    public Results getPayList(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestBody String data
    ) {
        Pay pay = JSONGet.getValue(data, "pay", Pay.class);
        PageHelper.startPage(page, size);
        List<Pay> list = payService.getPayList(pay);
        PageInfo<Pay> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }
}
