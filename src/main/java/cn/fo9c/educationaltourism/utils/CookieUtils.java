package cn.fo9c.educationaltourism.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public final class CookieUtils {
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        String retValue = null;

        // 获取请求中的所有 Cookie 并判断是否为空
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }

        // 遍历 Cookie 数组

        for (Cookie cookie : cookieList) {
            // 判断当前 Cookie 的名字是否与传入的名字相同
            if (cookie.getName().equals(cookieName)) {
                // 返回当前 Cookie 的值
                return cookie.getValue();
            }
        }

        return "getCookieValue";
    }

}
