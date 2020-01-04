package backEnd.handler.admin;

import backEnd.handler.admin.mapper.RightMapper;
import backEnd.handler.admin.model.Right;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RightService {
    @Autowired
    private RightMapper rightMapper;

    public List<Right> getRightList() {
        return  rightMapper.selectList(null);
    }

    public boolean addRight(Right right) {
        right.setCreateTime(new Timestamp(new Date().getTime()));
        return rightMapper.insert(right) > 0;
    }

    public boolean removeRight(String rightId) {
        UpdateWrapper<Right> wrapper = getWrapper(rightId);
        return rightMapper.delete(wrapper) > 0;
    }

    public boolean updateRight(Right right) {
        UpdateWrapper<Right> wrapper = getWrapper(right.getRightId());
        return rightMapper.update(right, wrapper) > 0;
    }

    private UpdateWrapper<Right> getWrapper(String rightId) {
        UpdateWrapper<Right> wrapper = new UpdateWrapper<>();
        wrapper.eq("right_id", rightId);
        return wrapper;
    }
}
