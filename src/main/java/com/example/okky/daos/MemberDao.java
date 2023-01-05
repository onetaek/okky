package com.example.okky.daos;

import com.example.okky.dtos.members.ContactCountryDto;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.dtos.members.TelecomDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    PreparedStatement pstmt;
    ResultSet rs;
    Connection conn;

    public MemberDao() {
        String url = "jdbc:mariadb://localhost:3306/okky";
        String user = "root";
        String password = "dpdlvldzm419!";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("db connect success!");
        } catch (Exception e) {
            System.out.println("db connect failed");
            e.printStackTrace();
        }
    }

    public void insertUser(MemberDto mdto) {
        System.out.println("insertUser method start");
        try {
            String sql = "insert into `okky`.`users` (`email`, `password`, `name`, `nickName`, `telecom`, `contact`, `contact`, `policyEmailSend`) values (?,?,?,?,?,?,?,?)";
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
            System.out.println("insertUser method ERROR");
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

    public List<ContactCountryDto> selectContactCountry() {
        System.out.println("selectContactCountry method start");
        String sql = "select * from `okky`.`contactcountries`";
        List<ContactCountryDto> contactCountryDtoList = new ArrayList<ContactCountryDto>();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            while(rs.next()) {
                ContactCountryDto contactCountryDto = new ContactCountryDto();
                contactCountryDto.setText(rs.getString(1));
                contactCountryDto.setValue(rs.getString(2));
                contactCountryDtoList.add(contactCountryDto);
            }
            if(conn != null)
                conn.close();
            if(pstmt!=null)
                pstmt.close();
        }catch (Exception e){
            System.out.println("selectContactCountry method ERROR");
            e.printStackTrace();
        }
        return contactCountryDtoList;
    }

    public List<TelecomDto> selectTelecom() {
        System.out.println("selectTelecom method start");
        List<TelecomDto> telecomDtoList = new ArrayList<TelecomDto>();
        try {
            String sql = "select * from `okky`.`telecoms`";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            while(rs.next()){
                TelecomDto telecomDto = new TelecomDto();
                telecomDto.setText(rs.getString(1));
                telecomDto.setValue(rs.getString(2));
                telecomDtoList.add(telecomDto);
            }
            if(conn!=null)
                conn.close();
            if(pstmt !=null)
                pstmt.close();
        }catch (Exception e){
            System.out.println("selectTelecom method ERROR");
            e.printStackTrace();
        }
        return telecomDtoList;
    }
}
