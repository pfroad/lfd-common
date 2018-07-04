package com.lfd.frontend.common.data;

/**
 * Created by ryan on 12/20/16.
 */
public class ServiceException extends RuntimeException {
    private ResponseCode responseCode;
    private String message;
    private Exception cause;

    public ServiceException(String message, ResponseCode responseCode, Exception cause) {
        super(cause);
        this.responseCode = responseCode;
        this.message = message;
        this.cause = cause;
    }

    public ServiceException(ResponseCode responseCode, Exception cause) {
        super(cause);
        this.responseCode = responseCode;
        this.message = responseCode.getDesc();
        this.cause = cause;
    }

    public ServiceException(String message, ResponseCode responseCode) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public ServiceException(ResponseCode responseCode) {
        this.responseCode = responseCode;
        this.message = responseCode.getDesc();
    }

    public ServiceException(Exception cause) {
        super(cause);
        this.responseCode = ResponseCode.SERVER_ERROR;
        this.message = cause.getMessage();
        this.cause = cause;
    }

    public ServiceException(String message) {
        super(message);
        this.responseCode = ResponseCode.SUCCESS;
        this.message = message;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Exception getCause() {
        return cause;
    }

    public void setCause(Exception cause) {
        this.cause = cause;
    }
}
