package com.example.demo.controller;

import com.example.demo.Util.ApplicationUser;
import com.example.demo.Util.ApplicationUserService;
import com.example.demo.dto.ListOutputResult;
import com.example.demo.dto.SearchRequestDTO;
import com.example.demo.dto.user.ProfileDTO;
import com.example.demo.dto.SuccessResponseDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.service.FileService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @GetMapping("/admin/user/get-all")
    public ResponseEntity<?> getListUser(@RequestParam(required = false) String pageNumber,
                                         @RequestParam(required = false) String pageSize){
        ListOutputResult result = userService.getListUser(pageNumber,pageSize);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<?> profileUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((ApplicationUser)principal).getUsername();
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("/user/changeProfile")
    public ResponseEntity<?> changeProfileUser(@RequestBody ProfileDTO model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof ApplicationUser) {
            username = ((ApplicationUser)principal).getUsername();
        } else {
            throw new IllegalStateException("Change profile failure!");
        }
        return ResponseEntity.ok(userService.changeProfile(model, username));
    }

    @PostMapping("/user/avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam(value = "avatar") MultipartFile avatar){
        if (!Objects.equals(avatar.getContentType(), "image/png"))
            if (!Objects.equals(avatar.getContentType(), "image/jpeg")) {
                return ResponseEntity.badRequest().body("file is not in format jpeg, png");
            }
        String username = ApplicationUserService.GetUsernameLoggedIn();
        if(Boolean.TRUE.equals(userService.uploadUserAvatar(avatar, username))) {
            return ResponseEntity.ok(new SuccessResponseDTO(
                    HttpStatus.OK,
                    "Update avatar user: " + username + " successful"));
        } else {
            return ResponseEntity.badRequest().body("Update avatar user: " + username + " failure");
        }

    }

    @GetMapping("/user/search")
    public ResponseEntity<?> searchUser(/*@RequestParam String keyword,*/
                                        @RequestBody SearchRequestDTO requestDTO,
                                        @RequestParam(required = false) String page,
                                        @RequestParam(required = false) String limit){
        ListOutputResult result = userService.searchUser(requestDTO,page,limit);
        if(result.getItemsNumber() == 0L){
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/admin/user/upload-csv")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        if(fileService.isCsvFormat(file)){
            fileService.processAndSaveData(file);
            return ResponseEntity.ok(new SuccessResponseDTO(
                    HttpStatus.OK,
                    "Update file " + file.getOriginalFilename() + " successful"));
        }
        return ResponseEntity.badRequest().body("Please upload csv file!!");
    }

    @GetMapping("/admin/user/export-csv")
    public void exportCsvFile(HttpServletResponse response) throws IOException {
        String filename = "csv_users.csv";
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition","attachment; filename=" +filename);

//        StatefulBeanToCsv<User> writer = new StatefulBeanToCsvBuilder<User>(response.getWriter())
//                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
//                .withOrderedResults(false)
//                .build();
//
//        try {
//            writer.write(userService.fetchAll());
//        } catch (CsvDataTypeMismatchException e) {
//            throw new RuntimeException(e);
//        } catch (CsvRequiredFieldEmptyException e) {
//            throw new RuntimeException(e);
//        }
        fileService.exportUserToCsv(response.getWriter());
    }

    @GetMapping("/admin/user/export-pdf")
    public void exportPdfFile(HttpServletResponse response) throws IOException {
        String filename = "pdf_users.pdf";
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition","attachment; filename=" +filename);
        fileService.exportUserToPDF(response);
    }

//    @DeleteMapping("/users/{name}")
//    public ResponseEntity<Integer> deleteUser(@PathVariable String name){
//        //userService.deleteUserByName(name);
//        return new ResponseEntity<>(userService.deleteUserByName(name),HttpStatus.OK);
//    }
//    //-------------------------------------------------
//

}
