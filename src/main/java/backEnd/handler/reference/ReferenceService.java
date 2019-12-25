package backEnd.handler.reference;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceMapper referenceMapper;

    public boolean addReference(Reference reference) {
        return referenceMapper.insert(reference) > 0;
    }

    public boolean removeReference(String referenceId) {
        UpdateWrapper<Reference> wrapper = new UpdateWrapper<>();
        wrapper.eq("reference_id", referenceId);
        return referenceMapper.delete(wrapper) > 0;
    }

    public boolean updateReference(Reference reference) {
        QueryWrapper<Reference> wrapper = new QueryWrapper<>();
        wrapper.eq("reference_id", reference);
        return referenceMapper.update(reference, wrapper) > 0;
    }

    public List<Reference> getReferenceList(Reference reference) {
        QueryWrapper<Reference> wrapper = getWrapper(reference);
        return referenceMapper.selectList(wrapper);
    }

    private QueryWrapper<Reference> getWrapper(Reference reference) {
        QueryWrapper<Reference> wrapper = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        if (reference.getRefType() != null && !reference.getRefType().equals("")) {
            map.put("ref_type", reference.getRefType());
        }
        if (reference.getUpId() != null && !reference.getUpId().equals("")) {
            map.put("up_id", reference.getUpId());
        }
        return wrapper.allEq(map);
    }
}
