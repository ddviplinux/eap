package com.website.eap.common.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: zhizunbao
 * Date: 15/12/30
 * Time: 15:22
 * Desc:
 */
@Service
public class RequestEventObserverImpl implements RequestEventObserver {

    @Override
    public void completed(HttpServletRequest servletRequest, HttpServletResponse response) {

    }
}
