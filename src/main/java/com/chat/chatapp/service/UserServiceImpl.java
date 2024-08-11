package com.chat.chatapp.service;


import com.chat.chatapp.model.User;
import com.chat.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
      return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException(" user not found with user: "+username);
        }
        return new UserDetailsImpl(user);
    }
}