package cn.fo9c.educationaltourism.controller;

import cn.fo9c.educationaltourism.domain.User.User;
import cn.fo9c.educationaltourism.domain.User.UserDTO;
import cn.fo9c.educationaltourism.service.CookieService;
import cn.fo9c.educationaltourism.service.UserService;
import cn.fo9c.educationaltourism.utils.CookieUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CookieService cookieService;

    @GetMapping("/login")
    public Map<String, Boolean> UserLogin(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestBody UserDTO userDTO) {

        // 1、判断用户cookie是否存在
        if (cookieService.checkUserLoginCookie(request, userDTO.getId())) {
            return Map.of("status", true);
        }

        // 2、判断用户登录信息是否正确
        if (userService.UserLogin(User.builder().user_name(userDTO.getId()).user_password(userDTO.getPwd()).build())) {
            // 如果正确，设置cookie
            CookieUtils.setCookie(request, response, "user", userDTO.getId(), 60 * 60 * 24 * 7);
            return Map.of("status", true);
        } else {
            return Map.of("status", false);
        }
    }
}
