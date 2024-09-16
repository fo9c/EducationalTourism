package cn.fo9c.educationaltourism.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class CookieUtils{
    // Cookie 编码的字符集（默认为 UTF-8）
    private final static Charset cookieEncoding = StandardCharsets.UTF_8;
    // Cookie 是否进行URL编码
    private final static boolean isDecoder = true;

    /**
     * 获取 Cookie 的值
     * @param request 服务端 HTTP 请求值
     * @param cookieName 需要查找的 Cookie 名称
     * @return Cookie 的值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {

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
     *
     * @param request      服务端 HTTP 请求值
     * @param response     客户端 HTTP 响应值
     * @param cookieName   Cookie 名称
     * @param cookieValue  Cookie 的值
     * @param cookieMaxAge Cookie 的最大持续时间（单位：秒）
     */
    public static void setCookieValue(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge) {
        try {
            Cookie cookie = new Cookie(cookieName, null);

            // 设置 Cookie 的最大持续时间
            cookie.setMaxAge(cookieMaxAge);

            // 设置 Cookie 的客户端路径
            if (request.getContextPath() != null){
                cookie.setPath(request.getContextPath());
            }

            // 设置 Cookie 的域名
            cookie.setDomain(request.getServerName());

            // 对 Cookie Value 进行 URL 编码
            if (isDecoder){
                cookie.setValue(URLEncoder.encode(cookieValue, cookieEncoding));
            }else {
                cookie.setValue(cookieValue);
            }

            response.addCookie(cookie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除 Cookie 的值（将 Cookie 的值设置为空并设置最大持续时间为 0）
     *
     * @param request      服务端 HTTP 请求值
     * @param response     客户端 HTTP 响应值
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取请求中的所有 Cookie
            Cookie[] cookieList = request.getCookies();
            if (cookieList == null) {
                return;
            }

            // 遍历 Cookie 数组
            for (Cookie cookie : cookieList) {
                Cookie newCookie = new Cookie(cookie.getName(), null);
                newCookie.setMaxAge(0);
                response.addCookie(newCookie);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
