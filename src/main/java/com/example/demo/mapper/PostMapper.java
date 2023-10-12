package com.example.demo.mapper;

import com.example.demo.dto.post.PostRequestDTO;
import com.example.demo.dto.post.PostResponseDTO;
import com.example.demo.entity.Post;

public class PostMapper {
    public static Post toEntity(PostRequestDTO postRequestDTO){
        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setDesc(postRequestDTO.getDesc());
        return post;
    }
    public static PostResponseDTO toDto(Post post){
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setImagePost(post.getImagePost());
        postResponseDTO.setId(post.getId());
        postResponseDTO.setDesc(post.getDesc());
        postResponseDTO.setTitle(post.getTitle());
        postResponseDTO.setImage1(post.getImage1());
        postResponseDTO.setImage2(post.getImage2());
        postResponseDTO.setView(post.getView());
        postResponseDTO.setAuthor(post.getUserId().getUsername());
        postResponseDTO.setCategory(post.getCategoryId().getName());
        postResponseDTO.setCreatedDate(post.getCreatedDate());
        postResponseDTO.setModifiedDate(post.getModifiedDate());
        return postResponseDTO;
    }
}
