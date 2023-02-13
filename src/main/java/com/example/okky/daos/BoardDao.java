package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.bbs.BoardDto;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDao {
    private static final BoardDao INSTANCE = new BoardDao();

    public static BoardDao getInstance() {
        return INSTANCE;
    }


    private BoardDao() {
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

    public BoardDto selectBoardById(String boardId) throws SQLException, ClassNotFoundException {
        BoardDto dto = null;
        String sql = "select * from `okky`.`boards` where id = ?";

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, boardId);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            dto = new BoardDto(
                    rs.getString(1),
                    rs.getString(2)
            );
        }
        return dto;
    }


}