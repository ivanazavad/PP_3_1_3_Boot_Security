package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;

@RestController
@RequestMapping("/user/user_page")
public class UsersController {
    @GetMapping
    public ResponseEntity<User> userPage(@AuthenticationPrincipal User user) {
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
