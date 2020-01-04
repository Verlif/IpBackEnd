package backEnd.handler.order;

import backEnd.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public boolean addOrder(Order order) {
        if (order.getOrderId() == null || order.getOrderId().equals(""))
            order.setOrderId(UUIDUtil.getUUID());
        return orderMapper.insert(order) > 0;
    }

    public boolean removeOrder(String orderId) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("order_id", orderId);
        return orderMapper.delete(wrapper) > 0;
    }

    public boolean updateOrder(Order order) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("order_id", order.getOrderId());
        return orderMapper.update(order, wrapper) > 0;
    }

    public Order getOrderById(String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        return orderMapper.selectOne(wrapper);
    }

    public List<Order> getOrderList(Order order) {
        QueryWrapper<Order> wrapper = getWrapper(order);
        return orderMapper.selectList(wrapper);
    }

    private QueryWrapper<Order> getWrapper(Order order) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        if (order.getUserId() != null && !order.getUserId().equals("")) {
            map.put("user_id", order.getUserId());
        }
        map.put("order_status", String.valueOf(order.getOrderStatus()));
        map.put("order_type", String.valueOf(order.getOrderType()));
        return wrapper.allEq(map);
    }
}
