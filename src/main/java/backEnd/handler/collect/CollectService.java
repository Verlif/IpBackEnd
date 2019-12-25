package backEnd.handler.collect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectService {
    @Autowired
    private CollectMapper collectMapper;

    public boolean addCollect(Collect collect) {
        return collectMapper.insert(collect) > 0;
    }

    public boolean deleteCollect(String collectId) {
        UpdateWrapper<Collect> wrapper = new UpdateWrapper<>();
        Map<String, String> map = new HashMap<>();
        map.put("collect_id", collectId);
        wrapper.allEq(map);
        return collectMapper.delete(wrapper) > 0;
    }

    public boolean updateCollect(Collect collect) {
        return collectMapper.updateById(collect) > 0;
    }

    public List<Collect> getCollectList(Collect collect) {
        return collectMapper.selectList(getWrapper(collect));
    }

    public Collect getCollect(String collectId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("collect_id", collectId);
        return collectMapper.selectOne(wrapper);
    }

    private QueryWrapper<Collect> getWrapper(Collect collect) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        map.put("collect_type", String.valueOf(collect.getCollectType()));
        if (collect.getCollectId() != null && !collect.getCollectId().equals("")) {
            map.put("collect_id", collect.getCollectId());
        }
        if (collect.getUserId() != null && !collect.getUserId().equals("")) {
            map.put("user_id", collect.getUserId());
        }
        if (collect.getFollowId() != null && !collect.getFollowId().equals("")) {
            map.put("follow_id", collect.getFollowId());
        }
        return wrapper.allEq(map);
    }
}
