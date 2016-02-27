package com.website.eap.common.web;

/**
 * User: zhizunbao
 * Date: 15/12/6
 * Time: 23:20
 * Desc:
 */
public class JsonResult {
    /**
     * 请求有错误提示
     */
    public static final int STATUS_FAIL = 0;
    /**
     * 请求成功
     */
    public static final int STATUS_SUCCESS = 1;
    /**
     * 未登录
     */
    public static final int STATUS_NOT_LOGIN = -1;

    private int status = 1;
    private String msg;
    private Object data;
    private Object key;
    private String href;

    public JsonResult() {
    }

    public JsonResult(String href) {
        this.href = href;
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JsonResult(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
