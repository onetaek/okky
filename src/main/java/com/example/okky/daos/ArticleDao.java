package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.TagDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        String sql = "select * from `okky`.`articles` where boardId = ?";

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
                        rs.getInt(6),
                        rs.getDate(7)
                );
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dtos;
    }


    public List<TagDto> selectTags() {
        List<TagDto> dtos = new ArrayList<>();
        String sql = "select * from `okky`.`tags`";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                TagDto dto = new TagDto(
                  rs.getString(1),
                  rs.getString(2)
                );
                dtos.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }

    public void insertArticle(String boardId,String title,String content,
                              String userEmail,String[] tags ) {
        System.out.println("insertArticles의 boardId: "+boardId);
        String sql = "insert into `okky`.`articles` (boardId,userEmail," +
                "title,content ) values(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardId);
            pstmt.setString(2,userEmail);
            pstmt.setString(3,title);
            pstmt.setString(4,content);
            pstmt.executeUpdate();






            int articleIndex = 0;
            System.out.println("articleIndex1: "+articleIndex);
            sql = "select max(`index`) from `okky`.`articles`";
            System.out.println("articleIndex2: "+articleIndex);
            pstmt = conn.prepareStatement(sql);
            System.out.println("000000000000000");
            rs = pstmt.executeQuery();
            System.out.println("01010101010101010");
            if(rs.next()){
                articleIndex = rs.getInt(1);
                System.out.println("articleIndex: "+articleIndex);
            }
            sql = "insert into `okky`.`tagofarticle` (articleIdx,tagValue) " +
                    "values(?,?)";
            for(String tag:tags){
                System.out.println("tag: "+tag);
                pstmt = conn.prepareStatement(sql);
                System.out.println("1111111111");
                pstmt.setInt(1,articleIndex);
                System.out.println("22222222222");
                pstmt.setString(2,tag);
                System.out.println("333333333333");
                pstmt.executeUpdate();
                System.out.println("4444444444444");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
