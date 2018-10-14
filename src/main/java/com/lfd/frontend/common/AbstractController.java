package com.lfd.frontend.common;

import com.lfd.frontend.common.data.CloudRequest;
import com.tencent.xinge.XingeApp;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 12/16/2016.
 */
public class AbstractController {
    public String getLoginUserId(HttpServletRequest request) {
        final Object userId = request.getAttribute("userId");
        return userId == null ? null : (String) userId;
    }

    public RowBounds rowBounds(HttpServletRequest request) {
        Integer size = ServletRequestUtils.getIntParameter(request, "size", 20);
        final Integer page = ServletRequestUtils.getIntParameter(request, "page", 1);
        size = size > 20 ? 20 : size;
        final Integer offset = page == 0 ? page : (page - 1) * size;
        return new RowBounds(offset, size);
    }

    public boolean pushNotification(HttpServletRequest request,
                                    XingeNotifyService notifyService,
                                    String title,
                                    String content,
                                    String account) {
        final Integer clientType = Integer.parseInt(request.getHeader("clientType"));
        if (clientType.equals(CloudRequest.ClientType.IOS.getValue())) {
            return notifyService.sendToIos(content, account, XingeApp.IOSENV_DEV);
        } else if (clientType.equals(CloudRequest.ClientType.ANDROID.getValue())) {
            return notifyService.sendToAndroid(title, content, account);
        } else {
            return false;
        }
    }
}
