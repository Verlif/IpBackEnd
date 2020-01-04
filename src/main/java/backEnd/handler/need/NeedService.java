package backEnd.handler.need;

import backEnd.handler.collect.Collect;
import backEnd.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NeedService {
    @Autowired
    private NeedMapper needMapper;

    public boolean addNeed(Need need) {
        if (need.getNeedId() == null || need.getNeedId().equals(""))
            need.setNeedId(UUIDUtil.getUUID());
        need.setCreateTime(new Timestamp(new Date().getTime()));
        return needMapper.insert(need) > 0;
    }

    public boolean removeNeed(String needId) {
        UpdateWrapper<Need> wrapper = new UpdateWrapper<>();
        wrapper.eq("need_id", needId);
        return needMapper.delete(wrapper) > 0;
    }

    public boolean updateNeed(Need need) {
        UpdateWrapper<Need> wrapper = new UpdateWrapper<>();
        wrapper.eq("need_id", need.getNeedId());
        return needMapper.update(need, wrapper) > 0;
    }

    public List<Need> getNeedList(Need need) {
        QueryWrapper<Need> wrapper = getWrapper(need);
        return needMapper.selectList(wrapper);
    }

    public Need getNeed(String needId) {
        QueryWrapper<Need> wrapper = new QueryWrapper<>();
        wrapper.eq("need_id", needId);
        return needMapper.selectOne(wrapper);
    }

    private QueryWrapper<Need> getWrapper(Need need) {
        QueryWrapper<Need> wrapper = new QueryWrapper<>();
        if (need.getUserId() != null && !need.getUserId().equals("")) {
            wrapper.eq("user_id", need.getUserId());
        }
        if (need.getNeedName() != null && !need.getNeedName().equals("")) {
            wrapper.like("need_name", need.getNeedName());
        }
        if (need.getNeedKeywords() != null && !need.getNeedKeywords().equals("")) {
            wrapper.like("need_keywords", need.getNeedKeywords());
        }
        return wrapper;
    }
}
