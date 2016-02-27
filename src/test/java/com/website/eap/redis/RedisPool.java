package com.website.eap.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/14
 * Time: 11:20
 * Desc:
 */
public class RedisPool {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6380);
        String keys = "randkey";

        // 存数据
        jedis.set(keys, "wacai");


        // 取数据
        String value = jedis.get(keys);


        System.out.println(value);

    }

    public void sender(String message) {
        this.sender(message);

    }


    private ApplicationContext app;
    private ShardedJedisPool pool;

    @Before
    public void before() throws Exception {
        app = new ClassPathXmlApplicationContext("spring/conf.spring.xml", "spring/redis.spring.xml");
        pool = (ShardedJedisPool) app.getBean("shardedJedisPool");
    }

    @Test
    public void test() {

        // 从池中获取一个Jedis对象
        ShardedJedis jedis = pool.getResource();
        String keys = "target.monitor";
        String value = "target.monitor";
        // 删数据
        jedis.del(keys);
        // 存数据
        jedis.set(keys, value);
        // 取数据
        String v = jedis.get(keys);

        System.out.println(v);

        // 释放对象池
        pool.returnResource(jedis);

    }

}
