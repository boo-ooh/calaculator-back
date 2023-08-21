package com.bruna.calabrese.calculator.domain.user;

public record UserDTO(int id, String username, Status status, Double balance) {

    public UserDTO(User user, Double bal) {
        this(user.getId(), user.getUsername(), user.getStatus(), bal);
    }
}
