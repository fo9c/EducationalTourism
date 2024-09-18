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

    /**
     * 获取 Cookie 的值
     * @param request 服务端 HTTP 请求值
     * @param username 需要查找的 Cookie 名称
     * @return Cookie 的值
     */
    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request,
                            @CookieValue(value = "myCookie1", defaultValue = "Atta") String username ) {
        System.out.println("username = " + username);
        return CookieUtils.getCookieValue(request, "myCookie1");
    }

    /**
     * 设置 Cookie 的值
     * @param response 客户端 HTTP 响应值
     * @param request 服务端 HTTP 请求值
     * @return 设置 Cookie 的值
     */
    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response, HttpServletRequest request) {
        CookieUtils.setCookieValue(request, response,"myCookie1", "m你 好135&*", 600000);
        CookieUtils.setCookieValue(request, response,"myCookie2", "m你 好135&*", 600000);
        System.out.println("setCookie.....");
        return "setCookie";
    }


    /**
     * 删除所有 Cookie 的值
     * @param response 客户端 HTTP 响应值
     * @param request 服务端 HTTP 请求值
     * @return 删除 Cookie 的值
     */
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
