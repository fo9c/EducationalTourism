package cn.fo9c.educationaltourism.service.Impl;

import cn.fo9c.educationaltourism.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserService userService;

    public void login() {
        System.out.println("login");
    }
    public void register() {
        System.out.println("register");
    }
    public void logout() {
        System.out.println("logout");
    }
}
