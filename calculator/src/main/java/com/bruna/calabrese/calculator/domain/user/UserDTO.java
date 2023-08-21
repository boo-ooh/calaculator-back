package com.bruna.calabrese.calculator.domain.user;

public record UserDTO(int id, String username, Status status) {

    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getStatus());
    }
}
