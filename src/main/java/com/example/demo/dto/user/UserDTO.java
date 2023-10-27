package com.example.demo.dto.user;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.Role;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseEntity {
    private Long id;
    private String fullname;
    private String email;
    private String phone;
    private String avatar;
    private String username;
    private String password;
    private String gender;
    private String status;
    private String role;
    private String accountType;

    public UserDTO(String fullname, String email, String phone, String username, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.gender = gender;
    }
}
