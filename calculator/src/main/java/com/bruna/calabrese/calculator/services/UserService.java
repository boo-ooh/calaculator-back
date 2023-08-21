package com.bruna.calabrese.calculator.services;

import com.bruna.calabrese.calculator.domain.user.AuthenticationDTO;
import com.bruna.calabrese.calculator.domain.user.User;
import com.bruna.calabrese.calculator.domain.user.UserDTO;
import com.bruna.calabrese.calculator.repositories.RecordRepository;
import com.bruna.calabrese.calculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordRepository recordRepository;

    public UserDTO getUserWithBalance(AuthenticationDTO authenticationDTO){
         User user= userRepository.findByUsername(authenticationDTO.username());
         Double balance = recordRepository.findTopByUserIdOrderByDateDesc(user.getId()).get().getUserBalance();

        return new UserDTO(user, balance);
    }
}
