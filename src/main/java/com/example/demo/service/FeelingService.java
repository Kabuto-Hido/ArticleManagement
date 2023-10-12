package com.example.demo.service;

import com.example.demo.dto.feeling.FeelingDTO;
import com.example.demo.dto.post.PostResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface FeelingService {
    PostResponseDTO createFeeling(FeelingDTO feelingDTO);
    FeelingDTO checkFeeling(long postId);
}
