package backEnd.handler.order;

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
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("order/add")
    public Results addOrder(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Order order = JSONGet.getValue(data, "order", Order.class);
        if (orderService.addOrder(order)) {
            return new SusResults();
        } else return new FailResults("添加订单失败");
    }

    @PostMapping("order/delete")
    public Results removeOrder(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        final String orderId = JSONGet.getString(data, "orderId");
        if (orderService.removeOrder(orderId)) {
            return new SusResults();
        } else return new FailResults("删除订单失败: " + orderId);
    }

    @PostMapping("order/update")
    public Results updateOrder(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Order order = JSONGet.getValue(data, "order", Order.class);
        if (orderService.updateOrder(order)) {
            return new SusResults();
        } else return new FailResults("更行订单失败: " + order.getOrderId());
    }

    @GetMapping("order/get")
    public Results getOrder(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam String orderId
    ) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return new SusResults(order);
        } else return new FailResults("获取订单失败: " + orderId);
    }

    @PostMapping("order/getList")
    public Results getOrderList(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestBody String data
    ) {
        Order order = JSONGet.getValue(data, "order", Order.class);
        PageHelper.startPage(page, size);
        List<Order> list = orderService.getOrderList(order);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }
}
