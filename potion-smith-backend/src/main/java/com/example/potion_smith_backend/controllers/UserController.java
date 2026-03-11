package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.dtos.UserDTO;
import com.example.potion_smith_backend.models.User;
import com.example.potion_smith_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping(value= "/add", consumes= MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody UserDTO userData) {
        User user = new User(userData.getUsername(), userData.getEmail(), userData.getAge(), userData.getPassword());
        return userService.save(user);
    }

//    Login endpoint to authenticate users based on their email and password.
//    It retrieves all users from the database and checks if the provided email and password match
//    any existing user. If a match is found, it returns the corresponding user object; otherwise,
//    it throws a RuntimeException indicating invalid credentials.
    @PostMapping("/login")
    public User login(@RequestBody UserDTO loginData) {

        List<User> users = userService.findAll();

        for (User user : users) {
            if (user.getEmail().equals(loginData.getEmail()) &&
                    user.getPassword().equals(loginData.getPassword())) {
                return user;
            }
        }

        throw new RuntimeException("Invalid email or password");
    }


}
