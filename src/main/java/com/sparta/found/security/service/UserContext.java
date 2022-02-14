package com.sparta.found.security.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

@Getter
public class UserContext extends User {
    private com.sparta.found.domain.entity.User user;

    public UserContext(com.sparta.found.domain.entity.User user, ArrayList<GrantedAuthority> roles){
        super(user.getUsername(),user.getPassword(),roles);
        this.user = user;
    }
}
