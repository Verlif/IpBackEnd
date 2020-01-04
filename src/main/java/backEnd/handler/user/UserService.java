package backEnd.handler.user;

import backEnd.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(String belong) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("belong", belong);
        return userMapper.selectList(wrapper);
    }

    public User getUserById(String userId, String belong) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("belong", belong);
        wrapper.allEq(map);
        return userMapper.selectOne(wrapper);
    }

    public boolean updateUser(User user) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        return userMapper.update(user, wrapper) > 0;
    }

    public boolean removeUser(String userId, String belong) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        Map<String, String> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("belong", belong);
        wrapper.allEq(map);
        return userMapper.delete(wrapper) > 0;
    }

    public boolean addUser(User user) {
        if (user.getUserId() == null || user.getUserId().equals(""))
            user.setUserId(UUIDUtil.getUUID());
        return userMapper.insert(user) > 0;
    }
}
