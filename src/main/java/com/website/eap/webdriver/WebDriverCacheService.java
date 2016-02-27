package com.website.eap.webdriver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author zhizunbao
 *
 */
@Service
public class WebDriverCacheService implements ICache<String,Object>
{
	private static final Log logger = LogFactory.getLog(WebDriverCacheService.class);
	
	/**
	 * 具体内容存放的地方
	 */
	public  ConcurrentHashMap<String, Object>[] caches;
	/**
	 * 超期信息存储
	 */
	public  ConcurrentHashMap<String, Long> expiryCache;
	
	/**
	 * 清理超期内容的服务
	 */
	private  ScheduledExecutorService scheduleService;
	
	/**
	 * 清理超期信息的时间间隔，默认1分钟
	 */
	private int expiryInterval = 1;
	
	/**
	 * 内部cache的个数，根据key的hash对module取模来定位到具体的某一个内部的Map，
	 * 减小阻塞情况发生。
	 */
	private int moduleSize = 10;

    public WebDriverCacheService(){
        init();
    }

	@SuppressWarnings("unchecked")
	private void init()
	{
		caches = new ConcurrentHashMap[moduleSize];
		
		for(int i = 0 ; i < moduleSize ;i ++)
			caches[i] = new ConcurrentHashMap<String, Object>();
		
		expiryCache = new ConcurrentHashMap<String, Long>();
		
		scheduleService = Executors.newScheduledThreadPool(1);

		scheduleService.scheduleAtFixedRate(new CheckOutOfDateSchedule(caches,expiryCache),
				0, expiryInterval * 60 , TimeUnit.SECONDS);
		
		if (logger.isInfoEnabled())
			logger.info("DefaultCache CheckService is start!");
	}
	
	@Override
	public boolean clear()
	{
		if (caches != null)
			for(ConcurrentHashMap<String, Object> cache : caches)
			{
				cache.clear();
			}
		
		if (expiryCache != null)
			expiryCache.clear();
		
		return true;
	}


	@Override
	public boolean containsKey(String key)
	{
		checkValidate(key);
		return getCache(key).containsKey(key);
	}


	@Override
	public Object get(String key)
	{
		checkValidate(key);
		return getCache(key).get(key);
	}

	
	@Override
	public Set<String> keySet()
	{
		checkAll();
		return expiryCache.keySet();
	}


	@Override
	public Object put(String key, Object value)
	{
		Object result = getCache(key).put(key, value);
		expiryCache.put(key,(long)-1);
		
		return result;
	}

	@Override
	public Object put(String key, Object value, Date expiry)
	{
		Object result = getCache(key).put(key, value);
		expiryCache.put(key,expiry.getTime());
		
		return result;
	}


	@Override
	public Object remove(String key)
	{
		Object result = getCache(key).remove(key);
		expiryCache.remove(key);
		return result;
	}


	@Override
	public int size()
	{
		checkAll();
		
		return expiryCache.size();
	}


	@Override
	public Collection<Object> values()
	{
		checkAll();
		
		Collection<Object> values = new ArrayList<Object>();
		
		for(ConcurrentHashMap<String, Object> cache : caches)
		{
			values.addAll(cache.values());	
		}

		return values;
	}
	
	private ConcurrentHashMap<String, Object>getCache(String key)
	{
		long hashCode = key.hashCode();
		
		if (hashCode < 0)
			hashCode = -hashCode;
		
		int moudleNum = (int)hashCode % moduleSize;
		
		return caches[moudleNum];
	}
	
	private void checkValidate(String key)
	{
		if (key != null && expiryCache.get(key) != null && expiryCache.get(key) != -1 
				&& new Date(expiryCache.get(key)).before(new Date()))
		{
			Object object=getCache(key).remove(key);
			if (object!=null){
				SessionContext sc=(SessionContext)object;
				 sc.getDriver().quit();
			}
		    expiryCache.remove(key);
		}
	}
	
	private void checkAll()
	{
		Iterator<String> iter = expiryCache.keySet().iterator();
		
		while(iter.hasNext())
		{
			String key =  iter.next();
			checkValidate(key);
		}
	}
	
	class CheckOutOfDateSchedule implements Runnable
	{
		/**
		 * 具体内容存放的地方
		 */
		ConcurrentHashMap<String, Object>[] caches;
		/**
		 * 超期信息存储
		 */
		ConcurrentHashMap<String, Long> expiryCache;

		public CheckOutOfDateSchedule(ConcurrentHashMap<String, Object>[] caches,
				ConcurrentHashMap<String, Long> expiryCache)
		{
			this.caches = caches;
			this.expiryCache = expiryCache;
		}
		

		@Override
		public void run()
		{
			check();
		}
		
		public void check()
		{
			try
			{
				for(ConcurrentHashMap<String, Object> cache : caches)
				{
					Iterator<String> keys = cache.keySet().iterator();
					
					while(keys.hasNext())
					{
						String key = keys.next();
						
						if (expiryCache.get(key) == null)
							continue;
						
						long date = expiryCache.get(key);
						
						if ((date > 0)&&(new Date(date).before(new Date())))
						{
							expiryCache.remove(key);
							Object object=cache.remove(key);
							if (object!=null){
								SessionContext sc=(SessionContext)object;
								 sc.getDriver().quit();
								logger.info("[CACHE_REMOVE]remove_key=" + key);
							}
						}
					}
				}
			}
			catch(Exception ex)
			{
				logger.error("DefaultCache CheckService is aborted!", ex);

			}
		}
		
	}

	
	@Override
	public Object put(String key, Object value, int TTL)
	{
		Object result = getCache(key).put(key, value);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, TTL);
		expiryCache.put(key,calendar.getTime().getTime());
		
		return result;
	}

	@Override
	public void destroy() 
	{
		try
		{
			clear();
			
			if (scheduleService != null)
				scheduleService.shutdown();
			
			scheduleService = null;
		}
		catch(Exception ex)
		{
			logger.error(ex);
		}
	}

}