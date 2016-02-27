package com.website.eap.common.session;

/**
 * SessionListener
 *
 * @author zhizunbao
 * @version 1.0.0
 */
public interface SessionListener {
    public void onAttributeChanged(RedisHttpSession session);
    public void onInvalidated(RedisHttpSession session);
}
