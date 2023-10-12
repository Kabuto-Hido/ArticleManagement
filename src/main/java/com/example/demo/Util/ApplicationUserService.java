package com.example.demo.Util;

import com.example.demo.dto.user.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(ApplicationUser::new).get();
    }

    public static String GetUsernameLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof ApplicationUser) {
            username = ((ApplicationUser)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }

    public ResponseEntity<?> save(UserDTO userDTO){
        Optional<User> DAOUsernameOptional = userRepository.findByUsername(userDTO.getUsername());
        if(userDTO.getPassword() == null || userDTO.getPassword().length() <= 6) {
            return new ResponseEntity<>("Password must be longer than 7 character and can't be null", HttpStatus.BAD_REQUEST);
        }

        //Nếu không trùng username, encode pwd và lưu vào db user
        Role roleUser = roleRepository.findById(Long.parseLong("2"))
                .orElseThrow(() -> new UsernameNotFoundException("Not found")); //Lấy ROLE_USER

        User newUser = UserMapper.toEntity(userDTO); //Parse DTO sang Entity
        String defaultAvatar = "https://firebasestorage.googleapis.com/v0/b/cnpm-30771.appspot.com/o/no-user.png?alt=media&token=517e08ab-6aa4-42eb-9547-b1b10f17caf0";
        newUser.setAvatar(defaultAvatar);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); //Bcrypt password tk
        newUser.setUserRole(roleUser);
        newUser.setGender("3");

        //newUser.setFullName("Unname#" + GlobalVariable.GetOTP());
        userRepository.save(newUser);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}
