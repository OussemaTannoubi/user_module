package com.security.user.controllers;


import com.security.user.Exceptions.UserAlreadyExistsException;
import com.security.user.entities.User;
import com.security.user.repositories.UserRepository;
import com.security.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
