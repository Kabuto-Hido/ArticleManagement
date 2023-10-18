package com.example.demo.controller;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.post.PostRequestDTO;
import com.example.demo.dto.SuccessResponseDTO;
import com.example.demo.service.PostService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@RestController
@Api
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post/createNew")
    public ResponseEntity<?> createNewPost(@RequestParam(value = "imagePost",required = false) MultipartFile imagePost,
                                           @RequestParam(value = "image1",required = false) MultipartFile image1,
                                           @RequestParam(value = "image2",required = false) MultipartFile image2,
                                           @RequestParam(value = "title") String title,
                                           @RequestParam(value = "desc") String desc,
                                           @RequestParam(value = "categoryId") Long categoryId){
        PostRequestDTO model = new PostRequestDTO(title,desc,categoryId);
        String username = ApplicationUserService.GetUsernameLoggedIn();
        return ResponseEntity.ok(postService.createNew(model,imagePost,image1,image2, username));
    }

    @PutMapping("/post/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable long id,
                                        @RequestParam(value = "imagePost",required = false) MultipartFile imagePost,
                                        @RequestParam(value = "image1",required = false) MultipartFile image1,
                                        @RequestParam(value = "image2",required = false) MultipartFile image2,
                                        @RequestParam(value = "title") String title,
                                        @RequestParam(value = "desc") String desc,
                                        @RequestParam(value = "categoryId") Long categoryId){
        PostRequestDTO model = new PostRequestDTO(title,desc,categoryId);
        return ResponseEntity.ok(postService.update(id,model,imagePost,image1,image2));
    }

    @PutMapping("/admin/post/approved/{id}")
    public ResponseEntity<?> approvedPost(@PathVariable long id){
        if(Boolean.TRUE.equals(postService.changeToApprovedPosts(id))){
            return ResponseEntity.ok(new SuccessResponseDTO(HttpStatus.OK,
                    "Approved post successful"));
        }
        else{
            return ResponseEntity.badRequest().body("Approved post failure");
        }
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws ParseException {
        if(postService.delete(id)){
            return ResponseEntity.badRequest().body("You have no permission to delete this post!!");
        }
        return ResponseEntity.ok(new SuccessResponseDTO(HttpStatus.OK,
                "Delete successfully!"));
    }


    @PutMapping("/admin/post/deny/{id}")
    public ResponseEntity<?> denyPost(@PathVariable long id){
        if(Boolean.TRUE.equals(postService.changeToDenyPosts(id))) {
            return ResponseEntity.ok(new SuccessResponseDTO(HttpStatus.OK,
                    "Deny post successful"));
        }
        else{
            return ResponseEntity.badRequest().body("Deny post failure");
        }
    }

    @GetMapping("/post/user")
    public ResponseEntity<?> getListPostByUser(@RequestParam long userId,
                                               @RequestParam(required = false) String status,
                                               @RequestParam(required = false) String page,
                                               @RequestParam(required = false) String limit){
        ListOutputResult result = postService.getPostByUser(userId, status, page, limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    //status: Approved, Deny, Waiting, Delete
    @GetMapping("/post/get-all")
    public ResponseEntity<?> getListPost(@RequestParam(required = false) String status,
                                         @RequestParam(required = false) String page,
                                         @RequestParam(required = false) String limit){
        ListOutputResult result = postService.getListPost(status, page, limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable long id){
        return ResponseEntity.ok(postService.getById(id));
    }


    @GetMapping("/post")
    public ResponseEntity<?> getListPostByCategory(@RequestParam long categoryId,
                                                   @RequestParam(required = false) String status,
                                                   @RequestParam(required = false) String page,
                                                   @RequestParam(required = false) String limit){
        ListOutputResult result = postService.getPostByCategory(categoryId, status, page, limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/post/search")
    public ResponseEntity<?> searchUser(@RequestBody SearchRequestDTO requestDTO,
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String limit){
        ListOutputResult result = postService.searchUser(requestDTO,page,limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}
