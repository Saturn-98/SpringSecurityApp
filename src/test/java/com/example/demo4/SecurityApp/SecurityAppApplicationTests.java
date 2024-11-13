package com.example.demo4.SecurityApp;

import com.example.demo4.SecurityApp.entities.UserEntity;
import com.example.demo4.SecurityApp.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityAppApplicationTests {

    @Autowired
    private JwtService jwtService;

    @Test
    void contextLoads(){

        UserEntity user = new UserEntity(4L, "user@gmail.com", "1234", "user");

        String token = jwtService.generateToken(user);

        System.out.println(token);

        Long userId = jwtService.getUserIdFromToken(token);

        System.out.println(userId);

    }




}
