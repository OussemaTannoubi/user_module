package com.security.user.services;


import com.security.user.Exceptions.UserAlreadyExistsException;
import com.security.user.entities.User;
import com.security.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static final String  USER_TEXT = "user with id";

    public User saveUser(User user) throws UserAlreadyExistsException {
        Optional<User> checkExistantUser = userRepository.findUserById(user.getId());
        if (!checkExistantUser.isPresent()){
            User newUser = new User();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setDisplayName(user.getDisplayName());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());

            return   userRepository.save(newUser);
        } else
            throw new UserAlreadyExistsException(USER_TEXT + user.getId() + "already exists");

    }
}
