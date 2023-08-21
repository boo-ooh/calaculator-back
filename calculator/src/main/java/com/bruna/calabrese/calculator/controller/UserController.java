package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.user.UserDTO;
import com.bruna.calabrese.calculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public UserDTO getUser() {
        return new UserDTO(userRepository.findById(1).orElseThrow(() -> new RuntimeException("User not found.")),0.0);
    }

}
