package cn.fo9c.educationaltourism.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @GetMapping("/getCookie")
    public String getCookie(@CookieValue(value = "username",
            defaultValue = "Atta") String username ) {

        Cookie cookie = new Cookie("userid", "simple");
        cookie.setMaxAge(60 * 60 * 24);
        return "getCookie";
    }

    @GetMapping("/setCookie")
    public ResponseEntity<String> setCookie(HttpServletResponse response) {
        // 创建一个 Cookie
        Cookie cookie = new Cookie("myCookie", "cookieValue");
        cookie.setMaxAge(600); // 设置 Cookie 的过期时间为 1 小时
        response.addCookie(cookie);

        return new ResponseEntity<>("Cookie set successfully", HttpStatus.OK);
    }
}
