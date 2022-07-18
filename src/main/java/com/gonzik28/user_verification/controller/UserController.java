package com.gonzik28.user_verification.controller;

import com.gonzik28.user_verification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<String>> findUsers() {
        List<String> users = userService.getUserNames();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/user/{userName}")
    public ModelAndView findUser(@PathVariable String userName, ModelAndView modelAndView) {
        modelAndView.setViewName("result");
        String massage = userService.findByUserName(userName);
        modelAndView.addObject("answer", massage);
        return modelAndView;
    }

}

