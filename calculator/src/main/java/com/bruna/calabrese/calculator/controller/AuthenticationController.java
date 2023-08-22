package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.user.*;
import com.bruna.calabrese.calculator.repositories.RecordRepository;
import com.bruna.calabrese.calculator.repositories.UserRepository;
import com.bruna.calabrese.calculator.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecordRepository recordRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        String token = authenticationService.authenticateLogin(dto);
        User user = userRepository.findByUsername(dto.username());

        return ResponseEntity.ok(new LoginResponseDTO(new UserDTO(user),token));
    }

    @GetMapping("/validateToken")
    public ResponseEntity login() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AuthenticationDTO data) {
        if (this.userRepository.findByUsername(data.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, Status.ACTIVE);

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
