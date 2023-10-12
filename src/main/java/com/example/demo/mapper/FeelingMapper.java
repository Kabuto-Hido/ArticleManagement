package com.example.demo.mapper;

import com.example.demo.dto.feeling.FeelingDTO;
import com.example.demo.entity.Feeling;

public class FeelingMapper {
    public static Feeling toEntity(FeelingDTO feelingDTO){
        Feeling feeling = new Feeling();
        feeling.setType(feelingDTO.getFeelingType());
        return feeling;
    }

    public static FeelingDTO toDto(Feeling feeling){
        FeelingDTO feelingDTO = new FeelingDTO();
        feelingDTO.setFeelingType(feeling.getType());
        feelingDTO.setPostId(feeling.getPostId().getId());
        return feelingDTO;
    }
}
