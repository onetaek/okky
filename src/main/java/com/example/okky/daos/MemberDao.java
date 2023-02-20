package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.members.ContactCountryDto;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.dtos.members.TelecomDto;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private static final MemberDao INSTANCE = new MemberDao();

    public static MemberDao getInstance() {
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


    public void insertmember(MemberDto mdto) throws SQLException, ClassNotFoundException {
        System.out.println("insertmember method start");

        String sql = "insert into `okky`.`members` (`email`, `password`, `name`, `nickName`, `telecom`, `contact`, `contactCountryValue`, `policyEmailSend`) values (?,?,?,?,?,?,?,?)";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, mdto.getEmail());
        pstmt.setString(2, mdto.getPassword());
        pstmt.setString(3, mdto.getName());
        pstmt.setString(4, mdto.getNickName());
        pstmt.setString(5, mdto.getTelecom());
        pstmt.setString(6, mdto.getContact());
        pstmt.setString(7, mdto.getContactCountryValue());
        pstmt.setBoolean(8, mdto.isPolicyEmailSend());

        @Cleanup ResultSet rs = pstmt.executeQuery();

    }

    public List<ContactCountryDto> selectContactCountry() throws SQLException, ClassNotFoundException {
        System.out.println("selectContactCountry method start");
        String sql = "select * from `okky`.`contactcountries`";
        List<ContactCountryDto> contactCountryDtoList = new ArrayList<ContactCountryDto>();
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            ContactCountryDto contactCountryDto = new ContactCountryDto();
            contactCountryDto.setText(rs.getString(1));
            contactCountryDto.setValue(rs.getString(2));
            contactCountryDtoList.add(contactCountryDto);
        }

        return contactCountryDtoList;
    }

    public List<TelecomDto> selectTelecom() throws SQLException, ClassNotFoundException {
        System.out.println("selectTelecom method start");
        List<TelecomDto> telecomDtoList = new ArrayList<>();

        String sql = "select * from `okky`.`telecoms`";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TelecomDto telecomDto = new TelecomDto();
            telecomDto.setText(rs.getString(1));
            telecomDto.setValue(rs.getString(2));
            telecomDtoList.add(telecomDto);
        }
        return telecomDtoList;
    }

    public MemberDto selectmemberById(String id, String password) throws SQLException, ClassNotFoundException {
        MemberDto dto = null;
        String sql = "select * from `okky`.`members` where `email` = ? and `password` = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, password);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("rs.getString(1) = " + rs.getString(1));
            System.out.println("rs.getString(11) = " + rs.getString(11));

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
        return dto;
    }


    public MemberDto selectmember(String email) throws SQLException, ClassNotFoundException {
        MemberDto dto = null;
        String sql = "select * from `okky`.`members` where email = ?";

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);

        @Cleanup ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
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
        return dto;
    }

    public void updatemember(MemberDto existingmember) throws SQLException, ClassNotFoundException {
        String sql = "update `okky`.`members` " +
                "set `name` = ?, " +
                "nickName = ?, " +
                "contact = ?," +
                "`password` = ? " +
                "where email = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, existingmember.getName());
            pstmt.setString(2, existingmember.getNickName());
            pstmt.setString(3, existingmember.getContact());
            pstmt.setString(4, existingmember.getPassword());
            pstmt.setString(5, existingmember.getEmail());
            pstmt.executeUpdate();


    }
}
