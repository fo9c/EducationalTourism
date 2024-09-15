package cn.fo9c.educationaltourism.controller;

import cn.fo9c.educationaltourism.utils.CookieUtils;
import cn.fo9c.educationaltourism.utils.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request, @CookieValue(value = "username",
            defaultValue = "Atta") String username ) {

        return CookieUtils.getCookieValue(request, "myqCookie", false);
    }

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {


        // 创建一个 Cookie
        Cookie cookie = new Cookie("myCookie", "co45468e");
        cookie.setMaxAge(600); // 设置 Cookie 的过期时间为 1 小时
        response.addCookie(cookie);
        return "setCookie";
    }

    @GetMapping("/1")
    public Result<String> getCookie1() {
        return Result.success("getCookie1");
    }
}
