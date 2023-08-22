package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.operation.Operation;
import com.bruna.calabrese.calculator.domain.operation.OperationTypes;
import com.bruna.calabrese.calculator.domain.record.Record;
import com.bruna.calabrese.calculator.domain.user.AuthenticationDTO;
import com.bruna.calabrese.calculator.domain.user.Status;
import com.bruna.calabrese.calculator.domain.user.User;
import com.bruna.calabrese.calculator.repositories.RecordRepository;
import com.bruna.calabrese.calculator.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuthenticationManager authenticationManager;
    @Autowired
    ObjectMapper objectMapper;
    @Mock
    Authentication authentication;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RecordRepository recordRepository;

    AuthenticationDTO authenticationDTO = new AuthenticationDTO("Test", "test");

    User user = new User(1, "Test", "Test", Status.ACTIVE);

    Record record = new Record(1,user, new Operation(1, OperationTypes.addition, 5.0, "Add"),10.0, 10.0, "33", LocalDateTime.now(), false);

    @Test
    void login() {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        when(authenticationManager.authenticate(userNamePassword)).thenReturn(authentication);
        when(userRepository.findByUsername(authenticationDTO.username())).thenReturn(user);
        when(recordRepository.findTopByUserIdOrderByDateDesc(user.getId())).thenReturn(Optional.of(record));
        when(authentication.getPrincipal()).thenReturn(user);
        try {
            String json = objectMapper.writeValueAsString(authenticationDTO);
            var response = mockMvc.perform(post("/auth/login")
                            .content(json)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
            ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser
    void register() {
        try {
            String json = objectMapper.writeValueAsString(authenticationDTO);
            mockMvc.perform(post("/auth/register")
                            .content(json)
                            .contentType("application/json"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}