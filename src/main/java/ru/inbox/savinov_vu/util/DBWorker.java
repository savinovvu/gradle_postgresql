package ru.inbox.savinov_vu.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private static final String URL = "jdbc:postgresql://localhost:5432/humantestdb";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "1";
    private Connection connection;


    public DBWorker() {

        try {
            java.sql.Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}