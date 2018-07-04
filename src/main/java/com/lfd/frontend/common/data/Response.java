package com.lfd.frontend.common.data;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ryan on 12/20/16.
 */
public class Response {
    private Integer code;
    private String desc;
    private Object result;
    private long timestamp = System.currentTimeMillis();

    public Response(ResponseCode responseCode) {
        this.code = responseCode.getValue();
        this.desc = responseCode.getDesc();
    }

    public Response(ResponseCode responseCode, String desc) {
        this.code = responseCode.getValue();
        this.desc = desc;
    }

    public Response(ResponseCode responseCode, Object result) {
        this.code = responseCode.getValue();
        this.desc = responseCode.getDesc();
        this.result = result;
    }

    public Response(Exception cause) {
        this.code = ResponseCode.SERVER_ERROR.getValue();
        this.desc = ResponseCode.SERVER_ERROR.getDesc();
        this.result = cause.getMessage();
    }

    public Response(Object result) {
        this.code = ResponseCode.SUCCESS.getValue();
        this.desc = ResponseCode.SUCCESS.getDesc();
        this.result = result;
    }

    public Response(ResponseCode responseCode, ServiceException cause) {
        this.code = responseCode.getValue();
        this.desc = StringUtils.isEmpty(cause.getMessage()) ? responseCode.getDesc() : cause.getMessage();
        this.result = cause.getMessage();
    }

    public Response(Integer code, String desc, Object result) {
        this.code = code;
        this.desc = desc;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
