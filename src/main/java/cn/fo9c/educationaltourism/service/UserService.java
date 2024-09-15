package cn.fo9c.educationaltourism.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    void login();
    void register();
    void logout();
}
