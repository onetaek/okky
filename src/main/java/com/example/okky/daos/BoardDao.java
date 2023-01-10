package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.bbs.BoardDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDao {
    public BoardDao(){
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

    public BoardDto selectBoardById(String boardId) {
        BoardDto dto = null;
        String sql ="select * from boards where id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto = new BoardDto(
                        rs.getString(1),
                        rs.getString(2)
                );
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dto;
    }


}