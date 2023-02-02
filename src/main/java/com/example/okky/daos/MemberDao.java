package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.members.ContactCountryDto;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.dtos.members.TelecomDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private static final MemberDao INSTANCE = new MemberDao();
    public static MemberDao getInstance(){
        return INSTANCE;
    }
    private MemberDao() {
        connect();
    }
    PreparedStatement pstmt = null;
    Connection conn;
    ResultSet rs = null;

    void connect() {
        try {
            conn = JDBCConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void insertUser(MemberDto mdto) {
        System.out.println("insertUser method start");
        try {
            String sql = "insert into `okky`.`users` (`email`, `password`, `name`, `nickName`, `telecom`, `contact`, `contactCountryValue`, `policyEmailSend`) values (?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, mdto.getEmail());
            pstmt.setString(2, mdto.getPassword());
            pstmt.setString(3, mdto.getName());
            pstmt.setString(4, mdto.getNickName());
            pstmt.setString(5, mdto.getTelecom());
            pstmt.setString(6, mdto.getContact());
            pstmt.setString(7, mdto.getContactCountryValue());
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
        List<TelecomDto> telecomDtoList = new ArrayList<>();
        try {
            String sql = "select * from `okky`.`telecoms`";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                TelecomDto telecomDto = new TelecomDto();
                telecomDto.setText(rs.getString(1));
                telecomDto.setValue(rs.getString(2));
                telecomDtoList.add(telecomDto);
            }

        }catch (Exception e){
            System.out.println("selectTelecom method ERROR");
            e.printStackTrace();
        }
        return telecomDtoList;
    }

    public MemberDto selectUserById(String id, String password) {
        MemberDto dto = null;
        String sql = "select * from `okky`.`users` where `email` = ? and `password` = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if(rs.next()){
                System.out.println("rs.getString(1) = " + rs.getString(1));
                System.out.println("rs.getString(11) = " + rs.getString(11));
//                dto = new MemberDto(
//                        rs.getString(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getString(7),
//                        rs.getBoolean(8),
//                        rs.getDate(9),
//                        rs.getString(10),
//                        rs.getBoolean(11)
//                );
                dto = new MemberDto();
                dto.setEmail(rs.getString(1));
                dto.setPassword(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setNickName(rs.getString(4));
                dto.setTelecom(rs.getString(5));
                dto.setcontactCountryValue(rs.getString(6));
                dto.setContact(rs.getString(7));
                dto.setPolicyEmailSend(rs.getBoolean(8));
                dto.setCreatedAt(rs.getDate(9));
                dto.setStatusValue(rs.getString(10));
                dto.setAdmin(rs.getBoolean(11));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }


    public MemberDto selectUser(String email) {
        MemberDto dto = null;
        String sql = "select * from `okky`.`users` where email = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto = new MemberDto(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getDate(9),
                        rs.getString(10),
                        rs.getBoolean(11)
                        );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public void updateUser(MemberDto existingUser) {
        String sql = "update `okky`.`users` " +
                "set `name` = ?, " +
                "nickName = ?, " +
                "contact = ?," +
                "`password` = ? " +
                "where email = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,existingUser.getName());
            pstmt.setString(2,existingUser.getNickName());
            pstmt.setString(3,existingUser.getContact());
            pstmt.setString(4,existingUser.getPassword());
            pstmt.setString(5,existingUser.getEmail());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
