package com.suo.pagetest.service.ex;

/**
 * @author 86131
 */
public class UsernameDupliate extends ServiceException{
    public UsernameDupliate() {
        super();
    }

    public UsernameDupliate(String message) {
        super(message);
    }

    public UsernameDupliate(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDupliate(Throwable cause) {
        super(cause);
    }

    protected UsernameDupliate(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
