package com.website.eap.webdriver;


import com.thoughtworks.selenium.Selenium;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 浏览器基本操作
 *
 * @author zhizunbao
 */


public class WebActionUtils {


    private static Logger logger = LoggerFactory.getLogger(WebActionUtils.class);

    public static final int SLEEP_MIN = 1;

    public static final int SLEEP_NORMAL = 5;

    public static final int SLEEP_MAX = 10;


    public enum WaitCondition {
        Wait_Visible, Wait_Clickable
    }

    public static class HowToWait {
        How _how;
        String _key;
        int _waitSeconds;
        WaitCondition _condition;

        public HowToWait(How how, String key) {
            this(how, key, WaitCondition.Wait_Visible, 30);
        }


        public HowToWait(How how, String key, WaitCondition condition,
                         int waitSencods) {
            _how = how;
            _key = key;
            _condition = condition;
            _waitSeconds = waitSencods;
        }

        public void waitDone(SessionContext sc) {
            if (_key == null || _key.length() <= 0) {
                sleep(_waitSeconds);
                return;
            }

            By by = null;
            switch (_how) {
                case XPATH:
                    by = By.xpath(_key);
                    break;
                case ID:
                    by = By.id(_key);
                    break;
                case NAME:
                    by = By.name(_key);
                    break;
                case CLASS_NAME:
                    by = By.className(_key);
                    break;
                case TAG_NAME:
                    by = By.tagName(_key);
                    break;
                default:
                    sleep(_waitSeconds);
                    return;
            }

            ExpectedCondition<WebElement> condition = null;
            switch (_condition) {
                case Wait_Visible:
                    condition = ExpectedConditions.visibilityOfElementLocated(by);
                    break;
                case Wait_Clickable:
                    condition = ExpectedConditions.elementToBeClickable(by);
                    break;
                default:
                    sleep(_waitSeconds);
                    return;
            }

            new WebDriverWait(sc.getDriver(), _waitSeconds)
                    .until(condition);
        }
    }

    /**
     * 根据不同 {@link org.openqa.selenium.support.How} 获取 {@link org.openqa.selenium.WebElement} 页面元素
     *
     * @param sc       {@link SessionContext}
     * @param _how     {@link org.openqa.selenium.support.How}
     * @param location {@link String}
     * @return
     */

    public static WebElement getElement(SessionContext sc, How _how, String location) {
        WebElement webElement = null;
        switch (_how) {
            case XPATH:
                webElement = sc.getDriver().findElement(By.xpath(location));
                break;
            case ID:
                webElement = sc.getDriver().findElement(By.id(location));
                break;
        }
        return webElement;

    }


    /**
     * 转向指定的URL页面
     */
    public static void startPage(SessionContext sc, String target, HowToWait wait) {
        WebDriver driver = sc.getDriver();
        logger.info("open url: " + target);
        driver.navigate().to(target);
        sc.getSelenium().windowMaximize();
        if (wait != null) {
            wait.waitDone(sc);
        }
    }

    public static void back(SessionContext sc) {
        sc.getDriver().navigate().back();
    }

    public static void forword(SessionContext sc) {
        sc.getDriver().navigate().forward();

    }

    /**
     * Text输入，先清除后输入
     */
    public static void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * 点击动作
     */
    public static void click(SessionContext sc, WebElement element, HowToWait wait) {
        element.click();
        if (wait != null) {
            wait.waitDone(sc);
        }
    }

    /**
     * 提交
     */
    public static void submit(SessionContext sc, WebElement element, HowToWait wait) {
        element.submit();
        if (wait != null) {
            wait.waitDone(sc);
        }
    }

    /**
     * 获取控件文本信息
     */
    public static String getText(WebElement element) {
        return element.getText();
    }

