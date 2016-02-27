package com.website.eap.oom;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/14
 * Time: 13:40
 * Desc:
 */
public class StackOOMCase2 {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args){
        StackOOMCase2 oom = new StackOOMCase2();
        oom.stackLeak();
    }

}
