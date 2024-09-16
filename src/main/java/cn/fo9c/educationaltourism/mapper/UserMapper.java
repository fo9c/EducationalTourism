package cn.fo9c.educationaltourism.mapper;

import cn.fo9c.educationaltourism.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 通过用户ID获取用户个人信息
     * @param userUUID 用户的UUID
     * @return 用户的个人信息
     */
    @Select("select * from user where user_uuid = #{userUUID}")
    User getUserInfoByUUID(String userUUID);

}
