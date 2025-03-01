package com.hmall.service.impl;

import com.hmall.domain.dto.LoginFormDTO;
import com.hmall.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    protected UserServiceImpl userServiceImpl;
    @Autowired
    protected AddressServiceImpl addressServiceImpl;
    @Test
    void login() {
        LoginFormDTO loginFormDTO = new LoginFormDTO();
        loginFormDTO.setUsername("Jack");
        loginFormDTO.setPassword("123");
        userServiceImpl.login(loginFormDTO);

    }
}