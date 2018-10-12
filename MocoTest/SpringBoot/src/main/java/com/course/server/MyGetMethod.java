package com.course.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController         //待扫描的类 //等于@Controller和@ResponseBody注解
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest      装请求信息的类
        //HttpServerletResponse     装相应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获取cookies成功";
    }

    /**
     * 要求客户端写待cookies访问
     * 这是一个需要写待cookies信息才能访问的get请求
     */

    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    public String getwithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "你必须携带cookies信息来";
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("login")&&      //equaks用于做两值比较
                    cookie.getValue().equals("true")){
                return "恭喜你访问成功";
            }
        }
        return "你必须携带cookies信息来";
    }
}

