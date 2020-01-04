package backEnd.handler.admin;

import backEnd.handler.admin.mapper.AdminMapper;
import backEnd.handler.admin.mapper.GroupMapper;
import backEnd.handler.admin.mapper.RightMapper;
import backEnd.handler.admin.model.Admin;
import backEnd.handler.admin.model.AdminRight;
import backEnd.handler.user.UserMapper;
import backEnd.handler.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private RightMapper rightMapper;
    @Autowired
    private AdminMapper adminMapper;

    public AdminRight getAdminById(String adminId) {
        return adminMapper.getAdminRight(adminId);
    }

    public Admin adminLogin(String userName, String userPassword) {
        return adminMapper.adminLogin(userName, userPassword);
    }
}
