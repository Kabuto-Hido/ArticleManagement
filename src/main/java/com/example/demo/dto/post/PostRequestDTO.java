package com.example.demo.dto.post;

import com.example.demo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO extends BaseEntity {
    private String title;
    private String desc;
    private Long categoryId;

}
