package com.website.eap.webdriver;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.Serializable;

/** *
 * 
 * 创建ThreadLocal，把Environment 对象放入ThreadLocal，
 * 保证driver和selenium在同一个线程里使用 * 
 *  zhizunbao
 */
public class SessionContext implements Serializable{

	private static final long serialVersionUID = -6088949592686886692L;

	
	private  Environment env;
	
	public  void init(Environment env) throws IOException{
		this.env=env;
	}
	 public  Environment getContext() throws Exception{
		 return env;
}
	
	 public  WebDriver getDriver() { 	         
	    return env.getDriver();
	    }
	 
	 public  Selenium getSelenium() {    
		     return env.getSelenium();
	    }
	 
	
	 public  void close(){			
		 WebDriver currentDriver = getDriver();			
		 if(currentDriver != null){			
			 currentDriver.close();			
			}		

	 }
}
