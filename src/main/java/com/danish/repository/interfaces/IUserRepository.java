package com.danish.repository.interfaces;

import com.danish.model.User;

public interface IUserRepository {

    boolean registerUser(User user);

    boolean emailExists(String email);

    User findByEmail(String email);
}