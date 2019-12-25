package backEnd.filter;

import backEnd.handler.admin.mapper.AdminMapper;
import backEnd.handler.admin.model.AdminRight;
import backEnd.model.ext.FailResults;
import backEnd.utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class RequestFilter implements Filter {

    //与前端统一的位于请求头的token参数名
    public static final String TOKEN_NAME = "ClientToken";
    //在token中UserId的参数名，可用于向Controller中传入用户Id
    public static final String ADMIN_ID_IN_TOKEN = "adminId";
    //在token中UserId的参数名，可用于向Controller中传入用户Id
    public static final String BELONG_ID_IN_TOKEN = "belong";

    private Logger logger;

    @Autowired
    private AdminMapper adminMapper;

    public RequestFilter() {
        logger = Logger.getLogger("RequestFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        response.setContentType("application/json;charset=UTF-8");

        if (req.getHeaders(TOKEN_NAME).hasMoreElements()) {
            //获取request请求头中的token
            String token = req.getHeaders(TOKEN_NAME).nextElement();
            Claims claims = JwtUtils.parseJWT(token);

            /*
             * 若token无效，则拒绝后续请求动作
             * 从有效的token中查找"USER_ID_IN_TOKEN"参数
             * 将userId添加到request参数列表中，便于后续获取
             * */
            if (claims != null) {
                String adminId = (String) claims.get(ADMIN_ID_IN_TOKEN);
                if (permissionFilter(adminId, req.getRequestURI())) {
                    req.setAttribute(ADMIN_ID_IN_TOKEN, adminId);
                    req.setAttribute(BELONG_ID_IN_TOKEN, getBelong(adminId));
                    chain.doFilter(request, response);
                } else {
                    logger.warning(adminId + ": beyond the authority: " + req.getRequestURI());
                    ServletOutputStream out = response.getOutputStream();
                    out.write(JSON.toJSONString(new FailResults("权限不足", FailResults.TOKEN_DISABLED)).getBytes());
                    out.flush();
                }
            } else {
                logger.warning(req.getRequestURI() + ": token is out of date");
                ServletOutputStream out = response.getOutputStream();
                out.write(JSON.toJSONString(new FailResults("token已失效", FailResults.TOKEN_DISABLED)).getBytes());
                out.flush();
            }
        } else {
            logger.warning("no token in this request");
            ServletOutputStream out = response.getOutputStream();
            out.write(JSON.toJSONString(new FailResults("token未找到", FailResults.NO_TOKEN)).getBytes());
            out.flush();
        }
    }

    /**
     * 判断使用者权限
     * @param adminId   使用者id
     * @param url       执行的操作
     * @return  true-权限可用</p>
     *          false-越权行为
     */
    private boolean permissionFilter(String adminId, String url) {

//        AdminRight right = adminMapper.getAdminRight(adminId);
//        return right.getAdminRightUrl().contains(url);

        return true;
    }

    /**
     * 获取管理员的管辖范围
     * @param adminId   管理员id
     * @return  管辖代号
     */
    private String getBelong(String adminId) {
        return "public";
    }
}
