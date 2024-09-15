package cn.fo9c.educationaltourism.service.Impl;

import cn.fo9c.educationaltourism.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserService userService;

    @Override
    public void login() {
        userService.login();
    }

    @Override
    public void register() {
        userService.register();
    }

    @Override
    public void logout() {
        userService.logout();
    }

}
