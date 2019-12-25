package backEnd.handler.patent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatentService {
    @Autowired
    private PatentMapper patentMapper;

    public boolean addPatent(Patent patent) {
        return patentMapper.insert(patent) > 0;
    }

    public boolean removePatent(String patentId) {
        UpdateWrapper<Patent> wrapper = new UpdateWrapper<>();
        wrapper.eq("patent_id", patentId);
        return patentMapper.delete(wrapper) > 0;
    }

    public boolean updatePatent(Patent patent) {
        UpdateWrapper<Patent> wrapper = new UpdateWrapper<>();
        wrapper.eq("patent_id", patent.getPatentId());
        return patentMapper.update(patent,wrapper) > 0;
    }

    public Patent getPatentById(String patentId) {
        QueryWrapper<Patent> wrapper = new QueryWrapper<>();
        wrapper.eq("patent_id", patentId);
        return patentMapper.selectOne(wrapper);
    }

    public List<Patent> getPatentList(Patent patent) {
        QueryWrapper<Patent> wrapper = getWrapper(patent);
        return patentMapper.selectList(wrapper);
    }

    private QueryWrapper<Patent> getWrapper(Patent patent) {
        QueryWrapper<Patent> wrapper = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        if (patent.getUserId() != null && !patent.getUserId().equals("")) {
            map.put("user_id", patent.getUserId());
        }
        if (patent.getPatentCode() != null && !patent.getPatentCode().equals("")) {
            map.put("patent_code", patent.getPatentCode());
        }
        return wrapper.allEq(map);
    }
}
