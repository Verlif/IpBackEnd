package backEnd.handler.login;

import backEnd.model.Results;
import backEnd.model.ext.FailResults;
import backEnd.model.ext.SusResults;
import backEnd.utils.JSONGet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private SecurityManager securityManager;

    @RequestMapping("/start/login")
    public Results login(
            @RequestBody String data
    ) {
        String userName = JSONGet.getString(data, "userName");
        String userPwd = JSONGet.getString(data, "userPwd");

        // 获取 SecurityManager 实例
        SecurityManager securityManager = new IniSecurityManagerFactory
                ("classpath:shiro/shiro.ini").getInstance();

        // 把 SecurityManager 绑定到 SecurityUtils 中
        SecurityUtils.setSecurityManager(securityManager);
        // 得到当前执行的用户
        Subject currentUser = SecurityUtils.getSubject();
        // 创建 token 令牌,用户名/密码
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
        try {
            // 身份验证
            currentUser.login(token);
            currentUser.execute(() -> {});


            return new SusResults("登录成功");
        } catch ( UnknownAccountException uae ) {
            return new FailResults("账户不存在");
        } catch ( IncorrectCredentialsException ice ) {
            return new FailResults("密码错误");
        } catch ( LockedAccountException lae ) {
            return new FailResults("用户已被锁定");
        } catch ( AuthenticationException ae ) {
            ae.printStackTrace();
            return new FailResults("未知错误");
        }
    }
}
