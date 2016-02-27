package com.website.eap.webdriver;

/**
 * @author zhizunbao
 */
public class CacheConstans {
    /*登陆cache key*/
    public static final String KEY_LOGIN = "login";

    /*账户列表 cache key*/
    public static final String KEY_ACCOUNT = "account";

    /*拉取数据月份最大缓存 cache key*/
    public static final String KEY_CELL = "cell";

    /*拉取任务缓存 cache key*/
    public static final String KEY_CELL_VISIT = "cell_visit";

    /*web driver cache key*/
    public static final String KEY_WEB_DRIVER = "web_driver";

    //保存{phoneNum+uid：aid}的失效时间
    public static final int MEMCACHE_EXPIRE_TIME_IN_SECONDS = 3600 * 24 * 5; // Memcache失效时间：5days

    /* login cache 登陆失效时间*/
    public static final int MEMCACHE_EXPIRE_TIME_LOGIN = 30 * 60 * 60;  //登陆失效时间 30小时

    /*web driver 缓存失效时间*/
    public static final int MEMCACHE_EXPIRE_TIME_WED_DRIVER = 30 * 60; //web driver缓存时间 30分钟

    public static final int REDIS_EXPIRE_TIME_IN_SECONDS = 60 * 60 * 24 * 30; // 抓取保存的文件存放在redis的失效时间 30天以供信用管家查看文件

    public static final int PARSE_FILE_LOCK_TIMEOUT = 60 * 10; // 解析文件锁定时间，10min

    public static final String KEY_FAILED_CELL_VISIT_MONTH = "failed_cell"; // 抓取失败判断前缀

    public static final int PARSE_FAILED_CELL_VISIT_LOCK_TIME = 60 * 30; // 抓取判断失效时间，30分钟

}
