package com.universidad.universidad_backend.controller;

import com.universidad.universidad_backend.dto.UserLoginDTO;
import com.universidad.universidad_backend.model.User;
import com.universidad.universidad_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody UserLoginDTO loginDto) {
        return ResponseEntity.ok(userService.signin(loginDto.getUsername(), loginDto.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.signup(user));
    }
}
