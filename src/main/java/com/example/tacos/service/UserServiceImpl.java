package com.example.tacos.service;

import com.example.tacos.dao.UserDAO;
import com.example.tacos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        return Optional
                .ofNullable(user)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }


    @Override
    public User findUser(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public boolean usernameTaken(String username) {
        return userDAO.existsByUsername(username);
    }

    @Override
    public boolean checkUsernamePassword(String username, String password) {
        return userDAO.existsByUsernameAndPassword(username, password);
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }
}
