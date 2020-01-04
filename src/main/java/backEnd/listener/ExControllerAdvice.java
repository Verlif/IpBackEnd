package backEnd.listener;

import backEnd.model.Results;
import backEnd.model.ext.FailResults;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@ControllerAdvice
public class ExControllerAdvice {

    private Logger logger;

    public ExControllerAdvice() {
        logger = Logger.getLogger("Exception");
    }

    /**
     * 全局异常捕捉处理
     * @param ex    异常
     * @return  错误提示信息
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Results errorHandler(Exception ex) {
        logger.warning(ex.getMessage());
        return new FailResults("请求出错");
    }

}
