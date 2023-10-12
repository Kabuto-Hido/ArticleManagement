package com.example.demo.dto.feeling;

import com.example.demo.config.FeelingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeelingDTO {
    private FeelingType feelingType;
    private long postId;
}
