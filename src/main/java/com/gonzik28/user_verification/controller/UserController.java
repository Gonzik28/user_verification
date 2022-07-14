package com.gonzik28.user_verification.controller;

import com.gonzik28.user_verification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<String>> findUsers() {
        List<String> users = userService.getUserNames();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/user/{userName}")
    public ResponseEntity<String> UserController(@PathVariable String userName) {
        String massage = userService.findByUserName(userName);
        return ResponseEntity.ok(massage);
    }

}

