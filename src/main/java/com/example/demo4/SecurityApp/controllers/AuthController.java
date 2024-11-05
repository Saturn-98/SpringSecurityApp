package com.example.demo4.SecurityApp.controllers;

import com.example.demo4.SecurityApp.dto.LoginDto;
import com.example.demo4.SecurityApp.dto.SignupDto;
import com.example.demo4.SecurityApp.dto.UserDto;
import com.example.demo4.SecurityApp.services.AuthService;
import com.example.demo4.SecurityApp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        UserDto user = userService.signUp(signupDto);
        return ResponseEntity.ok(user);

    }

    @PostMapping(path="/logIn")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        String token = authService.logIn(loginDto);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
