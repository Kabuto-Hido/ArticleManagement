package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Please enter a username")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password should not be null")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Name should not be null")
    @Column(name = "fullname")
    private String fullName;

    @Email(message = "Please enter the valid email")
    @Column(name = "email", unique = true)
    private String email;

    @Pattern(regexp = "^\\d{10}$")
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "gender")
    private String gender;

    @Column(name="status")
    private String status = "Active";

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userRole", nullable = false, referencedColumnName = "id")
    private Role userRole;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Post> userPosts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Feeling> userFeelings;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Comment> userComments;

}
