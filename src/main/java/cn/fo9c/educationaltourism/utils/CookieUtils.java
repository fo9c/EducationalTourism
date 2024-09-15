package cn.fo9c.educationaltourism.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class CookieUtils{
    // Cookie 编码的字符集（默认为 UTF-8）
    private final static Charset cookieEncoding = StandardCharsets.UTF_8;

    /**
     * 获取 Cookie 的值
     * @param request HTTP 请求值
     * @param cookieName 需要查找的 Cookie 名称
     * @param isDecoder 是否解码
     *                  true: 解码
     *                  false: 不解码
     * @return Cookie 的值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {

        // 获取请求中的所有 Cookie 并判断是否为空
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }

        // 遍历 Cookie 数组
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    if (isDecoder) {
                        return URLDecoder.decode(cookie.getValue(), cookieEncoding);
                    } else {
                        return cookie.getValue();
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /**
     * 设置 Cookie 的值
     * @param request HTTP 请求值
     * @param cookieName Cookie 名称
     * @param cookieValue Cookie 的值
     * @param cookieMaxAge Cookie 的最大持续时间（单位：秒）
     * @param isDecoder 是否编码
     *                  true: 编码
     *                  false: 不编码
     * @return Cookie 的值
     */
    public static void setCookieValue(HttpServletRequest request, String cookieName, String cookieValue, int cookieMaxAge, boolean isDecoder) {
        try {
            Cookie cookie = new Cookie(cookieName, isDecoder ? URLDecoder.decode(cookieValue, cookieEncoding) : cookieValue);
            cookie.setMaxAge(cookieMaxAge);
            request.setAttribute(cookieName, cookie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
