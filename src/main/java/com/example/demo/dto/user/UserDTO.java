package com.example.demo.dto.user;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public UserDTO() {
    }

    public UserDTO(Long id, String fullname, String email, String phone, String avatar, String username, String password, String gender, String status, String role) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.role = role;
    }

    public UserDTO(String fullname, String email, String phone, String username, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.gender = gender;
    }
}
