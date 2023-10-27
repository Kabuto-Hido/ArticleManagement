package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "typeaccount")
public class TypeAccount extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "type_name", unique = true)
    private String typename;

    @OneToMany(mappedBy = "userType" ,cascade = CascadeType.ALL)
    private List<User> users;
}

