package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
    private String status = "Not Active";

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userRole", nullable = false, referencedColumnName = "id")
    private Role userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userType", nullable = false, referencedColumnName = "id")
    private TypeAccount userType;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> userOrders;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> userPosts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Feeling> userFeelings;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> userComments;

}
