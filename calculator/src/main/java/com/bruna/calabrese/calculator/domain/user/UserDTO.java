package com.bruna.calabrese.calculator.domain.user;

public record UserDTO(int id, String username, String password, Status status) {

    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getStatus());
    }
}
