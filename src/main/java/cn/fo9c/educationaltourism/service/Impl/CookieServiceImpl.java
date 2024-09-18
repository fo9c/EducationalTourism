package cn.fo9c.educationaltourism.service.Impl;

import cn.fo9c.educationaltourism.service.CookieService;
import cn.fo9c.educationaltourism.service.UserService;
import cn.fo9c.educationaltourism.utils.CookieUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CookieServiceImpl implements CookieService {

    private static final String LOGIN_COOKIE_CHECK_NAME = "userUUID";

    @Resource
    private UserService userService;



    @Override
    public Boolean getCookieValueByName(HttpServletRequest request, String cookieName, String checkValue) {
        if (request == null || cookieName == null || checkValue == null) {
            return false;
        }
        return Objects.equals(CookieUtils.getCookieValue(request, cookieName), checkValue);
    }



    @Override
    public Boolean checkUserLoginCookie(HttpServletRequest request, String userName) {
        // 1、判断非空
        if (request == null || userName == null) {
            return false;
        }
        // 2、获取用户UUID
        String userUUID = userService.getUserUUIDByUserName(userName);

        // 3、判断用户UUID是否相同
        return Objects.equals(CookieUtils.getCookieValue(request, LOGIN_COOKIE_CHECK_NAME), userUUID);
    }
}
