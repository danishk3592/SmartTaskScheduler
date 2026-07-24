package com.danish.service;

import com.danish.model.User;
import com.danish.repository.UserRepository;

public class UserService {

    private final UserRepository repository = new UserRepository();

    public boolean register(User user) {

        if (user.getFullName().isBlank()) {
            System.out.println("Name cannot be empty.");
            return false;
        }

        if (!user.getEmail().contains("@")) {
            System.out.println("Invalid email.");
            return false;
        }

        if (user.getPassword().length() < 6) {
            System.out.println("Password must contain at least 6 characters.");
            return false;
        }

        return repository.registerUser(user);
    }

    public User login(String email, String password) {

        return repository.loginUser(email, password);

    }
}