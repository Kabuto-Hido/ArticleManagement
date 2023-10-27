package com.example.demo.mapper;

import com.example.demo.dto.user.ProfileDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.entity.User;

public class UserMapper {

    public static UserDTO toUserDto (User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setRole(user.getUserRole().getName());
        userDTO.setId(user.getId());
        userDTO.setFullname(user.getFullName());
        userDTO.setUsername(user.getUsername());
        userDTO.setGender(user.getGender());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setPhone(user.getPhone());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setModifiedDate(user.getModifiedDate());
        userDTO.setStatus(user.getStatus());
        userDTO.setAccountType(user.getUserType().getTypename());
        return userDTO;
    }

    public static ProfileDTO toProfileDto (User user){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setRole(user.getUserRole().getName());
        profileDTO.setFullname(user.getFullName());
        profileDTO.setUsername(user.getUsername());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setGender(user.getGender());
        profileDTO.setAvatar(user.getAvatar());
        profileDTO.setPhone(user.getPhone());
        profileDTO.setCreatedDate(user.getCreatedDate());
        profileDTO.setModifiedDate(user.getModifiedDate());
        profileDTO.setStatus(user.getStatus());
        profileDTO.setAccountType(user.getUserType().getTypename());
        return profileDTO;
    }

    public static User toEntity (UserDTO userDTO){
        User user = new User();
//        user.setId(userDTO.getId());
        user.setFullName(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setAvatar(userDTO.getAvatar());
        user.setPhone(userDTO.getPhone());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
