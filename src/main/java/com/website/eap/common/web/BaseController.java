package com.website.eap.common.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: zhizunbao
 * Date: 15/12/6
 * Time: 23:27
 * Desc:
 */
public class BaseController {
    /**
     * 失败场景输出客户端json
     */
    public JsonResult outputJSONError(String msg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(0);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

    /**
     *
     *
     * @return
     */
    public Map<String, Object> getJsonSuccMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 1);
        map.put("msg", "");
        return map;
    }

    /**
     * 成功场景输出客户端json
     */
    public JsonResult outputJSONSucc(Object info) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(1);
        if (info != null) {
            jsonResult.setData(info);
        }
        return jsonResult;
    }

    /**
     * 成功场景输出客户端json
     */
    public JsonResult outputJsonHref(String href) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(1);
        if ( href != null) {
            jsonResult.setHref(href);
        }
        return jsonResult;
    }

    /**
     * 成功场景输出客户端json
     */
    public JsonResult outputJSONInter(Map<String, Object> map, List<?> infos) {
        if (infos==null || infos.size()==0) {
            return outputJSONSucc(map);
        } else {
            JSONArray jsonArray = new JSONArray();
            for (Object info : infos) {
                jsonArray.add(JSONObject.toJSON(info));
            }
            map.put("data", jsonArray);
            return outputJSONSucc(map);
        }
    }


}
