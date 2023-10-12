package com.example.demo.service;

import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.user.ProfileDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UserService {
    ListOutputResult getListUser(String pageNumber, String pageSize);
    List<User> fetchAll();
    UserDTO getUserById(long id);
    ListOutputResult searchUser(String keyword, String page, String limit);
    ProfileDTO findByUsername(String username);
    @Transactional
    ProfileDTO changeProfile(ProfileDTO profileDTO, String username);
    Boolean uploadUserAvatar(MultipartFile avatar, String username);
    UserDTO save(UserDTO userDTO);
    void delete(long id);
    Integer deleteUserByName(String name);
    User findFirstByUsername(String username);
}
