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
                    DatabaseConnection.class.getClassLoader()
                            .getResourceAsStream("db.properties");

            properties.load(inputStream);

            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {

        try {

            Connection connection =
                    DriverManager.getConnection(url, username, password);

            //System.out.println("✅ Database Connected Successfully!");

            return connection;

        } catch (SQLException e) {

            System.out.println("❌ Connection Failed");

            e.printStackTrace();

            return null;
        }

    }

}