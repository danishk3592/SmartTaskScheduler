package com.danish;

import com.danish.util.PasswordUtil;

public class PasswordTest {

    public static void main(String[] args) {

        String password = "password123";

        String hash = PasswordUtil.hashPassword(password);

        System.out.println("Original Password: " + password);
        System.out.println("Hashed Password: " + hash);

        boolean isMatch = PasswordUtil.verifyPassword(password, hash);

        System.out.println("Password Match: " + isMatch);
    }
}