package com.website.eap.oom;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.zip.Deflater;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/14
 * Time: 11:14
 * Desc:
 */
public class NativeMemoryOOMCase {
    public static void main( String args[] ) throws Exception{
        for(int i=0;i<100;i++){
            new Thread(new Runnable(){
                public void run(){
                    for(int i=0;i<350;i++){
                        Deflater deflater = new Deflater( 9, true );
                        deflater.end();
                    }
                    byte[] bytes1=new byte[1024*512];
                    byte[] bytes2=new byte[1024*512];
                    byte[] bytes3=new byte[1024*512];
                    byte[] bytes4=new byte[1024*512];
                    byte[] bytes5=new byte[1024*512];
                    byte[] bytes6=new byte[1024*512];
                    byte[] bytes7=new byte[1024*512];
                    byte[] bytes8=new byte[1024*512];
                }
            }).start();
            Thread.sleep(1);
        }
        Thread.sleep(30000);
    }
}
