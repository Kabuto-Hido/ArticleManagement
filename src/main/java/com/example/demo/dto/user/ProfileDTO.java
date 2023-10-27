package com.example.demo.dto.user;

import com.example.demo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO extends BaseEntity {
    private String fullname;
    private String email;
    private String phone;
    private String avatar;
    private String gender;
    private String username;
    private String status;
    private String role;
    private String accountType;
}
