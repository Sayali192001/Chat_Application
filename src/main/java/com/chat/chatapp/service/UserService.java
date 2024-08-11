package com.chat.chatapp.service;

import com.chat.chatapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);
    User findByUsername(String username);
}
