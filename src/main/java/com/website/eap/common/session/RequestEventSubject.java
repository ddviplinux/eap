/*
 * @(#)RequestEventContainer.java 1.0.0 12/11/16
 * Copyright 2012© Emagsoftware Technology Co., Ltd. All Rights reserved.
 */

package com.website.eap.common.session;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Event Observer Design pattern of Subject
 *
 * @author zhizunbao
 * @version 1.0.0
 */
@Service
public class RequestEventSubject {

    @Resource
    private RequestEventObserver listener;

    public void attach(RequestEventObserver eventObserver) {
        listener = eventObserver;
    }

    public void detach() {
        listener = null;
    }

    public void completed(HttpServletRequest servletRequest, HttpServletResponse response) {
        if(listener != null)
            listener.completed(servletRequest, response);
    }
}
