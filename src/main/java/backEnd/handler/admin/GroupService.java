package backEnd.handler.admin;

import backEnd.handler.admin.mapper.GroupMapper;
import backEnd.handler.admin.model.Group;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupMapper groupMapper;

    public List<Group> getGroupList() {
        return groupMapper.selectList(null);
    }

    public boolean addGroup(Group group) {
        return groupMapper.insert(group) > 0;
    }

    public boolean removeGroup(String groupId) {
        UpdateWrapper<Group> wrapper = new UpdateWrapper<>();
        wrapper.eq("group_id", groupId);
        return groupMapper.delete(wrapper) > 0;
    }

    public boolean updateGroup(Group group) {
        UpdateWrapper<Group> wrapper = new UpdateWrapper<>();
        wrapper.eq("group_id", group.getGroupId());
        return groupMapper.update(group, wrapper) > 0;
    }

}
