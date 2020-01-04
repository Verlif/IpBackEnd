package backEnd.handler.pay;

import backEnd.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayService {
    @Autowired
    private PayMapper payMapper;

    public boolean addPay(Pay pay) {
        if (pay.getPayId() == null || pay.getPayId().equals(""))
            pay.setPayId(UUIDUtil.getUUID());
        return payMapper.insert(pay) > 0;
    }

    public boolean removePay(String payId) {
        UpdateWrapper<Pay> wrapper = new UpdateWrapper<>();
        wrapper.eq("pay_id", payId);
        return payMapper.delete(wrapper) > 0;
    }

    public boolean updatePay(Pay pay) {
        UpdateWrapper<Pay> wrapper = new UpdateWrapper<>();
        wrapper.eq("pay_id", pay.getPayId());
        return payMapper.update(pay, wrapper) > 0;
    }

    public Pay getPayById(String payId) {
        QueryWrapper<Pay> wrapper = new QueryWrapper<>();
        wrapper.eq("pay_id", payId);
        return payMapper.selectOne(wrapper);
    }

    public List<Pay> getPayList(Pay pay) {
        QueryWrapper<Pay> wrapper = getWrapper(pay);
        return payMapper.selectList(wrapper);
    }

    private QueryWrapper<Pay> getWrapper(Pay pay) {
        QueryWrapper<Pay> wrapper = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        map.put("pay_status", String.valueOf(pay.getStatus()));
        map.put("pay_type", String.valueOf(pay.getType()));
        if (pay.getFromId() != null && !pay.getFromId().equals("")) {
            map.put("from_id", pay.getFollowId());
        }
        if (pay.getToId() != null && !pay.getToId().equals("")) {
            map.put("to_id", pay.getToId());
        }
        return wrapper.allEq(map);
    }
}
