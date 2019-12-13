package backEnd.model.ext;

import backEnd.model.Results;

public class FailResults extends Results {

    // 登陆时，用户手机号并未注册
    public static final String NONEXISTENT = "201";

    // 未查找到数据时
    public static final String NO_DATA = "501";
    // token中的用户Id于请求的用户id不匹配时
    public static final String MISMATCH = "502";
    // 一般问题提示
    public static final String DEFAULT = "503";
    // 未知错误
    public static final String UNKNOWN_ERROR = "504";
    // token过期
    public static final String TOKEN_DISABLED = "505";
    // 没有找到token
    public static final String NO_TOKEN = "506";

    /**
     * 错误结果，默认返回
     * @param msg
     */
    public FailResults(String msg) {
        setCode(UNKNOWN_ERROR);
        setMsg(msg);
    }
    public FailResults(String msg, String errorCode) {
        setCode(errorCode);
        setMsg(msg);
    }
}
