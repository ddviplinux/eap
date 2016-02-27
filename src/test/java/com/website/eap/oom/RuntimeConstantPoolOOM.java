package com.website.eap.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/14
 * Time: 11:26
 * Desc: -XX:PermSize=10m -XX:MaxPermSize=10m java8无效
 */
public class RuntimeConstantPoolOOM{
    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        int i = 0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
