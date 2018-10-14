package com.lfd.frontend.common;

import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XingeNotifyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XingeNotifyService.class);
    private Long accessId;
    private String secretKey;
    private XingeApp xingeApp;

    public XingeNotifyService(XingeApp xingeApp) {
        this.xingeApp = xingeApp;
    }

    public XingeNotifyService(Long accessId, String secretKey) {
        this.accessId = accessId;
        this.secretKey = secretKey;
        this.xingeApp = new XingeApp(this.accessId, this.secretKey);
    }

    public boolean sendToIos(String content, String account, int env) {
        MessageIOS message = new MessageIOS();
        message.setAlert(content);
        message.setBadge(1);
        message.setSound("beep.wav");

//        XingeApp xinge = new XingeApp(accessId, secretKey);
        JSONObject ret = xingeApp.pushSingleAccount(0, account, message, env);
        final Integer retCode = ret.getInt("ret_code");
        if (retCode != 0) {
            LOGGER.error("push notification failed,", ret.getString("err_msg"));
            return false;
        }
        return true;
    }

    public boolean sendToAndroid(String title, String content, String account) {
        Message message = new Message();
        message.setType(Message.TYPE_NOTIFICATION);
        message.setTitle(title);
        message.setContent(content);

//        XingeApp xinge = new XingeApp(accessId, secretKey);
        JSONObject ret = this.xingeApp.pushSingleAccount(0, account, message);
        final Integer retCode = ret.getInt("ret_code");
        if (retCode != 0) {
            LOGGER.error("push notification failed,", ret.getString("err_msg"));
            return false;
        }
        return true;
    }
}
