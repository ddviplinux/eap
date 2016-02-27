package com.website.eap.web;

import com.website.eap.common.web.BaseController;
import com.website.eap.crawler.model.MovieEntity;
import com.website.eap.crawler.storage.HBaseDataApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * User: zhizunbao
 * Date: 15/12/24
 * Time: 15:24
 * Desc:
 */
@Controller
public class CrawlerAction extends BaseController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        String view="/search";
        HttpSession session=request.getSession();
        session.setAttribute("userId",new Object());
        ModelAndView modelAndView=new ModelAndView(view);
        try {
            HBaseDataApi hBaseDataApi=new HBaseDataApi();
            List<MovieEntity> movieEntityList= hBaseDataApi.queryData("wac-crawler-douban");
            modelAndView.addObject("movieList",movieEntityList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
