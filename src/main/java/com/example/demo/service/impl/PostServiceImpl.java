package com.example.demo.service.impl;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.config.FeelingType;
import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.post.PostRequestDTO;
import com.example.demo.dto.post.PostResponseDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FeelingRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.config.ApplicationUserRole.ADMIN;

@Component
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FeelingRepository feelingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private FiltersSpecificationServiceImpl<Post> postFiltersSpecificationService;

    private Boolean isNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean isValidNumber(String num) {
        return num != null && !num.isEmpty() && isNumber(num) && Long.parseLong(num) >= 0;
    }

    @Override
    public PostResponseDTO createNew(PostRequestDTO postRequestDTO,
                                     MultipartFile imagePost, MultipartFile image1,
                                     MultipartFile image2, String username) {
        if(postRequestDTO.getCategoryId() == null){
            throw new BadRequest("Please enter category!");
        }
        Category category = categoryRepository.findById(postRequestDTO.getCategoryId()).orElseThrow(()
            -> new NotFoundException("Not found category with id "+ postRequestDTO.getCategoryId()));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));

        Post post = PostMapper.toEntity(postRequestDTO);
        post.setCategoryId(category);
        post.setUserId(user);
        post = postRepository.save(post);

        Post result = uploadImg(imagePost,image1,image2,post);
        return PostMapper.toDto(result);
    }

    @Override
    public PostResponseDTO update(long id, PostRequestDTO postRequestDTO, MultipartFile imagePost,
                                  MultipartFile image1, MultipartFile image2) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found post with id "+ id));

        String username = ApplicationUserService.GetUsernameLoggedIn();
        if(!username.equals(post.getUserId().getUsername())){
            throw new BadRequest("You have no permission to update this post!!");
        }

        if(!post.getStatus().equals("Approved")){
            throw new BadRequest("You can not update approved post!!");
        }

        if(postRequestDTO.getCategoryId() == null){
            throw new BadRequest("Please enter category!");
        }

        if(!postRequestDTO.getCategoryId().equals(post.getCategoryId().getId())){
            Category category = categoryRepository.findById(postRequestDTO.getCategoryId()).orElseThrow(()
                    -> new NotFoundException("Not found category with id "+ postRequestDTO.getCategoryId()));
            post.setCategoryId(category);
        }

        post.setTitle(postRequestDTO.getTitle());
        post.setDesc(postRequestDTO.getDesc());

        Post result = uploadImg(imagePost,image1,image2,post);
        return PostMapper.toDto(result);
    }

    @Override
    public ListOutputResult getListPost(String status, String page, String limit) {
        Pageable pageable = preparePaging(page,limit);
        Page<Post> posts;
        if(status == null){
            posts = postRepository.findAll(pageable);
        }
        else {
            posts = postRepository.findAllByStatus(status, pageable);
        }
        return resultPaging(posts);
    }

    @Override
    public ListOutputResult getPostByCategory(long categoryId, String status, String page, String limit) {
        Pageable pageable = preparePaging(page,limit);
        Page<Post> posts;
        if(status == null){
            posts = postRepository.findAllByCategoryIdId(categoryId, pageable);
        }
        else {
            posts = postRepository.findAllByStatusAndCategoryIdId(status, categoryId, pageable);
        }
        return resultPaging(posts);
    }

    @Override
    public ListOutputResult getPostByUser(long userId, String status,
                                                     String page, String limit) {
        Pageable pageable = preparePaging(page,limit);
        Page<Post> posts;
        if(status == null){
            posts = postRepository.findAllByUserIdId(userId, pageable);
        }
        else {
            posts = postRepository.findAllByStatusAndUserIdId(status, userId, pageable);
        }
        return resultPaging(posts);
    }

    @Override
    public Boolean changeToApprovedPosts(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found post with id "+ id));
        post.setStatus("Approved");
        postRepository.save(post);
        return true;
    }

    @Override
    public Boolean changeToDenyPosts(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found post with id "+ id));
        post.setStatus("Deny");
        postRepository.save(post);
        return true;
    }

    @Override
    public Boolean delete(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Fail! This shop not exist"));

        String username = ApplicationUserService.GetUsernameLoggedIn();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));

        if(!user.getUserRole().getName().equals(ADMIN.name())){
            if(!username.equals(post.getUserId().getUsername())){
                return false;
            }
        }
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public ListOutputResult searchUser(SearchRequestDTO requestDTO, String page, String limit) {
        Pageable pageable = preparePaging(page,limit);

        Specification<Post> postSpecification = postFiltersSpecificationService
                .getSearchSpecification(requestDTO.getCriteriaList());

        Page<Post> posts = postRepository.findAll(postSpecification,pageable);

        return resultPaging(posts);
    }

    @Override
    public PostResponseDTO getById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found post with id "+ id));

        post.setView(post.getView() + 1);
        post = postRepository.save(post);

        PostResponseDTO postResponseDTO = PostMapper.toDto(post);
        long amountLike = feelingRepository.countByPostIdIdAndType(post.getId(), FeelingType.LIKE);
        long amountDislike = feelingRepository.countByPostIdIdAndType(post.getId(), FeelingType.DISLIKE);
        postResponseDTO.setLikes(prettyCount(amountLike));
        postResponseDTO.setDislikes(prettyCount(amountDislike));

        return postResponseDTO;
    }

    public Pageable preparePaging(String page, String limit){
        limit = isValidNumber(limit) ? limit : GlobalVariable.DEFAULT_LIMIT_SEARCH;
        page = isValidNumber(page) ? page : GlobalVariable.DEFAULT_PAGE;
        return PageRequest.of((Integer.parseInt(page) - 1),Integer.parseInt(limit),
                Sort.by(Sort.Direction.DESC, "createdDate"));
    }
    public ListOutputResult resultPaging(Page<Post> posts){
        List<PostResponseDTO> postResponseDTOS = new ArrayList<>();
        for (Post post : posts){
            PostResponseDTO postResponseDTO = PostMapper.toDto(post);
            long amountLike = feelingRepository.countByPostIdIdAndType(post.getId(), FeelingType.LIKE);
            long amountDislike = feelingRepository.countByPostIdIdAndType(post.getId(), FeelingType.DISLIKE);
            postResponseDTO.setLikes(prettyCount(amountLike));
            postResponseDTO.setDislikes(prettyCount(amountDislike));
            postResponseDTOS.add(postResponseDTO);
        }
        ListOutputResult result = new ListOutputResult(0, 0, null,null,new ArrayList<>());
        if (!posts.isEmpty()) {
            result.setResult(postResponseDTOS);
            result.setTotalPage(posts.getTotalPages());
            result.setItemsNumber(posts.getTotalElements());

            if(posts.hasNext()){
                result.setNextPage((long) posts.nextPageable().getPageNumber() + 1);
            }
            if(posts.hasPrevious()){
                result.setPreviousPage((long) posts.previousPageable().getPageNumber() + 1);
            }
        }
        return result;
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

    public Post uploadImg(MultipartFile imagePost, MultipartFile image1,
                          MultipartFile image2, Post post){
        //upload img
        String path = "BlogWeb" + "/" + "Post" + "/";

        if(!imagePost.isEmpty()){
            String imgPostUrl = cloudinaryService.uploadFile(
                    imagePost,
                    "imagePost",
                    path + post.getId());
            post.setImagePost(imgPostUrl);
        }

        if(!image1.isEmpty()) {
            String img1Url = cloudinaryService.uploadFile(
                    image1,
                    "image1",
                    path + post.getId());
            post.setImage1(img1Url);
        }

        if(!image2.isEmpty()) {
            String img2Url = cloudinaryService.uploadFile(
                    image2,
                    "image2",
                    path + post.getId());
            post.setImage2(img2Url);
        }

        return postRepository.save(post);
    }
}
