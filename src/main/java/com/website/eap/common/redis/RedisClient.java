package com.website.eap.common.redis;

import com.website.eap.common.session.RedisHttpSession;
import org.springframework.stereotype.Service;

/**
 * User: zhizunbao
 * Date: 15/12/7
 * Time: 11:18
 * Desc:
 */

@Service
public class RedisClient {

    public void set(String key, int time, RedisHttpSession redisHttpSession) {

    }

    public void delete(String key) {

    }

    public RedisHttpSession get(String key) {
        return null;
    }
}
