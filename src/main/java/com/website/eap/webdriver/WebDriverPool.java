package com.website.eap.webdriver;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhizunbao
 * 
 */
@Service
public class WebDriverPool {
	
	private final Logger logger= LoggerFactory.getLogger(WebDriverPool.class);


	@Autowired
	private WebDriverCacheService webDriverCacheService;
	
	public SessionContext getWebDriverByPhone(String phone) {
        Object object=webDriverCacheService.get(phone);
        if (object==null){
            return null;
        }
		return (SessionContext)webDriverCacheService.get(phone);
		//return  (SessionContext)MemCacheUtil.get(CacheConstans.KEY_WEB_DRIVER+phone);
	}

	public void putWebDriver(String phone, SessionContext sessionContext) {
		webDriverCacheService.put(phone, sessionContext,CacheConstans.MEMCACHE_EXPIRE_TIME_WED_DRIVER);
		logger.info("[CACHE_ADD]add_key=" + phone);
		//MemCacheUtil.add(CacheConstans.KEY_WEB_DRIVER+phone, sessionContext,CacheConstans.MEMCACHE_EXPIRE_TIME_WED_DRIVER);

	}

	public void removeDriverByPhone(String phone) {
		if (StringUtils.isNotBlank(phone)) {
			SessionContext sessionContext=getWebDriverByPhone(phone);
				//MemCacheUtil.delete(CacheConstans.KEY_WEB_DRIVER+phone);
			webDriverCacheService.remove(phone);
			if (sessionContext!=null && sessionContext.getDriver() != null) {
				sessionContext.getDriver().quit();
			}
			logger.info("[CACHE_CLOSE]close_key=" + phone);
		}

	}
	
	/**
	 * 关闭当前浏览器
	 * @param actionContext
	 */
	public  void   closeAction(ActionContext actionContext){
		try {
			if (actionContext!=null){
				//关闭浏览器
				WebActionUtils.quit(actionContext.getSessionContext());
				//移除缓存句柄
				this.removeDriverByPhone(actionContext.getPhone());
			}
		} catch (Exception e) {
			logger.error("msg:"+actionContext+" error_msg:"+e);
		}
		
	}

}
