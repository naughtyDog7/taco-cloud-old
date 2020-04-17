package com.example.tacos.service;

import com.example.tacos.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUser(String username);
    boolean usernameTaken(String username);
    boolean checkUsernamePassword(String username, String password);
    User save(User user);
}
