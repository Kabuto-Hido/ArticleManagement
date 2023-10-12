package com.example.demo.dto.post;

import com.example.demo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO extends BaseEntity {
    private Long id;
    private String title;
    private String desc;
    private String imagePost;
    private String image1;
    private String image2;
    private String likes = "0";
    private String dislikes = "0";
    private long view = 0;
    private String author;
    private String category;

    public PostResponseDTO() {
    }

    public PostResponseDTO(Long id, String title, String desc, String imagePost, String image1, String image2, long view,String author, String category) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.imagePost = imagePost;
        this.image1 = image1;
        this.image2 = image2;
        this.view = view;
        this.author = author;
        this.category = category;
    }

    public PostResponseDTO(String title, String desc, String category) {
        this.title = title;
        this.desc = desc;
        this.category = category;
    }

}
