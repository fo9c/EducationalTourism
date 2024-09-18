package cn.fo9c.educationaltourism.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CookieService {
    /**
     * 检查Cookie的值
     * @param request 客户端请求
     * @param cookieName Cookie名称
     * @param checkValue 待验证的值
     * @return 用户的个人信息
     */
    Boolean getCookieValueByName(HttpServletRequest request, String cookieName, String checkValue);

    /**
     * 通过Cookie检查用户是否登录
     * @param request 客户端请求
     * @param userName 用户名
     * @return Cookie的值
     */
    Boolean checkUserLoginCookie(HttpServletRequest request, String userName);
}
