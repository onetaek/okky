package com.example.okky.DBConntection;

import java.sql.*;

public class JDBCConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        String url = "jdbc:mariadb://localhost:3306/WebMarketDB";
        String user= "kjh";
        String password = "9172";

        Class.forName("org.mariadb.jdbc.Driver");

        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }




}
