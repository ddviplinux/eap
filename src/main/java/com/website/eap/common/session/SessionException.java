/*
 * @(#)SessionException.java 1.0.0 12/11/16
 * Copyright 2012© Emagsoftware Technology Co., Ltd. All Rights reserved.
 */

package com.website.eap.common.session;

/**
 * function description
 *
 * @author zhizunbao
 * @version 1.0.0
 */
public class SessionException extends RuntimeException {
    public SessionException() {
        super();
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionException(Throwable cause) {
        super(cause);
    }
}
