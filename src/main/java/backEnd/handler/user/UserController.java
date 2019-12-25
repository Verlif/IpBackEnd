package backEnd.handler.user;

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
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @return 检索出来的用户信息列表
     */
    @PostMapping("user/getList")
    public Results getUserList(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestAttribute(RequestFilter.BELONG_ID_IN_TOKEN) String belong,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size
    ) {
        PageHelper.startPage(page, size);
        List<User> userList = userService.getUserList(belong);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return new SusResults(pageInfo);
    }

    /**
     * 获取用户详情
     * @param adminId
     * @param belong
     * @param userId
     * @return
     */
    @RequestMapping("user/detail")
    public Results getUser(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestAttribute(RequestFilter.BELONG_ID_IN_TOKEN) String belong,
            @RequestParam String userId
    ) {
        User user = userService.getUserById(userId, belong);
        if (user != null) {
            return new SusResults(user);
        } else return new FailResults("未查询到该用户");
    }

    /**
     * 从数据库中删除用户
     * @param adminId
     * @param belong
     * @param userId
     * @return
     */
    public Results removeUser(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestAttribute(RequestFilter.BELONG_ID_IN_TOKEN) String belong,
            @RequestParam String userId
    ) {
        if (userService.removeUser(userId, belong)) {
            return new SusResults();
        } else return new FailResults("未能删除该用户");
    }

    /**
     * 更新用户数据
     * @param adminId
     * @param belong
     * @param data
     * @return
     */
    public Results updateUser(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestAttribute(RequestFilter.BELONG_ID_IN_TOKEN) String belong,
            @RequestBody String data
    ) {
        User user = JSONGet.getValue(data, "user", User.class);
        if (userService.updateUser(user)) {
            return new SusResults();
        } else return new FailResults("更新数据失败");
    }

    /**
     * 新增用户
     * @param adminId
     * @param belong
     * @param data
     * @return
     */
    public Results addUser(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestAttribute(RequestFilter.BELONG_ID_IN_TOKEN) String belong,
            @RequestBody String data
    ) {
        User user = JSONGet.getValue(data, "user", User.class);
        if (userService.addUser(user)) {
            return new SusResults();
        } else return new FailResults("添加用户失败");
    }
}
