/**
 * 
 */
package com.website.eap.webdriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhizunbao
 * action上下文基类
 */
public class ActionContext extends BaseObject{
//全站唯一用户id	
private  Long uid;
//站点名称 如 : zj,nj,xa
private String  siteName;
//动作类型  如:login,fetch
private String  actionType;
//动作名称 
private String  actionName;
//电话
private String phone;
//运营商编码
private String corpCode;
//省份 e.g. ZJ
private String provinceShortName;

private SessionContext sessionContext;
//号码归属地的省份
private String province;
//号码归属地的城市
private String city;



public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public SessionContext getSessionContext() {
	return sessionContext;
}
public void setSessionContext(SessionContext sessionContext) {
	this.sessionContext = sessionContext;
}
public String getCorpCode() {
	return corpCode;
}
public void setCorpCode(String corpCode) {
	this.corpCode = corpCode;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
//cookies
private  List<String> cookies =new ArrayList<String>();
//参数map
private  Map<String,String> paramMap=new HashMap<String,String>();

private Map<String, String> cdrConditionMap = new HashMap<String,String>();//189zj量身打造的动态参数map
public Long getUid() {
	return uid;
}
public void setUid(Long uid) {
	this.uid = uid;
}
public Map<String, String> getParamMap() {
	return paramMap;
}
public void setParamMap(Map<String, String> paramMap) {
	this.paramMap = paramMap;
}
public List<String> getCookies() {
	return cookies;
}

public  void  addCookies(List<String> cookies){
	this.cookies.addAll(cookies);
}
public void setCookies(List<String> cookies) {
	this.cookies = cookies;
}
public String getSiteName() {
	return siteName;
}
public void setSiteName(String siteName) {
	this.siteName = siteName;
}
public String getActionType() {
	return actionType;
}
public void setActionType(String actionType) {
	this.actionType = actionType;
}
public String getActionName() {
	return actionName;
}
public void setActionName(String actionName) {
	this.actionName = actionName;
}
/**
 * @return the cdrConditionMap
 */
public Map<String, String> getCdrConditionMap() {
	return cdrConditionMap;
}
/**
 * @param cdrConditionMap the cdrConditionMap to set
 */
public void setCdrConditionMap(Map<String, String> cdrConditionMap) {
	this.cdrConditionMap = cdrConditionMap;
}

	public String getProvinceShortName() {
		return provinceShortName;
	}

	public void setProvinceShortName(String provinceShortName) {
		this.provinceShortName = provinceShortName;
	}
}