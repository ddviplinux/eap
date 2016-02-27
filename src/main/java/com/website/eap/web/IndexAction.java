package com.website.eap.web;

import com.website.eap.common.web.BaseController;
import com.website.eap.webdriver.SessionContext;
import com.website.eap.webdriver.WebActionUtils;
import com.website.eap.webdriver.WebDriverFactory;
import com.website.eap.webdriver.WebDriverPool;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Launcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;

/**
 * User: zhizunbao
 * Date: 15/12/6
 * Time: 23:23
 * Desc:
 */
@Controller
public class IndexAction extends BaseController {

    @Resource
    private WebDriverPool webDriverPool;

    @Resource
    private WebDriverFactory webDriverFactory;

    @RequestMapping(value = "/login/{uid}", method = RequestMethod.GET)
    public ModelAndView login() {
        String view="/login";
        ModelAndView modelAndView=new ModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
        String view="/login";
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        try {
            SessionContext sessionContext=this.webDriverPool.getWebDriverByPhone(username);
            if (sessionContext==null){
                sessionContext= this.webDriverFactory.getSessionContext();
                webDriverPool.putWebDriver(username,sessionContext);
            }
            String jdLoginPage="https://passport.jd.com/new/login.aspx?ReturnUrl=http%3A%2F%2Fwww.jd.com%2F";
            WebActionUtils.startPage(sessionContext,jdLoginPage,null);

            WebElement loginNameElement=WebActionUtils.getElement(sessionContext, How.ID, "loginname");
            WebActionUtils.type(loginNameElement,username);

            WebElement nloginpwdElement=WebActionUtils.getElement(sessionContext, How.ID, "nloginpwd");
            WebActionUtils.type(nloginpwdElement,password);

            WebElement submitElement=WebActionUtils.getElement(sessionContext, How.ID, "loginsubmit");

            WebActionUtils.click(sessionContext, submitElement, new WebActionUtils.HowToWait(How.ID, "loginsubmit"));


            String order_xpath="//*[@id=\"shortcut-2014\"]/div/ul[2]/li[3]/div/a";

            WebElement orderElement=WebActionUtils.getElement(sessionContext, How.XPATH, order_xpath);

            WebActionUtils.click(sessionContext, orderElement,null);

            WebActionUtils.switchToLastWindow(sessionContext);

            String orderHtml=WebActionUtils.getHtml(sessionContext);

            FileOutputStream fileOutputStream=new FileOutputStream("/data/program/order.htm");
            IOUtils.write(orderHtml,fileOutputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView=new ModelAndView(view);
        return modelAndView;
    }
}
