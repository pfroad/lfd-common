package com.lfd.frontend.common;

import com.tencent.xinge.XingeApp;
import com.tencent.xinge.bean.*;
import com.tencent.xinge.bean.ios.Alert;
import com.tencent.xinge.bean.ios.Aps;
import com.tencent.xinge.push.app.PushAppRequest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class XingeNotifyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XingeNotifyService.class);
    private String accessId;
    private String secretKey;
    private XingeApp xingeApp;

    public XingeNotifyService(XingeApp xingeApp) {
        this.xingeApp = xingeApp;
    }

    public XingeNotifyService(String accessId, String secretKey) {
        this.accessId = accessId;
        this.secretKey = secretKey;
        this.xingeApp = new XingeApp(this.accessId, this.secretKey);
    }

    public boolean sendToIos(String title, String content, String account, Environment env) {
        Message message = new Message();
        message.setContent(content);
        message.setTitle(title);

        MessageIOS msgIOS = new MessageIOS();
        Alert alert = new Alert();
        alert.setTitle(title);
        alert.setBody(content);

        Aps aps = new Aps();
        aps.setBadge_type(-2);
        aps.setAlert(alert);
        msgIOS.setAps(aps);

        message.setIos(msgIOS);

        ArrayList<String> accountList = new ArrayList<String>();
        accountList.add(account);

        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setPlatform(Platform.ios);
        pushAppRequest.setEnvironment(env);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(message);
//        pushAppRequest.setSend_time(new Date());
//        pushAppRequest.setStat_tag("test");
//        pushAppRequest.setPush_id("0");
        pushAppRequest.setAccount_list(accountList);
        pushAppRequest.setAudience_type(AudienceType.account);

//        XingeApp xinge = new XingeApp(accessId, secretKey);
        LOGGER.info(pushAppRequest.toString());
        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        final Integer retCode = ret.getInt("ret_code");
        if (retCode != 0) {
            LOGGER.error("push notification failed: " + retCode + "，" + ret.getString("err_msg"));
            return false;
        }
        return true;
    }

    public boolean sendToAndroid(String title, String content, String account) {
        MessageAndroid messageAndroid = new MessageAndroid();
//        messageAndroid.setBuilder_id(0);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(1);
        messageAndroid.setClearable(1);
        messageAndroid.setN_id(0);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);

        Message message = new Message();
        message.setAndroid(messageAndroid);
        message.setTitle(title);
        message.setContent(content);


        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setEnvironment(Environment.product);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setAudience_type(AudienceType.account);
//        pushAppRequest.setSeq(123);
//        pushAppRequest.setPush_id("0");
//        pushAppRequest.setSend_time("2018-10-09 17:24:20");
//        pushAppRequest.setStat_tag("test");
        pushAppRequest.setMessage(message);

        ArrayList<String> accountList = new ArrayList<>();
        accountList.add(account);
        pushAppRequest.setAccount_list(accountList);
//        XingeApp xinge = new XingeApp(accessId, secretKey);
        LOGGER.info(pushAppRequest.toString());
        JSONObject ret = this.xingeApp.pushApp(pushAppRequest.toString());
        final Integer retCode = ret.getInt("ret_code");
        if (retCode != 0) {
            LOGGER.error("push notification failed: " + retCode + "，" + ret.getString("err_msg"));
            return false;
        }
        return true;
    }
}
