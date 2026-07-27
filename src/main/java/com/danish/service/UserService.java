package com.danish.service;

import com.danish.util.PasswordUtil;
import com.danish.model.User;
import com.danish.repository.UserRepository;
import com.danish.repository.interfaces.IUserRepository;
public class UserService {

    private final IUserRepository repository = new UserRepository();

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

        if (repository.emailExists(user.getEmail())) {
            System.out.println("Email already exists.");
            return false;
        }

        user.setPassword(
                PasswordUtil.hashPassword(user.getPassword())
        );

        return repository.registerUser(user);
    }

    public User login(String email, String password) {

        User user = repository.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (PasswordUtil.verifyPassword(password, user.getPassword())) {
            return user;
        }

        return null;
    }
}