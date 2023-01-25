package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private static final ArticleDao INSTANCE = new ArticleDao();
    public static ArticleDao getInstance(){
        return INSTANCE;
    }
    private ArticleDao(){
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

        String sql = "select * from `okky`.`articles` where boardId = ? order by `index` desc";

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
                        rs.getDate(7),
                        rs.getBoolean(8)
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
            sql = "select max(`index`) from `okky`.`articles` where userEmail = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userEmail);
            rs = pstmt.executeQuery();
            if(rs.next()){
                articleIndex = rs.getInt(1);
            }
            sql = "insert into `okky`.`tagofarticle` (articleIdx,tagValue) " +
                    "values(?,?)";
            for(String tag:tags){
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,articleIndex);
                pstmt.setString(2,tag);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TagOfArticleDto> selectTagsByBoardId(String boardId) {
        ArrayList<TagOfArticleDto> dtos = new ArrayList<>();
        String sql = "select `A`.index, `T`.tagValue\n" +
                "from `okky`.`articles` as `A`\n" +
                "    join `okky`.`tagofarticle` as `T` on `A`.`index`  = `T`.`articleIdx` where boardId = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                TagOfArticleDto dto = new TagOfArticleDto();
                dto.setArticleIdx(rs.getInt(1));
                dto.setTagValue(rs.getString(2));
                dtos.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }

    public ArticleDto selectArticleByIndex(int articleIndex) {
        ArticleDto dto = null;

        String sql = "select * from `okky`.`articles` where `index` = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,articleIndex);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto = new ArticleDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getBoolean(8)
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public ArrayList<String> selectTagsByArticleIdxAndBoardId(int articleIndex, String boardId) {
        ArrayList<String> tags = new ArrayList<>();
        String sql = "select  t.tagValue from `okky`.`articles` as `a`, `okky`.tagOfArticle as `t`" +
                " where `a`.`index` = `t`.articleIdx and a.`index` = ? and boardId = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,articleIndex);
            pstmt.setString(2,boardId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println("rs.getSting: "+rs.getString(1));
                tags.add(rs.getString(1));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tags;
    }

    public void updateArticle(int articleIndex,String userEmail, String title, String content, String[] tags) {
        String sql = "update `okky`.`articles` set title = ?, content = ? where `index` = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            pstmt.setInt(3,articleIndex);
            pstmt.executeUpdate();

            sql = "delete from `okky`.`tagofarticle` where articleIdx = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, articleIndex);
            pstmt.executeUpdate();

            for(String tag: tags){
                System.out.println("tag = " + tag);
                sql = "insert into `okky`.`tagofarticle`(articleIdx, tagValue) values (?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,articleIndex);
                pstmt.setString(2,tag);
                pstmt.executeUpdate();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteArticle(int articleIndex) {
        String sql = "update `okky`.`articles` set status = ? where `index` = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1,false);
            pstmt.setInt(2,articleIndex);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<ArticleDto> selectAllArticle() {
        ArrayList<ArticleDto> dtos = new ArrayList<>();
        try{
            for(int i = 1 ; i <= 3; i++){
                String sql = "select * from `okky`.`articles`" +
                        " where boardId = "+ i + " order by `index` desc limit 5";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    ArticleDto dto = new ArticleDto(
                            rs.getInt("index"),
                            rs.getString("boardId"),
                            rs.getString("userEmail"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getInt("view"),
                            rs.getDate("createdAt"),
                            rs.getBoolean("status")
                    );
                    dtos.add(dto);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }

    public List<BoardDto> selectAllBoard() {
        String sql = "select * from `okky`.boards";
        ArrayList<BoardDto> dtos = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BoardDto dto = new BoardDto(
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

    public List<TagOfArticleDto> selectAllTag() {
        String sql = "select * from tagofarticle";
        ArrayList<TagOfArticleDto> dtos = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                TagOfArticleDto dto = new TagOfArticleDto(
                        rs.getInt("index"),
                        rs.getInt("articleIdx"),
                        rs.getString("tagValue"),
                        rs.getDate("writtenAt")
                );
                dtos.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }

    public int selectArticleTotalCount(String boardId) {
        String sql = "select count(*) from articles where boardId = ?";
        int count = 0;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public List<ArticleDto> selectArticleByPageNum(String boardId,int startNum, int countListPerPage,String keyWord) {
        List<ArticleDto> dtos = new ArrayList<>();
        String sql = null;
        if(keyWord == null){
            sql = "select * from `okky`.articles where boardId = ? limit ? , ?";
        }else{
            sql = "select * from `okky`.articles where boardId = ? and " +
                    "(title like concat('%',"+keyWord+",'%') or content like concat('%',"+keyWord+",'%')) limit ? , ?";
        }
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardId);
            pstmt.setInt(2,startNum);
            pstmt.setInt(3,countListPerPage);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ArticleDto dto = new ArticleDto(
                        rs.getInt("index"),
                        rs.getString("boardId"),
                        rs.getString("userEmail"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("view"),
                        rs.getDate("createdAt"),
                        rs.getBoolean("status")
                );
                dtos.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }
}
