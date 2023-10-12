package com.example.demo.controller;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.Util.JwtUtil;
import com.example.demo.dto.auth.AuthDTO;
import com.example.demo.dto.auth.AuthResponse;
import com.example.demo.dto.SuccessResponseDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URISyntaxException;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
        ResponseEntity<?> saveResult = applicationUserService.save(user);
        if(saveResult.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(new SuccessResponseDTO(HttpStatus.OK,
                    "Create account with user " + user.getUsername() + " successful"));
        } else {
            return ResponseEntity.badRequest().body(saveResult.getBody());
        }
    }

    @PostMapping("/login")
    public Object authentication (@RequestBody AuthDTO authRequest) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),authRequest.getPassword()
            ));
        }catch (BadCredentialsException ex){
            ResponseEntity.badRequest().body("Username or password is invalid");
        }

        final UserDetails userDetails = applicationUserService.loadUserByUsername(authRequest.getUsername());
        User user = userService.findFirstByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity
                .ok(new AuthResponse(jwt,
                        user.getId(),
                        authRequest.getUsername(),
                        (Set<GrantedAuthority>) userDetails.getAuthorities()));

    }

    @GetMapping("/")
    public ModelAndView home(@RequestParam(value = "jwt") String jwtToken) throws URISyntaxException {
        String username = jwtUtil.extractUsername(jwtToken);
        final UserDetails userDetails = applicationUserService
                .loadUserByUsername(username);
        User user = userService.findFirstByUsername(userDetails.getUsername());

        URIBuilder url = new URIBuilder("/login");
        url.addParameter("jwt", jwtToken);
        url.addParameter("userId", String.valueOf(user.getId()));
        url.addParameter("username", userDetails.getUsername());
        String urlString = url.build().toString();
        return new ModelAndView("redirect:http://localhost:8081" + urlString);
    }

}
