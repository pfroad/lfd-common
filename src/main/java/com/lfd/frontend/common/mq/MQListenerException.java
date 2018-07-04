package com.lfd.frontend.common.mq;

/**
 * Created by Administrator on 2017/1/12.
 */
public class MQListenerException extends RuntimeException {
    public MQListenerException() {
    }

    public MQListenerException(String message) {
        super(message);
    }

    public MQListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQListenerException(Throwable cause) {
        super(cause);
    }

    public MQListenerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}