    /**
     * 选择下拉框（Select），通过索引下标
     */
    public static void select(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    /**
     * 选择下拉框（Select），通过选项值
     */
    public static void select(WebElement element, String optionValue) {
        Select select = new Select(element);
        select.selectByValue(optionValue);
    }

    /**
     * 获取下拉框值（Select）
     */
    public static String getSelectValue(WebElement element, int index) {
        Select select = new Select(element);
        return select.getOptions().get(index).getText();
    }

    /**
     * 获取当前选中的下拉框值（Select）
     */
    public static String getSelectdValue(WebElement element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getAttribute("value");
    }

    /**
     * 勾选多选框
     */
    public static void check(WebElement element) {
        element.click();
        sleep(1);
    }

    public static boolean isElementPresent(SessionContext sc, By by) {
        try {
            sc.getDriver().findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 给isElementPresent设置超时时间
     */
    public static boolean isElementPresent(SessionContext sc, By by, int time) {
        try {
            sc.getDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
            sc.getDriver().findElement(by);
            sc.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            sc.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return false;
        }
    }

    /**
     * 获取当前页面的URL
     */
    public static String getURL(SessionContext sc) {
        return sc.getDriver().getCurrentUrl();
    }

    /**
     * 刷新页面
     */
    public static void refresh(SessionContext sc) {
        sc.getDriver().navigate().refresh();
        sleep(1);
    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前页面的html
     */
    public static String getHtml(SessionContext sc) {
        return sc.getDriver().getPageSource();
    }

    /**
     * 切换到指定的frame
     */
    public static void switchToFrame(SessionContext sc, String frameName) {
        sc.getDriver().switchTo().frame(frameName);
    }

    /**
     * 切换到指定的frame
     */
    public static void switchToFrame(SessionContext sc, int index) {
        sc.getDriver().switchTo().frame(index);
    }

    /**
     * 切换到指定的frame
     */
    public static void switchToFrame(SessionContext sc, WebElement frame) {
        sc.getDriver().switchTo().frame(frame);
    }

    /**
     * 处理alert弹出窗口
     */
    public static void acceptAlert(SessionContext sc) {
        Alert alert = sc.getDriver().switchTo().alert();
        alert.accept();
        sleep(3);
    }

    /**
     * 处理alert弹出窗口
     */
    public static void cancelAlert(SessionContext sc) {
        Alert alert = sc.getDriver().switchTo().alert();
        alert.dismiss();
        sleep(3);
    }

    /**
     * 判断 alert 是否存在
     *
     * @param sc
     * @return
     */
    public static boolean isAlertPresent(SessionContext sc) {
        try {
            sc.getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    /**
     * 给 isAlertPresent 设置等待时间
     */
    public static boolean isAlertPresent(SessionContext sc, int RUN_COUNT) {
        boolean isLoad = false;
        for (int i = 0; i <= RUN_COUNT; i++) {
            isLoad = isAlertPresent(sc);
            try {
                if (!isLoad) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info(isLoad + "_" + i);
        }
        return isLoad;
    }

    /**
     * 获取 alert的内容
     *
     * @param sc
     * @return
     */
    public static String getAlertText(SessionContext sc) {
        Alert alert = sc.getDriver().switchTo().alert();
        return alert.getText();
    }

    /**
     * 切换到最后一个打开的窗口
     */
    public static void switchToLastWindow(SessionContext sc) {
        Object[] handles = sc.getDriver().getWindowHandles()
                .toArray();
        int id = handles.length - 1;
        sc.getDriver().switchTo().window(handles[id].toString());
    }

    /**
     * 执行当前window的js代码
     *
     * @param js
     */
    public static void runScript(SessionContext sc, String js) {
        sc.getSelenium().runScript(js);
    }

    /**
     * 触发webElement元素js事件
     *
     * @param id
     * @param event
     */
    public static void fireEvent(SessionContext sc, String id, String event) {
        sc.getSelenium().fireEvent(id, event);
    }

    /**
     * 关闭当前window
     */
    public static void close(SessionContext sc) {
        sc.getDriver().close();
    }

    /**
     * 关闭当前driver进程
     */
    public static void quit(SessionContext sc) {
        if (sc.getDriver() != null) {
            sc.getDriver().quit();
        }
    }

    /**
     * @param imageElement 验证码图片元素
     * @param imageType    图片类型
     * @return
     * @throws java.io.IOException
     */
    public static File validateImg(SessionContext sc, WebElement imageElement, String imageType) {
        try {
            File screen = ((TakesScreenshot) sc.getDriver())
                    .getScreenshotAs(CrawlerOutputType.FILE);


            Point p = imageElement.getLocation();

            int width = imageElement.getSize().getWidth();
            int height = imageElement.getSize().getHeight();

            Rectangle rect = new Rectangle(width, height);

            BufferedImage img = null;

            img = ImageIO.read(screen);

            BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,
                    rect.height);

            ImageIO.write(dest, imageType, screen);

            return screen;
        } catch (WebDriverException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean isElementShow(Selenium selenium, String location, int RUN_COUNT) {
        boolean isLoad = false;
        for (int i = 0; i <= RUN_COUNT; i++) {
            isLoad = selenium.isElementPresent(location);
            try {
                if (!isLoad) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(isLoad + "_" + i);
        }
        return isLoad;
    }

    public static boolean isElementShowByBy(Selenium selenium, String location, int RUN_COUNT) {
        boolean isLoad = false;
        for (int i = 0; i <= RUN_COUNT; i++) {
            int count = selenium.getXpathCount(location).intValue();
            try {
                if (count != 0) {
                    Thread.sleep(1000);
                } else {
                    isLoad = true;
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(isLoad + "_" + i);
        }
        return isLoad;
    }

    /**
     * 判断页面是否弹窗
     *
     * @param sc
     * @param runCnt
     * @return
     */
    public static boolean isAlertShow(SessionContext sc, int runCnt) {
        boolean isAlert = false;
        for (int i = 0; i <= runCnt; i++) {
            isAlert = isAlertPresent(sc);
            try {
                if (!isAlert) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(isAlert + "_" + i);
        }
        return isAlert;
    }

    /**
     * 验证元素是否可点击
     */
    public static boolean isElementVisible(SessionContext sc, String location, int RUN_COUNT) {
        boolean isLoad = false;
        for (int i = 0; i <= RUN_COUNT; i++) {
            isLoad = sc.getSelenium().isVisible(location);
            try {
                if (!isLoad) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info(isLoad + "_" + i);
        }
        return isLoad;
    }

    /**
     * 判断页面元素是否可见（有时候会有元素存在但不显示）
     */
    public static boolean isElementDisplayed(SessionContext sc, By by) {
        try {
            return sc.getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * 给isElementDisplayed设置等待时间
     */
    public static boolean isElementDisplayed(SessionContext sc, By by, int RUN_COUNT) {
        boolean isLoad = false;
        for (int i = 0; i <= RUN_COUNT; i++) {
            isLoad = isElementDisplayed(sc, by);
            try {
                if (!isLoad) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info(isLoad + "_" + i);
        }
        return isLoad;
    }

    /**
     * 等待元素加载，并出现某些内容
     *
     * @param sc
     * @param by
     * @param content2Wait
     * @param RUN_COUNT
     * @return
     */
    public static boolean isElementDisplayed(SessionContext sc, By by, String content2Wait, int RUN_COUNT) {
        boolean isLoad = false;
        for (int i = 0; i <= RUN_COUNT; i++) {
            isLoad = isElementDisplayed(sc, by);
            try {
                if (!(isLoad && StringUtils.contains(sc.getDriver().findElement(by).getText(), content2Wait))) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info(isLoad + "_" + i);
        }
        return isLoad;
    }


    public static void sleepUntilChange(SessionContext sc, int RUN_COUNT, String html1) {
        for (int i = 0; i <= RUN_COUNT; i++) {
            String html2 = WebActionUtils.getHtml(sc);
            try {
                if (html1.equals(html2)) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @param imageElement 验证码图片元素
     * @param imageType    图片类型
     * @return
     * @throws java.io.IOException
     */
    public static File validateFrameImg(SessionContext sc, WebElement imageElement, String fromStr, String imageType) {

        try {
            File screen = ((TakesScreenshot) sc.getDriver())
                    .getScreenshotAs(CrawlerOutputType.FILE);
            switchToLastWindow(sc);
            Point winp = sc.getDriver().findElement(By.id(fromStr)).getLocation();
            switchToFrame(sc, fromStr);
            Point p = imageElement.getLocation();

            int width = imageElement.getSize().getWidth();
            int height = imageElement.getSize().getHeight();

            Rectangle rect = new Rectangle(width, height);

            BufferedImage img = null;

            img = ImageIO.read(screen);

            BufferedImage dest = img.getSubimage(p.getX() + winp.getX(), p.getY() + winp.getY(), rect.width,
                    rect.height);

            ImageIO.write(dest, imageType, screen);

            return screen;
        } catch (WebDriverException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
