package com.learntodrive.user_detail.learntodrive_user_detail.controller;

import com.learntodrive.user_detail.learntodrive_user_detail.model.User;
import com.learntodrive.user_detail.learntodrive_user_detail.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/getall")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @GetMapping("/get/{email}")
//    public User getUsers(@PathVariable String email) {
//        return userRepository.findByEmail(email);
//    }

    @GetMapping("/email/{email}")
    public Optional<User> getUserDetails(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }
}
