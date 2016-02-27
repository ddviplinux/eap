package com.website.eap.web;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zhizunbao
 * Date: 16/1/12
 * Time: 16:13
 * Desc:
 */
@RestController
@RequestMapping("/router")
@Api(value = "路由接口",description = "路由api",basePath ="/roter")
public class RestApiAction {

    @RequestMapping("/get")
    @ApiOperation(value = "获取信息",notes = "get获取信息")
    public String   get(){
        return "json";
    }

    @RequestMapping("/list")
    @ApiOperation(value = "列表信息",notes = "list列表信息")
    public List<String> list(){
        List<String> arrayList=new ArrayList<String>();
        return arrayList;
    }
}
