package com.app;

import java.sql.Connection;
import java.sql.DriverManager;

public class CheckConn {
    public static void main(String[] args) {
        if (args == null || args.length == 0 || args.length != 4) {
            System.out.println("USAGE:Please provide the following arguments: URL, DriverClass, UserName, Password");
        }
        String url = args[0];
        String driver = args[1];
        String username = args[2];
        String password = args[3];

        ping(url, driver, username, password);
    }

    public static void ping(String url, String driver, String username, String password) {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected to: " + url);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
