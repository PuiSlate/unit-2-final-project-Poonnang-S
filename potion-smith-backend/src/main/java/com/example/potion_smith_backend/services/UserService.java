package com.example.potion_smith_backend.services;

import com.example.potion_smith_backend.models.User;
import com.example.potion_smith_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }


    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}

