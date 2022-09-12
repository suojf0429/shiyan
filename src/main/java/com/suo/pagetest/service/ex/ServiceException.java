package com.suo.pagetest.service.ex;

/**
 * @author 86131
 */
public class ServiceException extends RuntimeException {

    //快捷方式：右键+Generate+Override Methods   重写父类的方法


    public ServiceException() {
        super();
    }

    public ServiceException(String message) {  //（常用）
        super(message);
    }

    public ServiceException(String message, Throwable cause) {  //抛出异常信息+对象（常用）
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
