package com.example.demo.service.impl;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.config.FeelingType;
import com.example.demo.dto.feeling.FeelingDTO;
import com.example.demo.dto.post.PostResponseDTO;
import com.example.demo.entity.Feeling;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.FeelingMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.FeelingRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FeelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class FeelingServiceImpl implements FeelingService {
    @Autowired
    private FeelingRepository feelingRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PostResponseDTO createFeeling(FeelingDTO feelingDTO) {
        Post post = postRepository.findById(feelingDTO.getPostId()).orElseThrow(() ->
                new NotFoundException("Not found post with id "+feelingDTO.getPostId()));

        String username = ApplicationUserService.GetUsernameLoggedIn();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));

        Feeling feeling = feelingRepository.findOneByUserIdIdAndPostIdId(user.getId(), feelingDTO.getPostId());

        //if no feeling -> new feeling
        if(feeling == null){
            feeling = FeelingMapper.toEntity(feelingDTO);
            feeling.setPostId(post);
            feeling.setUserId(user);
            feelingRepository.save(feeling);
        }
        else{
            if(feeling.getType().equals(feelingDTO.getFeelingType())){ //feeling type = type input -> delete
                feelingRepository.delete(feeling);
            }
            else{//change feeling type
                feeling.setType(feelingDTO.getFeelingType());
                feelingRepository.save(feeling);
            }
        }

        return getDetailPost(post);
    }

    @Override
    public FeelingDTO checkFeeling(long postId) {
        postRepository.findById(postId).orElseThrow(() ->
                new NotFoundException("Not found post with id "+postId));

        String username = ApplicationUserService.GetUsernameLoggedIn();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));

        Feeling feeling = feelingRepository.findOneByUserIdIdAndPostIdId(user.getId(), postId);
        FeelingDTO feelingDTO;
        if(feeling == null){
            feelingDTO = new FeelingDTO();
        }
        else {
            feelingDTO = FeelingMapper.toDto(feeling);
        }

        return feelingDTO;
    }

    public PostResponseDTO getDetailPost(Post p){
        PostResponseDTO postResponseDTO = PostMapper.toDto(p);
        long amountLike = feelingRepository.countByPostIdIdAndType(p.getId(), FeelingType.LIKE);
        long amountDislike = feelingRepository.countByPostIdIdAndType(p.getId(), FeelingType.DISLIKE);
        postResponseDTO.setLikes(prettyCount(amountLike));
        postResponseDTO.setDislikes(prettyCount(amountDislike));
        return postResponseDTO;
    }
    public String prettyCount(Number number) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }
}
