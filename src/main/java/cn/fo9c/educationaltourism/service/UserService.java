package cn.fo9c.educationaltourism.service;

import cn.fo9c.educationaltourism.domain.User.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    /**
     * 用户登录
     * @param user 用户登录数据
     * @return 用户UUID
     */
    Boolean UserLogin(User user);

    /**
     * 通过用户名获取用户UUID
     * @param userName 用户名
     * @return 用户UUID
     */
    String getUserUUIDByUserName(String userName);

    Boolean UserRegister(User user);
}
