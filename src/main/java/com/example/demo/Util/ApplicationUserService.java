package com.example.demo.Util;

import com.example.demo.config.EmailTemplate;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.TypeAccount;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequest;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TypeAccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.TypeAccountService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
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
    @Autowired
    private UserService userService;
    @Autowired
    private TypeAccountService typeAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndStatus(username, "Active");
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(ApplicationUser::new).get();
    }

    public static String GetUsernameLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof ApplicationUser) {
            username = ((ApplicationUser) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }

    public ResponseEntity<?> save(UserDTO userDTO) {
        Optional<User> DAOUsernameOptional = userRepository.findByUsername(userDTO.getUsername());
        if (userDTO.getPassword() == null || userDTO.getPassword().length() <= 6) {
            return new ResponseEntity<>("Password must be longer than 7 character and can't be null", HttpStatus.BAD_REQUEST);
        }

        //Nếu không trùng username, encode pwd và lưu vào db user
        Role roleUser = roleRepository.findById(Long.parseLong("2"))
                .orElseThrow(() -> new UsernameNotFoundException("Not found")); //Lấy ROLE_USER

        Optional<User> userByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (userByEmail.isPresent()) {
            throw new BadRequest("Email duplicate, Please retype!");
        }

        User newUser = UserMapper.toEntity(userDTO); //Parse DTO sang Entity
        String defaultAvatar = "https://firebasestorage.googleapis.com/v0/b/cnpm-30771.appspot.com/o/no-user.png?alt=media&token=517e08ab-6aa4-42eb-9547-b1b10f17caf0";
        newUser.setAvatar(defaultAvatar);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); //Bcrypt password tk
        newUser.setUserRole(roleUser);
        newUser.setGender("3");

        TypeAccount regularAcc = typeAccountService.getTypeByName("Regular");
        newUser.setUserType(regularAcc);

        //newUser.setFullName("Unname#" + GlobalVariable.GetOTP());
        userRepository.save(newUser);

        String username = userDTO.getUsername();
        try {
            userService.sendEmailToActivatedAccount(userDTO.getEmail(), username);
        } catch (MessagingException e) {
            throw new BadRequest("Gmail send fail");
        }

        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}
