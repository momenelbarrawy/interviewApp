package com.example.interviewapp.services;

import com.example.interviewapp.abstracts.UserService;
import com.example.interviewapp.dto.UserCreate;
import com.example.interviewapp.dto.UserDto;
import com.example.interviewapp.entities.User;
import com.example.interviewapp.repositpories.UserRepo;
import com.example.interviewapp.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDto findOne(UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "User with id " + userId + " not found"
                ));
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                user.getRole()
        );
        return userDto;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User createOne(UserCreate userCreate) {
        User user = new User();

        user.setEmail(userCreate.email());
        user.setFullName(userCreate.fullName());
        user.setPassword(userCreate.password());
        user.setRole(userCreate.role());
        userRepo.save(user);

        return user;
    }


    public void deleteOne(UUID userId) {

    }
}
