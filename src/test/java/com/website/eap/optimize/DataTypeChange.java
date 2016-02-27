package com.website.eap.optimize;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/13
 * Time: 20:41
 * Desc:
 */
public class DataTypeChange {
    public static void main(String[] args)

    {
        //5万次的基本数据类型转化String操作
        int loopTime = 50000;
        Integer i = 0;
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++)

        {
            String str = String.valueOf(i);
        }

        System.out.println("String.valueOf() cost : " + (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++)

        {

            String str = i.toString();

        }
        System.out.println("Integer.toString() cost :" + (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++)

        {
            String str = i + "";
        }
        System.out.println("i + \"\" cost :" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
