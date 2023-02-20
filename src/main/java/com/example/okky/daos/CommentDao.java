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
import java.util.List;

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
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getDate(10)
            );
        }
        return dto;
    }

    public int commentInsert(
            int boardId,
            int articleIndex,
            String memberEmail,
            String memberNickName,
            String content
    ) throws SQLException, ClassNotFoundException {
        CommentDto dto = null;
        int maxGroup = 0;

        String sql1 = "select  max(`group`) from `okky`.comment where boardId = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql1);
        pstmt.setInt(1, boardId);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            maxGroup = rs.getInt(1);
        }


        String sql = "insert into `okky`.comment (`index`,`group`, `sequence`, " +
                "`level`, `boardId`, `articleIndex`, `memberEmail`, `memberNickName`, " +
                "`content`, `createdAt`) values(null, ? ,default,default,?,?,?,?,?,default) ";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, maxGroup + 1);
        pstmt.setInt(2, boardId);
        pstmt.setInt(3, articleIndex);
        pstmt.setString(4, memberEmail);
        pstmt.setString(5, memberNickName);
        pstmt.setString(6, content);
        int count = pstmt.executeUpdate();
        return count;
    }

    public List<CommentDto> selectCommentByArticleIndex(int articleIndex) throws SQLException, ClassNotFoundException {
        List<CommentDto> list = new ArrayList<>();

        String sql = "select * from `okky`.`comment` where `articleIndex` = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,articleIndex);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            CommentDto cdto = CommentDto.builder()
                    .index(rs.getInt("index"))
                    .group(rs.getInt("group"))
                    .sequence(rs.getInt("sequence"))
                    .level(rs.getInt("level"))
                    .boardId(rs.getInt("boardId"))
                    .articleIndex(rs.getInt("articleIndex"))
                    .memberEmail(rs.getString("memberEmail"))
                    .memberNickName(rs.getString("memberNickName"))
                    .content(rs.getString("content"))
                    .createdAt(rs.getDate("createdAt"))
                    .build();
            list.add(cdto);
        }
        return list;
    }

    public boolean commentDeleteByIndex(int index) throws SQLException, ClassNotFoundException {
        String sql = "delete from `okky`.comment where `index` = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,index);
        int count = pstmt.executeUpdate();
        return count == 1;
    }

}
