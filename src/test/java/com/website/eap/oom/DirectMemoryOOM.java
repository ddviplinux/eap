package com.website.eap.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/14
 * Time: 13:29
 * Desc: java -XX:MaxDirectMemorySize=10M  com.website.eap.oom.DirectMemoryOOM
 */
public class DirectMemoryOOM{
    private static final int _1MB = 1024* 1024 * 1024;
    public  static void main(String[] args) throws Exception{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while(true){
            //unsafe直接想操作系统申请内存
            unsafe.allocateMemory(_1MB);
        }
    }
}