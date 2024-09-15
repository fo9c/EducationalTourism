package cn.fo9c.educationaltourism.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void login();
    void register();
    void logout();
}
