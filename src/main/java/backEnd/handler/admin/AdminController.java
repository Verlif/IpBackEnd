package backEnd.handler.admin;

import backEnd.filter.RequestFilter;
import backEnd.handler.admin.model.Admin;
import backEnd.handler.admin.model.Group;
import backEnd.handler.admin.model.Right;
import backEnd.model.RecordLogger;
import backEnd.model.Results;
import backEnd.model.ext.FailResults;
import backEnd.model.ext.SusResults;
import backEnd.utils.JSONGet;
import backEnd.utils.JwtUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private RightService rightService;

    @Autowired
    private RecordLogger logger;

    @PostMapping("admin/login")
    public Results login(
            @RequestBody String data
    ) {
        final String name = JSONGet.getString(data, "name");
        final String password = JSONGet.getString(data, "password");
        Admin admin = adminService.adminLogin(name, password);
        if (admin != null) {
            HashMap<String, String> map = new HashMap<>();
            map.put(RequestFilter.ADMIN_ID_IN_TOKEN, admin.getUserId());
            map.put(RequestFilter.BELONG_ID_IN_TOKEN, admin.getBelong());
            logger.record(admin, "自己", "登录");
            return new SusResults(RequestFilter.TOKEN_NAME, JwtUtils.createJWT(map));
        } else return new FailResults("登录失败");
    }

    @GetMapping("admin/group/getList")
    public Results getGroupList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size
    ) {
        PageHelper.startPage(page, size);
        List<Group> list = groupService.getGroupList();
        PageInfo<Group> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }

    @PostMapping("admin/group/add")
    public Results addGroup(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Group group = JSONGet.getValue(data, "group", Group.class);
        if (group != null && groupService.addGroup(group)) {
            logger.record(adminId, "Group-" + group.getGroupId(), "添加");
            return new SusResults();
        } else return new FailResults("添加用户组失败");
    }

    @PostMapping("admin/group/delete")
    public Results removeGroup(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        final String groupId = JSONGet.getString(data, "groupId");
        if (groupService.removeGroup(groupId)) {
            logger.record(adminId, "Group-" + groupId, "删除");
            return new SusResults();
        } else return new FailResults("删除用户组失败: " + groupId);
    }

    @PostMapping("admin/group/update")
    public Results updateGroup(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Group group = JSONGet.getValue(data, "group", Group.class);
        if (groupService.updateGroup(group)) {
            logger.record(adminId, "Group-" + group.getGroupId(), "修改");
            return new SusResults();
        } else return new FailResults("修改用户组失败: " + group.getGroupId());
    }

    @GetMapping("admin/right/getList")
    public Results getRightList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size
    ) {
        PageHelper.startPage(page, size);
        List<Right> list = rightService.getRightList();
        PageInfo<Right> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }

    @PostMapping("admin/right/add")
    public Results addRight(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Right right = JSONGet.getValue(data, "right", Right.class);
        if (right != null && rightService.addRight(right)) {
            logger.record(adminId, "Right-" + right.getRightId(), "添加");
            return new SusResults();
        } else return new FailResults("添加权限失败");
    }

    @PostMapping("admin/right/delete")
    public Results removeRight(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        final String rightId = JSONGet.getString(data, "rightId");
        if (rightService.removeRight(rightId)) {
            logger.record(adminId, "Right-" + rightId, "删除");
            return new SusResults();
        } else return new FailResults("删除权限失败: " + rightId );
    }

    @PostMapping("admin/right/update")
    public Results updateRight(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Right right = JSONGet.getValue(data, "right", Right.class);
        if (rightService.updateRight(right)) {
            logger.record(adminId, "Right-" + right.getRightId(), "修改");
            return new SusResults();
        } else return new FailResults("修改权限失败: " + right.getRightId());
    }
}
