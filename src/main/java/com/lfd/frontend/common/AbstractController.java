package com.lfd.frontend.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 12/16/2016.
 */
public class AbstractController {
    public String getLoginUserId(HttpServletRequest request) {
        final Object userId = request.getAttribute("userId");
        return userId == null ? null : (String) userId;
    }
}
