package cn.fo9c.educationaltourism.controller;

import cn.fo9c.educationaltourism.utils.CookieUtils;
import cn.fo9c.educationaltourism.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CookieController {

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request, @CookieValue(value = "myCookie1",
            defaultValue = "Atta") String username ) {
        System.out.println("username = " + username);
        return CookieUtils.getCookieValue(request, "myCookie1");
    }

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response, HttpServletRequest request) {


        // 创建一个 Cookie
        CookieUtils.setCookieValue(request, response,"myCookie1", "m你 好135&*", 600000);
        CookieUtils.setCookieValue(request, response,"myCookie2", "m你 好135&*", 600000);


        System.out.println("setCookie123");
        return "setCookie";
    }

    @GetMapping("/deleteCookie")
    public String deleteCookie(HttpServletResponse response, HttpServletRequest request) {
        CookieUtils.deleteCookie(request, response);
        return "deleteCookie";
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
