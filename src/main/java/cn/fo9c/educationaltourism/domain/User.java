package cn.fo9c.educationaltourism.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String user_name;
    String user_password;
}
