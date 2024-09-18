package cn.fo9c.educationaltourism.controller;

import cn.fo9c.educationaltourism.service.CookieService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    private CookieService cookieService;

    @GetMapping("/")
    public String test(HttpServletRequest request) {
        return "test";
    }
}
