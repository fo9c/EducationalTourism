package cn.fo9c.educationaltourism.service.Impl;

import cn.fo9c.educationaltourism.mapper.UserMapper;
import cn.fo9c.educationaltourism.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void login() {
    }

    @Override
    public void register() {
    }

    @Override
    public void logout() {
    }

}
