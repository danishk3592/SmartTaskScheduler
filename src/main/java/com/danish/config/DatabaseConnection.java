package com.danish.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String url;
    private static String username;
    private static String password;

    static {

        try {

            Properties properties = new Properties();

            InputStream inputStream =
                    DatabaseConnection.class
                            .getClassLoader()
                            .getResourceAsStream("db.properties");

            if (inputStream == null) {
                throw new IOException(
                        "db.properties file not found in resources folder."
                );
            }

            properties.load(inputStream);
            inputStream.close();

            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = System.getenv("DB_PASSWORD");

            if (password == null || password.isBlank()) {
                throw new IOException(
                        "DB_PASSWORD environment variable is not set."
                );
            }

            if (url == null || username == null || password == null) {
                throw new IOException(
                        "Database configuration is incomplete."
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "❌ Failed to load database configuration."
            );

            System.out.println(
                    "Please check src/main/resources/db.properties"
            );

            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                    url,
                    username,
                    password
            );

        } catch (SQLException e) {

            System.out.println(
                    "❌ Database connection failed!"
            );

            System.out.println(
                    "Please check:"
            );

            System.out.println(
                    "1. MySQL Server is running"
            );

            System.out.println(
                    "2. Database name is correct"
            );

            System.out.println(
                    "3. Username/password are correct"
            );

            System.out.println(
                    "4. MySQL is running on the correct port"
            );

            throw new RuntimeException(
                    "Unable to connect to MySQL database.",
                    e
            );
        }
    }
}