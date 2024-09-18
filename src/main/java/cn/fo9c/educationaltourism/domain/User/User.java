package cn.fo9c.educationaltourism.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    String user_uuid;           // 用户UUID（32位）
    String user_name;           // 用户名(通常是手机号)
    String user_password;
}
