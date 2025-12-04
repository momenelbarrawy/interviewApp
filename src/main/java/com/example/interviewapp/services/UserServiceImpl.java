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
import java.util.Optional;
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

        validateUserCreate(userCreate);

        Optional<User> existingUser = userRepo.findByEmail(userCreate.email());
        if (existingUser.isPresent()) {
            throw CustomResponseException.BadRuqest("email already exists");
        }

        User user = new User();

        user.setEmail(userCreate.email());
        user.setFullName(userCreate.fullName());
        user.setPassword(userCreate.password());
        user.setRole(userCreate.role());

        userRepo.save(user);

        return user;
    }


    public void deleteOne(UUID userId) {
        userRepo.deleteById(userId);
    }


    private void validateUserCreate(UserCreate userCreate) {
        // Email validation
        if (userCreate.email() == null || userCreate.email().trim().isEmpty()) {
            throw CustomResponseException.BadRuqest("Invalid email");
        }

        if (userCreate.email().length() > 100) {
            throw CustomResponseException.BadRuqest("Invalid email");
        }

        if (!isValidEmail(userCreate.email())) {
            throw CustomResponseException.BadRuqest("Invalid email");
        }

        // Full name validation
        if (userCreate.fullName() == null || userCreate.fullName().trim().isEmpty()) {
            throw CustomResponseException.BadRuqest("Invalid Full name");
        }

        if (userCreate.fullName().length() > 100) {
            throw CustomResponseException.BadRuqest("Invalid Full name");
        }

        // Password validation
        if (userCreate.password() == null || userCreate.password().trim().isEmpty()) {
            throw CustomResponseException.BadRuqest("Invalid Password");
        }

        if (userCreate.password().length() < 8) {
            throw CustomResponseException.BadRuqest("Invalid Password");
        }

        // Role validation
        if (userCreate.role() == null) {
            throw CustomResponseException.BadRuqest("Role is required");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }
}
