package com.website.eap.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/14
 * Time: 12:04
 * Desc: 栈溢出
 *
 * java  -Xms10m  -Xmx10m -XX:+HeapDumpOnOutOfMemoryError  com.website.eap.oom.HeapOOM
 */
public class HeapOOM{
    static class OOMObject{
    }
    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}