package com.website.eap.oom;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/14
 * Time: 11:37
 * Desc:
 */
public class StackOOMCase1 {
    private void dontStop(){
        while(true){

        }
    }

    //无限创建线程,导致本地栈溢出
    public void stackLeakByThread(){
        while(true){
            Thread t = new Thread(new Runnable(){
                public void run(){
                    dontStop();
                }
            });//创建线程
            t.start();//线程可运行状态
        }
    }
    public static void main(String[] args){
        StackOOMCase1 oom = new StackOOMCase1();
        oom. stackLeakByThread();
    }
}