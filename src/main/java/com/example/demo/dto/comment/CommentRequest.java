package com.example.demo.dto.comment;

import com.example.demo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest extends BaseEntity {
    private Long id;
    private String text;
    private Long parentId;
    private Long postId;

}
