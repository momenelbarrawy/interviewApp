package com.example.interviewapp.abstracts;

import com.example.interviewapp.dto.UserCreate;
import com.example.interviewapp.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User findone(UUID userId);

    List<User> findAll();

    User createOne(UserCreate user);

    void deleteOne(UUID userId);
}
