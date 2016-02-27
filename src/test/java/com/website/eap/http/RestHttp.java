package com.website.eap.http;



import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/18
 * Time: 14:23
 * Desc:
 */
public class RestHttp {
    public static final MediaType JSON
            = MediaType.parse("application/html; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    public static void main(String[] args){
        RestHttp  restHttp=new RestHttp();
        String url="http://127.0.0.1:1888/monitor/sender_message.htm";
        String json=null;
        //message={system:"wac-partner",topic:"exception",eventMsg:{eventName:"exception",eventValue:"1",eventReason:"exception",eventTime:"1447830098"}}
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("system","wac-partner");
        jsonObject.put("topic","dubbo-api");
        JSONObject eventMsg=new JSONObject();
        eventMsg.put("eventName","timeout");
        eventMsg.put("eventValue",1);
        eventMsg.put("eventReason","exception");
        eventMsg.put("eventTime",new Date().getTime());
        jsonObject.put("eventMsg",eventMsg);
        json=jsonObject.toString();
        try {
           String result= restHttp.post(url,json);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = new FormEncodingBuilder().add("message", json).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.setReadTimeout(1,TimeUnit.HOURS);
        client.setConnectTimeout(1,TimeUnit.HOURS);
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
