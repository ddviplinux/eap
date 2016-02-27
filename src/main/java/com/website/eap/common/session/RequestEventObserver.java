/*
 * @(#)RequestEventObserver.java 1.0.0 12/11/16
 * Copyright 2012© Emagsoftware Technology Co., Ltd. All Rights reserved.
 */

package com.website.eap.common.session;

/**
 * function description
 *
 * @author zhizunbao
 * @version 1.0.0
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

interface RequestEventObserver {
    public void completed(HttpServletRequest servletRequest, HttpServletResponse response);
}
