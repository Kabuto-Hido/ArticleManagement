package com.example.demo.Util;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ApplicationUser implements UserDetails {
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;

    public ApplicationUser() {
    }

    public ApplicationUser(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        Role roleEntity = user.getUserRole();
        Set<GrantedAuthority> role = new HashSet<>();
        role.add(new SimpleGrantedAuthority(roleEntity.getName()));
        this.authorities = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
