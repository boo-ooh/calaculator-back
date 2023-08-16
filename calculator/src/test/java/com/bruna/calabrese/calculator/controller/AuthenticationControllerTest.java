package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.user.AuthenticationDTO;
import com.bruna.calabrese.calculator.infra.security.TokenService;
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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    TokenService tokenService;
    @Autowired
    ObjectMapper objectMapper;
    @Mock
    Authentication authentication;
    AuthenticationDTO authenticationDTO = new AuthenticationDTO("Test", "test");

    @Test
    void login() {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        when(authenticationManager.authenticate(userNamePassword)).thenReturn(authentication);
        try {
            String json = objectMapper.writeValueAsString(authenticationDTO);
            mockMvc.perform(post("/auth/login")
                            .content(json)
                            .contentType("application/json"))
                    .andExpect(status().isOk());
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