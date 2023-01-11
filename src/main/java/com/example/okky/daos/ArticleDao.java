package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.bbs.ArticleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleDao {
    public ArticleDao(){
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

    public ArrayList<ArticleDto> selectArticleByBoardId(String boardId) {
        ArrayList<ArticleDto> dtos = new ArrayList<>();

        String sql = "select * from articles where boardId = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ArticleDto dto = new ArticleDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8)
                );
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dtos;
    }



}
