package com.website.eap.webdriver;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/** 
 * zhizunbao
 * 创建WebDriver,初始化SessionContext
 */
@Service
public class WebDriverFactory  {
	
	private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

	@Autowired
	private WebDriverUtils webDriverUtils;

    public SessionContext getSessionContext() throws Exception{
    	logger.info("initializing webdriver context");
    	WebDriver driver = webDriverUtils.buildDriver();
    	logger.info("webdriver has been initializing");
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Selenium selenium = new WebDriverBackedSelenium(driver, "http://www.baidu.com");
		logger.info("selenium has been initializing");
		Environment env = new Environment(driver, selenium);
		logger.info("environment has been initializing");
		SessionContext sc=new SessionContext();
        sc.init(env);
		logger.info("initialized webdriver context successful");
        return sc;       
    }


    
}