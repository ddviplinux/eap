package com.website.eap.webdriver;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

import java.io.Serializable;

/**
 * 
 * 浏览器环境句柄 zhizunbao
 * 
 */
public class Environment implements Serializable{
	private static final long serialVersionUID = -2078618555717211172L;

	private WebDriver driver;

	private Selenium selenium;

	public Environment(WebDriver driver, Selenium selenium) {
		this.driver = driver;
		this.selenium = selenium;

	}

	public WebDriver getDriver() {
		return driver;
	}

	public Selenium getSelenium() {
		return selenium;
	}
}
