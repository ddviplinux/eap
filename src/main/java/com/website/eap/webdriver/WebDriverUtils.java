package com.website.eap.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author zhizunbao
 *         <p/>
 *         webDriver 实例化封装
 */
@Service
public class WebDriverUtils {

	private static WebDriver driver = null;

	@Value("${driver.path}")
	private String driverPath;

	@Value("${bin.path}")
	private String binPath;

	@Value("${webdriver.log.path}")
	private String logPath;

	@Value("${webdriver.proxy.urls}")
	private String proxyUrls;

	private final static String PROXY_FUDI = "192.168.200.11:9999";

	private final static String PROXY_XIAOSHAN = "192.168.49.11:8888";

	private List<String> proxyList;

	public WebDriver buildDriver() {
		return buildDriver(null);
	}

	public WebDriver buildDriver(String proxy) {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// 代理
		ChromeOptions options = new ChromeOptions();
		if (proxy != null) {
			options.addArguments("--proxy-server=socks5://" + proxy);
		}
		options.addArguments("start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		System.setProperty("webdriver.chrome.logfile", logPath); // webdriver  log for debug
		// 设置chrome驱动路径
		capabilities.setCapability("chrome.binary", binPath);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(capabilities);
		return driver;
	}

	/**
	 * 福地：192.168.200.11:8888 萧山：192.168.49.11:8888
	 * 
	 * @return
	 */
	private String getRandomProxy() {
		Random random = new Random();
		return getProxyList().isEmpty() ? null : getProxyList().get(Math.abs(random.nextInt()) % proxyList.size());
	}

	private String[] getProxyUrls() {
		return proxyUrls == null ? null : proxyUrls.split(",");
	}

	public List<String> getProxyList() {
		if (proxyList == null) {
			proxyList = new ArrayList<String>();
			String[] urls = getProxyUrls();
			if (urls != null) {
				String regex = "(\\w+\\.)+\\w+:\\d+";
				Pattern p = Pattern.compile(regex);
				for (int i = 0; i < urls.length; i++) {
					if (p.matcher(urls[i]).matches()) {
						proxyList.add(urls[i]);
					}
				}
			}
		}
		return proxyList;
	}
}
