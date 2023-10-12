package com.example.demo.controller;

import com.example.demo.config.FeelingType;
import com.example.demo.dto.feeling.FeelingDTO;
import com.example.demo.service.FeelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FeelingController {
    @Autowired
    private FeelingService feelingService;

    @PostMapping("/feeling/createFeeling")
    public ResponseEntity<?> createFeeling(@RequestBody FeelingDTO model){
        return ResponseEntity.ok(feelingService.createFeeling(model));
    }

    //check user's feeling
    @GetMapping(path = "/feeling/checkFeeling", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkFeeling(@RequestParam long postId){
        Map<String, Object> map = new HashMap<>();
        FeelingDTO result = feelingService.checkFeeling(postId);
        if(result.getFeelingType() == null){
            map.put("LIKE", false);
            map.put("DISLIKE", false);
        }
        else {
            if (result.getFeelingType() == FeelingType.LIKE) {
                map.put("LIKE", true);
                map.put("DISLIKE", false);
            } else {
                map.put("LIKE", false);
                map.put("DISLIKE", true);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
