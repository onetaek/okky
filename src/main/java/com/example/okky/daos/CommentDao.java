package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.CommentDto;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDao {

    private static final CommentDao INSTANCE = new CommentDao();

    public static CommentDao getInstance() {
        return INSTANCE;
    }

    private CommentDao() {
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


    public CommentDto selectParentComment(int index) throws SQLException, ClassNotFoundException {
        String sql = "select * from `okky`.`comment` where `index` = ?";

        CommentDto dto = null;
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, index);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            dto = new CommentDto(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getDate(10)
            );
        }
        return dto;
    }

    public void commentInsert(
            String boardId,
            int articleIndex,
            String userEmail,
            String userNickName,
            String content

    ) throws SQLException {
        CommentDto dto = null;
        int maxGroup = 0;
        try {
            String sql = "select  max(`group`) from `okky`.comment where boardId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxGroup = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }


        try {
            String sql = "insert into `okky`.comment (`index`,`group`, `sequence`, " +
                    "`level`, `boardId`, `articleIndex`, `userEmail`, `userNickName`, " +
                    "`content`, `createdAt`) values(null, ? ,default,default,?,?,?,?,?,default) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maxGroup + 1);
            pstmt.setString(2, boardId);
            pstmt.setInt(3, articleIndex);
            pstmt.setString(4, userEmail);
            pstmt.setString(5, userNickName);
            pstmt.setString(6, content);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

}
