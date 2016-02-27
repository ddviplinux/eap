package com.website.eap.common.session;

import org.springframework.stereotype.Service;

/**
 * SessionListener  interface adaptor
 *
 * @author zhizunbao
 * @version 1.0.0
 */

@Service
public class SessionListenerAdaptor implements SessionListener {

    public void onAttributeChanged(RedisHttpSession session) {
    }

    public void onInvalidated(RedisHttpSession session) {
    }
}
