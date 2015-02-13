package com.trushank.dbperformance;
import java.sql.Connection;
import java.sql.DriverManager;

/**
ConnectionConfiguration.java
@author Trushank
Date: Sep 28, 2014
 */
public class ConnectionConfiguration {
    public static final String URL = "jdbc:mysql://localhost:3306/performanceevaluation";
    /**
     * In my case username is "root" *
     */
    public static final String USERNAME = "root";
    /**
     * In my case password is "1234" *
     */
    public static final String PASSWORD = "";
 
    public static Connection getConnection() {
        Connection connection = null;
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return connection;
    }
}

