package com.example.demo.controller;

import com.example.demo.config.EmailTemplate;
import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.SuccessResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/recoveryPassword")
public class PasswordRecoveryController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @GetMapping( "/getOtp")
    public Object sendOtpRecoveryCodeToUserEmail(@RequestParam Map<String, String> requestParams) {
        if(requestParams.get("email") == null) {
            throw new BadRequest("Change password by address for user fail! need ?email= param");
        }
        String emailRecovery = (requestParams.get("email"));
        User user = userService.findByEmailAndStatus(emailRecovery,"Active"); //Kiem tra user voi email khoi phuc
        String otpCode = GlobalVariable.GetOTP();
        String username = user.getUsername();
        try {
            emailService.sendAsHTML(
                    emailRecovery,
                    "You have requested to change your account password for " + username,
                    EmailTemplate.TemplateRecoveryPassword(username, otpCode)
            );
        } catch (MessagingException e) {
            throw new BadRequest("Gmail send fail");
        }

        return ResponseEntity.ok().body(otpCode);
    }

    @PutMapping("/recovery/{email}")
    public SuccessResponseDTO recoveryPassword(@PathVariable String email,@RequestBody String newPassword) {
        userService.changeUserPasswordByEmail(newPassword, email);
        return new SuccessResponseDTO(HttpStatus.OK, "Change password success!!");
    }

}
