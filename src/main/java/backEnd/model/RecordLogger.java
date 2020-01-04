package backEnd.model;

import backEnd.handler.admin.model.Admin;

import java.util.logging.Logger;

public class RecordLogger {

    private Logger logger;

    public RecordLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * 记录管理员操作
     * @param admin         管理员
     * @param targetDesc    操作对象描述
     * @param operateDesc   操作行为描述
     */
    public void record(Admin admin, String targetDesc, String operateDesc) {
        logger.info(
                "[" + admin.getUserName()  + "(" + admin.getUserId() + ")]: " +
                        "[target: " + targetDesc + "]: " +
                        operateDesc
        );
    }

    /**
     * 记录管理员操作
     * @param adminId       管理员Id
     * @param targetDesc    操作对象描述
     * @param operateDesc   操作行为描述
     */
    public void record(String adminId, String targetDesc, String operateDesc) {
        logger.info(
                "[(" + adminId + ")]: " +
                        "[target:" + targetDesc + "]: " +
                        operateDesc
        );
    }
}
