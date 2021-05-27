package com.app.services;

import com.app.User;
import com.app.dao.UserDao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User validateUser(User user){
        user.setValidated(true);

        if (user.getUserName().isEmpty()){
            user.setValidated(false);
            user.setErrors("User cannot be empty");
        }

        if (user.getUserPassword().isEmpty()){
            user.setValidated(false);
            user.setErrors("password cannot be empty");
        }

        if (!user.getUserName().isEmpty()){
            if (!userDao.checkUserNameAvailability(user.getUserName())){
                user.setValidated(false);
                user.setErrors("no! you can't use this name! choose another one!");
            }
        }

        if (user.isValidated()) {
            String hashedPassword = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt());
            user.setUserPassword(hashedPassword);
            userDao.storeUser(user);
        }
        return user;
    }
}
