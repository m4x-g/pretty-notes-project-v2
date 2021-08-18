package com.app.security;

import com.app.User;
import com.app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<User> users = userDao.getUserByName(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("user " + username + "not found!");
        }

        User user = users.get(0);

        return new CustomUserDetails(user);
    }
}
