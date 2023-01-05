package com.example.okky.daos;

import com.example.okky.dtos.MemberDto;

import java.sql.*;

public class MemberDao {
    PreparedStatement pstmt;
    ResultSet rs;
    Connection conn;

    public MemberDao() {
        String url = "jdbc:mariadb://localhost:3306/okky";
        String user = "kjh";
        String password = "9172";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertUser(MemberDto mdto) {
        try {
            String sql = "insert into `okky`.`users` (`email`, `password`, `name`, `nickName`, `telecom`, `contact`, `contactCountryValue`, `policyEmailSend`) values (?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mdto.getEmail());
            pstmt.setString(2, mdto.getPassword());
            pstmt.setString(3, mdto.getName());
            pstmt.setString(4, mdto.getNickName());
            pstmt.setString(5, mdto.getTelecom());
            pstmt.setString(6, mdto.getContact());
            pstmt.setString(7, mdto.getcontactCountryValue());
            pstmt.setBoolean(8, mdto.isPolicyEmailSend());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
