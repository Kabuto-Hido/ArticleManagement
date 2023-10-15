package com.example.demo.service;

import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.post.PostRequestDTO;
import com.example.demo.dto.post.PostResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PostService {
    PostResponseDTO createNew(PostRequestDTO postRequestDTO, MultipartFile imagePost,
                              MultipartFile image1, MultipartFile image2, String username);
    PostResponseDTO update(long id, PostRequestDTO postRequestDTO, MultipartFile imagePost,
                           MultipartFile image1, MultipartFile image2);
    ListOutputResult getListPost(String status, String page, String limit);
    ListOutputResult getPostByCategory(long categoryId, String status ,String page, String limit);
    ListOutputResult getPostByUser(long userId, String status, String page, String limit);
    PostResponseDTO getById(long id);
    Boolean changeToApprovedPosts(long id);
    Boolean changeToDenyPosts(long id);
    Boolean delete(long id);
    ListOutputResult searchUser(SearchRequestDTO requestDTO, String page, String limit);
}
