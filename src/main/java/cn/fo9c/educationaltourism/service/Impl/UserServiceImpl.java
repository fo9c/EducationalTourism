package cn.fo9c.educationaltourism.service.Impl;

import cn.fo9c.educationaltourism.domain.User.User;
import cn.fo9c.educationaltourism.mapper.UserMapper;
import cn.fo9c.educationaltourism.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean UserLogin(User user) {
        // 1、获取用户信息
        User userSelect = userMapper.selectUserByName(user.getUser_name());

        // 2、判断用户信息是否正确
        // 如果正确，设置cookie
        return userSelect != null && userSelect.getUser_password().equals(user.getUser_password());
    }

    @Override
    public String getUserUUIDByUserName(String userName) {
        return userMapper.selectUserByName(userName).getUser_uuid();
    }

    @Override
    public Boolean UserRegister(User user) {
        if (userMapper.saveUser(user)==1)
            return true;
        else
            return false;
    }

}
