package com.example.okky.DBConntection;

import java.sql.*;

public class JDBCConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        String url = "jdbc:mariadb://localhost:3306/okky";
        String user= "root";
        String password = "7517";

        Class.forName("org.mariadb.jdbc.Driver");

        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }




}
