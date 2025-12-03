package com.example.interviewapp.controller;

import com.example.interviewapp.abstracts.UserService;
import com.example.interviewapp.dto.UserCreate;
import com.example.interviewapp.dto.UserDto;
import com.example.interviewapp.entities.User;
import com.example.interviewapp.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<User>>> findAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(users), HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<GlobalResponse<UserDto>> findOne(@PathVariable UUID userId) {
        UserDto userDto = userService.findOne(userId);
        return new ResponseEntity<>(new GlobalResponse<>(userDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<User>> createOne(
            @RequestBody @Valid UserCreate user
    ) {
        User newUser = userService.createOne(user);

        return new ResponseEntity<>(new GlobalResponse<>(newUser), HttpStatus.CREATED);
    }
}
