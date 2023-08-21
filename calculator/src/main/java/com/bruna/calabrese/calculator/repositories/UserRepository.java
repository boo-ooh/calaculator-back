package com.bruna.calabrese.calculator.repositories;

import com.bruna.calabrese.calculator.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);



}
