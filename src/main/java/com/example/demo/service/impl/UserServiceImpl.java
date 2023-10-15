package com.example.demo.service.impl;

import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.user.ProfileDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private FiltersSpecificationServiceImpl<User> userFiltersSpecificationService;

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

    public Pageable preparePaging(String pageNumber, String pageSize){
        pageSize = isValidNumber(pageSize) ? pageSize : GlobalVariable.DEFAULT_LIMIT_USER;
        pageNumber = isValidNumber(pageNumber) ? pageNumber : GlobalVariable.DEFAULT_PAGE;

        return PageRequest.of((Integer.parseInt(pageNumber) - 1),Integer.parseInt(pageSize),
                Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    public ListOutputResult resultPaging(List<?> listUser, Page<User> users){
        ListOutputResult result = new ListOutputResult(0, 0,null,null, new ArrayList<>());
        if (!listUser.isEmpty()) {
            result.setList(listUser);
            result.setTotalPage(users.getTotalPages());
            result.setItemsNumber(users.getTotalElements());

            if(users.hasNext()){
                result.setNextPage((long) users.nextPageable().getPageNumber() + 1);
            }
            if(users.hasPrevious()){
                result.setPreviousPage((long) users.previousPageable().getPageNumber() + 1);
            }
        }
        return result;
    }

    @Override
    public ListOutputResult getListUser(String pageNumber, String pageSize) {
        Pageable pages = preparePaging(pageNumber,pageSize);

        Page<User> users = userRepository.findAll(pages);

        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users){
            userDTOS.add(UserMapper.toUserDto(user));
        }

        return resultPaging(userDTOS, users);

    }

    @Override
    public List<User> fetchAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not exist"));
        return UserMapper.toUserDto(user);
    }

    @Override
    public ListOutputResult searchUser(SearchRequestDTO requestDTO, String page, String limit) {
//        if(keyword == null){
//            throw new BadRequest("Please enter valid keyword");
//        }

        Specification<User> userSpecification = userFiltersSpecificationService
                .getSearchSpecification(requestDTO.getCriteriaList());

        Pageable pages = preparePaging(page,limit);

        Page<User> users = userRepository.findAll(userSpecification,pages);
        System.out.println(users);

//        Page<User> users = userRepository.search(keyword, pages);

        List<ProfileDTO> profileDTOS = new ArrayList<>();

        for(User user : users) {
            profileDTOS.add(UserMapper.toProfileDto(user));
        }

        return resultPaging(profileDTOS, users);

    }

    @Override
    public ProfileDTO findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new BadRequest("Not found user with "+username);
        }
        return UserMapper.toProfileDto(user.get());
    }

    @Override
    public ProfileDTO changeProfile(ProfileDTO profileDTO, String username) {
        User changeProfileUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cannot found user with username = " + username));
        //check unique email
        if(Boolean.TRUE.equals(checkValidValue(profileDTO.getEmail(), changeProfileUser.getEmail()))){
            Optional<User> userOptional = userRepository.findByEmail(profileDTO.getEmail());
            if(userOptional.isPresent()){
                throw new BadRequest("Email taken!");
            }
            changeProfileUser.setEmail(profileDTO.getEmail());
        }
        //check unique phone
        if(Boolean.TRUE.equals(checkValidValue(profileDTO.getPhone(), changeProfileUser.getPhone()))){
            Optional<User> userOptional = userRepository.findByPhone(profileDTO.getPhone());
            if(userOptional.isPresent()){
                throw new BadRequest("Phone taken!");
            }
            changeProfileUser.setPhone(profileDTO.getPhone());
        }
        //check fullname
        if(Boolean.TRUE.equals(checkValidValue(profileDTO.getFullname(), changeProfileUser.getFullName()))){
            changeProfileUser.setFullName(profileDTO.getFullname());
        }
        //check gender
        if(Boolean.TRUE.equals(checkValidValue(profileDTO.getGender(), changeProfileUser.getGender()))){
            changeProfileUser.setGender(profileDTO.getGender());
        }
        changeProfileUser = userRepository.save(changeProfileUser);
        return UserMapper.toProfileDto(changeProfileUser);

    }

    @Override
    public Boolean uploadUserAvatar(MultipartFile avatar, String username) {
        Optional<User> usersOptional = userRepository.findByUsername(username);
        if(!usersOptional.isPresent()) {
            throw new IllegalStateException("Username " + username + " not found");
        }
        User userLogin = usersOptional.get();
        String imgName = userLogin.getId() + "_" + username;
        String url = cloudinaryService.uploadFile(
                avatar,
                imgName,
                "BlogWeb" + "/" + "Avatar");
        if(Objects.equals(url, "-1")) {
            throw new IllegalStateException("Upload Image Fail!");
        }
        else{
            userLogin.setAvatar(url);
            userRepository.save(userLogin);
            return true;
        }
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
//        Role role =  roleRepository.findOneByName(userDTO.getRole());
        User user = new User();
        //update
//        if(userDTO.getId() != 0L){
//            user = userRepository.findById(userDTO.getId())
//                    .orElseThrow(() -> new NotFoundException("User not exist"));
//
//            user.setName(userDTO.getName());
//            user.setAvatar(userDTO.getAvatar());
//            user.setEmail(userDTO.getEmail());
//            user.setPhone(userDTO.getPhone());
//            user.setPassword(userDTO.getPassword());
//
//        }
//        //create new
//        else {
//            user = UserMapper.toEntity(userDTO);
//        }
        //user.setUserRole(role);
//        user = userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Integer deleteUserByName(String name) {
        return 1;
//        return userRepository.deleteUserByName(name);
    }

    @Override
    public User findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    Boolean checkValidValue(String newValue, String oldValue){
        return newValue != null && !newValue.isEmpty() &&
                !Objects.equals(newValue, oldValue);
    }
}
