package com.example.demo.controller;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.dto.SuccessResponseDTO;
import com.example.demo.dto.comment.CommentRequest;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/createNew")
    public ResponseEntity<?> addCommentToPost(@RequestBody CommentRequest model) {
        String username = ApplicationUserService.GetUsernameLoggedIn();
        if (commentService.save(model, username) != null) {
            return ResponseEntity.ok(new SuccessResponseDTO(
                    HttpStatus.OK,
                    "Create comment successful"));
        } else {
            return ResponseEntity.badRequest().body("Create comment failure");
        }
    }

    @PutMapping("/comment/edit/{id}")
    public ResponseEntity<?> editComment(@RequestBody CommentRequest model, @PathVariable long id) {
        String username = ApplicationUserService.GetUsernameLoggedIn();
        model.setId(id);
        return ResponseEntity.ok(commentService.save(model, username));
    }

    @GetMapping("/comment/get-all")
    public ResponseEntity<?> getByPostId(@RequestParam long postId,
                                         @RequestParam Boolean paging,
                                         @RequestParam(required = false) String page,
                                         @RequestParam(required = false) String limit) {
        if(paging){
            return ResponseEntity.ok(commentService.getPagingByPostId(postId, page, limit));
        }else{
            return ResponseEntity.ok(commentService.getByPostId(postId));
        }
    }

    @GetMapping("/comment/getBy")
    public ResponseEntity<?> getByParentId(@RequestParam long parentId,
                                           @RequestParam Boolean paging,
                                           @RequestParam(required = false) String page,
                                           @RequestParam(required = false) String limit) {
        if(paging){
            return ResponseEntity.ok(commentService.getPagingByParentId(parentId, page, limit));
        }else{
            return ResponseEntity.ok(commentService.getByParentId(parentId));
        }

    }
}
