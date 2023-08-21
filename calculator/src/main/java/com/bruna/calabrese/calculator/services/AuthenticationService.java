package com.bruna.calabrese.calculator.services;

import com.bruna.calabrese.calculator.domain.user.AuthenticationDTO;
import com.bruna.calabrese.calculator.domain.user.User;
import com.bruna.calabrese.calculator.infra.security.TokenService;
import com.bruna.calabrese.calculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public String authenticateLogin(AuthenticationDTO dto){

        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }

}
