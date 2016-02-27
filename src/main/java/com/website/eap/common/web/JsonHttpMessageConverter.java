package com.website.eap.common.web;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 *
 */
public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        JsonResult result;
        if (obj instanceof JsonResult) {
            //如果是JsonResult，直接返回这个对象
            result = (JsonResult) obj;
        } else {
            //如果不是JsonResult，将返回的对象作为JsonResult中data的值
            result = new JsonResult(obj);
        }

        outputMessage.getHeaders().add("Content-Type", "application/json;charset=UTF-8"); //可以不加。 明确告知格式，方便格式化查看
        super.writeInternal(result, outputMessage);
    }
}