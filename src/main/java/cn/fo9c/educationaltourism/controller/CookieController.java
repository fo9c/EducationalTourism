package cn.fo9c.educationaltourism.controller;

import cn.fo9c.educationaltourism.utils.CookieUtils;
import cn.fo9c.educationaltourism.utils.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class CookieController {

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request, @CookieValue(value = "myCookie",
            defaultValue = "Atta") String username ) {
        System.out.println("username = " + username);
        return CookieUtils.getCookieValue(request, "myCookie");
    }

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response, HttpServletRequest request) {


        // 创建一个 Cookie
        CookieUtils.setCookieValue(request, response,"myCookie", "m你好", 6);
        System.out.println("setCookie123");
        return "setCookie";
    }

    @GetMapping("/1")
    public Result<ArrayList> getCookie1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        return Result.success("success", list);
    }
}
