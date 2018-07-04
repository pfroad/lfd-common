package com.lfd.frontend.common.data;

/**
 * Created by ryan on 12/20/16.
 */
public enum ResponseCode {
    SUCCESS(200, "success"),
    NO_CHANGE(304, "no any changes"),

    REQUEST_ERROR(400, "request error"),

    NOT_AUTHORIZED(401, "not authorized"),
//    LOGIN_FAILED(4011, "登录失败!"),
//    NOT_SUPPORT_VERSION(4012, "not support this version"),
//    SMS_CODE_NOT_VERIFIED(4013, "短信验证码错误！"),
    LOGIN_EXPIRED(4014, "登录过期！"),
//    MOBILE_EXISTED(4015, "号码已注册，请登录！"),
//    MOBILE_NOT_REG(4016, "电话号码没有注册！"),
//    ERROR_ACCOUNT(4017, "用户名或者密码错误！"),
    NOAVAILIBLE_SPACE(4015, "no available space"),
    MISS_REQUIRED(402, "miss required parameters"),
    MISS_APPID(4021, "miss appId"),
    MISS_SIGN(4022, "miss request sign"),
    MISS_TIMESTAMP(4023, "miss request timestamp"),

    INVALID_SIGN(403, "invalid sign"),

    SERVER_ERROR(500, "internal server error"),

//    UNCOMPLETED_ORDERS(1011, "有2张未完成订单！"),
//    UNPAID_ORDERS(1012, "您有未支付订单，请支付后再下单！"),
//    PLATENO_UNPAID_ORDERS(1013, "车牌%有未支付订单，请通过%用户支付后再下单！"),
//    ONE_PARK_UNCOMPLETED_ORDERS(1014, "你在该车场有未完成订单！"),
//    PARK_TIMEOUT(1015, "车位已经抢光了!"),
//    WHITELIST_ORDER(1016, "车场已无可用车位！"),
//    SPACE_CANNOT_SERVICE(1017, "车位当前时段不可用！"),
//    MISS_MATCH_TIME(1018, "车位最晚可停至%s，是否继续下单？"),
//    ONE_PARK_SAME_PLATENO_UNCOMPLETED_ORDERS(1019, "车牌号在该车场有未完成订单！"),
//
//    PAY_FAILED(1030, "支付失败！")
    ;
    private int value;
    private String desc;

    ResponseCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
