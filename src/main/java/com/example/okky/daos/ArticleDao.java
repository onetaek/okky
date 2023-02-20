package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;
import com.example.okky.utils.Pagination;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArticleDao {
    private static final ArticleDao INSTANCE = new ArticleDao();

    public static ArticleDao getInstance() {
        return INSTANCE;
    }

    private ArticleDao() {
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

    public ArrayList<ArticleDto> selectArticleByBoardId(int boardId) throws SQLException, ClassNotFoundException {
        ArrayList<ArticleDto> dtos = new ArrayList<>();

        String sql = "select * from `okky`.`articles` where boardId = ? order by `index` desc";


        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, boardId);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            ArticleDto dto = new ArticleDto(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getDate(7),
                    rs.getBoolean(8)
            );
            dtos.add(dto);
        }

        return dtos;
    }


    public List<TagDto> selectTags() throws SQLException, ClassNotFoundException {
        List<TagDto> dtos = new ArrayList<>();
        String sql = "select * from `okky`.`tags`";

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            TagDto dto = new TagDto(
                    rs.getString(1),
                    rs.getString(2)
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public void insertArticle(int boardId, String title, String content,
                              String userEmail, String[] tags) throws SQLException, ClassNotFoundException {
        System.out.println("insertArticles의 boardId: " + boardId);
        String sql = "insert into `okky`.`articles` (boardId,userEmail," +
                "title,content ) values(?,?,?,?)";

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = null;


        pstmt.setInt(1, boardId);
        pstmt.setString(2, userEmail);
        pstmt.setString(3, title);
        pstmt.setString(4, content);

        pstmt.executeUpdate();


        int articleIndex = 0;
        sql = "select max(`index`) from `okky`.`articles` where userEmail = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userEmail);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            articleIndex = rs.getInt(1);
        }
        sql = "insert into `okky`.`tagofarticle` (articleIdx,tagValue) " +
                "values(?,?)";
        if (tags == null) return;
        for (String tag : tags) {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, articleIndex);
            pstmt.setString(2, tag);
            pstmt.executeUpdate();
        }
    }


    public ArrayList<TagOfArticleDto> selectTagsByBoardId(int boardId) throws SQLException, ClassNotFoundException {
        ArrayList<TagOfArticleDto> dtos = new ArrayList<>();
        String sql = "select `A`.index, `T`.tagValue\n" +
                "from `okky`.`articles` as `A`\n" +
                "    join `okky`.`tagofarticle` as `T` on `A`.`index`  = `T`.`articleIdx` where boardId = ?";

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            TagOfArticleDto dto = new TagOfArticleDto();
            dto.setArticleIdx(rs.getInt(1));
            dto.setTagValue(rs.getString(2));
            dtos.add(dto);
        }
        return dtos;
    }


    public ArticleDto selectArticleByIndex(int articleIndex) throws SQLException, ClassNotFoundException {
        ArticleDto dto = null;

        String sql = "select * from `okky`.`articles` where `index` = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,articleIndex);
        @Cleanup ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            dto = new ArticleDto(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getDate(7),
                    rs.getBoolean(8)
            );
        }
        return dto;
    }


    public ArrayList<String> selectTagsByArticleIdxAndBoardId(int articleIndex, int boardId) throws SQLException, ClassNotFoundException {
        ArrayList<String> tags = new ArrayList<>();
        String sql = "select  t.tagValue from `okky`.`articles` as `a`, `okky`.tagOfArticle as `t`" +
                " where `a`.`index` = `t`.articleIdx and a.`index` = ? and boardId = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, articleIndex);
        pstmt.setInt(2, boardId);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("rs.getSting: " + rs.getString(1));
            tags.add(rs.getString(1));

        }
        return tags;
    }


    public void updateArticle(int articleIndex, String userEmail, String title, String content, String[] tags) throws SQLException, ClassNotFoundException {
        String sql = "update `okky`.`articles` set title = ?, content = ? where `index` = ?";

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, title);
        pstmt.setString(2, content);
        pstmt.setInt(3, articleIndex);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        sql = "delete from `okky`.`tagofarticle` where articleIdx = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, articleIndex);
        pstmt.executeUpdate();

        for (String tag : tags) {
            System.out.println("tag = " + tag);
            sql = "insert into `okky`.`tagofarticle`(articleIdx, tagValue) values (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, articleIndex);
            pstmt.setString(2, tag);
            pstmt.executeUpdate();
        }
    }

    public void deleteArticle(int articleIndex) throws SQLException, ClassNotFoundException {
        String sql = "update `okky`.`articles` set status = ? where `index` = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setBoolean(1, false);
        pstmt.setInt(2, articleIndex);

        @Cleanup ResultSet rs = pstmt.executeQuery();

    }

    public List<ArticleDto> selectAllArticle() throws SQLException, ClassNotFoundException {
        ArrayList<ArticleDto> dtos = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            String sql = "select * from `okky`.`articles`" +
                    " where boardId = " + i + " order by `index` desc limit 5";
            @Cleanup Connection conn = JDBCConnection.getConnection();
            @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

            @Cleanup ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ArticleDto dto = new ArticleDto(
                        rs.getInt("index"),
                        rs.getInt("boardId"),
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

        return dtos;
    }

    public BoardDto selectBoardById(int boardId) throws SQLException, ClassNotFoundException {
        String sql = "select * from `okky`.boards where id = ?";
        BoardDto dto = null;
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, boardId);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            dto = new BoardDto(
                    rs.getInt("id"),
                    rs.getString("text"));
        }
        return dto;
    }

    public List<BoardDto> selectAllBoard() throws SQLException, ClassNotFoundException {
        String sql = "select * from `okky`.boards";
        ArrayList<BoardDto> dtos = new ArrayList<>();
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            BoardDto dto = new BoardDto(
                    rs.getInt(1),
                    rs.getString(2)
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public List<TagOfArticleDto> selectAllTag() throws SQLException, ClassNotFoundException {
        String sql = "select * from `okky`.`tagofarticle`";
        ArrayList<TagOfArticleDto> dtos = new ArrayList<>();
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TagOfArticleDto dto = new TagOfArticleDto(
                    rs.getInt("index"),
                    rs.getInt("articleIdx"),
                    rs.getString("tagValue"),
                    rs.getDate("writtenAt")
            );
            dtos.add(dto);
        }

        return dtos;
    }

    public int selectArticleTotalCount(int boardId, String tagValue) throws SQLException, ClassNotFoundException {
        String sql = null;

        if (tagValue == null || tagValue.equals("")) {
            log.info("select count(*) from `okky`.articles where boardId = ?");
            sql = "select count(*) from `okky`.articles where boardId = ?";

        } else {
            sql = "select count(0)\n" +
                    "FROM `okky`.`articles`\n" +
                    "LEFT JOIN `okky`.`tagOfArticle` as `ta` on articles.`index` = ta.articleIdx where `tagValue` = ?";
        }
        int count = 0;

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        if (tagValue == null || tagValue.equals("")) {
            pstmt.setInt(1, boardId);
        } else {
            pstmt.setString(1, tagValue);
        }

        @Cleanup ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    public ArrayList<ArticleDto> selectArticleByPageNum(int boardId, int startRow, int pageSize, String keyWord) throws SQLException, ClassNotFoundException {
        ArrayList<ArticleDto> dtos = new ArrayList<>();
        String sql = null;
        if (keyWord == null) {
            sql = "select * from `okky`.articles where boardId = ? order by `index` desc limit ? , ?";
        } else {
            sql = "select * from `okky`.articles where boardId = ? and " +
                    "(title like concat('%'," + keyWord + ",'%') or content like concat('%'," + keyWord + ",'%')) limit ? , ?";
        }
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, boardId);
        pstmt.setInt(2, startRow - 1);
        pstmt.setInt(3, pageSize);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            ArticleDto dto = new ArticleDto(
                    rs.getInt("index"),
                    rs.getInt("boardId"),
                    rs.getString("userEmail"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("view"),
                    rs.getDate("createdAt"),
                    rs.getBoolean("status")
            );
            dtos.add(dto);
        }
        return dtos;
    }


    public int selectLikeByCount(int articleIndex) throws SQLException, ClassNotFoundException {
        int count = 0;
        String sql = "select count(0) from `okky`.`articleLikes` where `articleIndex` = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, articleIndex);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }


    public void insertLike(String userEmail, int articleIndex) throws SQLException, ClassNotFoundException {
        String sql = "insert into `okky`.`articleLikes` (userEmail, articleIndex) values (?,?)";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userEmail);
        pstmt.setInt(2, articleIndex);

        @Cleanup ResultSet rs = pstmt.executeQuery();

    }


    public void deleteLike(String userEmail, int articleIndex) throws SQLException, ClassNotFoundException {
        String sql = "delete from `okky`.`articleLikes` where `userEmail` = ? and `articleIndex` = ?";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userEmail);
        pstmt.setInt(2, articleIndex);

        @Cleanup ResultSet rs = pstmt.executeQuery();

    }


    public List<ArticleDto> selectTagOfArticle(String tagValue, int startRow, int pageSize, String keyWord) throws SQLException, ClassNotFoundException {
        List<ArticleDto> dtos = new ArrayList<>();

        String sql = null;

        if (keyWord == null) {
            sql = "select `articles`.`index`,\n" +
                    "       `boardId`,\n" +
                    "       `userEmail`,\n" +
                    "       `title`,\n" +
                    "       `content`,\n" +
                    "       `view`,\n" +
                    "       `createdAt`,\n" +
                    "       `status`\n" +
                    "FROM `okky`.`articles`\n" +
                    "LEFT JOIN `okky`.`tagOfArticle` as `ta` on articles.`index` = ta.articleIdx where `tagValue` = ? order by `index` desc limit ? , ?";
        } else {
            sql = "select `articles`.`index`,\n" +
                    "       `boardId`,\n" +
                    "       `userEmail`,\n" +
                    "       `title`,\n" +
                    "       `content`,\n" +
                    "       `view`,\n" +
                    "       `createdAt`,\n" +
                    "       `status`\n" +
                    "FROM `okky`.`articles`\n" +
                    "LEFT JOIN `okky`.`tagOfArticle` as `ta` on articles.`index` = ta.articleIdx where `tagValue` = ? " +
                    "and \n" +
                    "(title like concat('%', " + keyWord + ",'%') or content like concat('%'," + keyWord + ",'%')) limit ? , ?";
        }

        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tagValue);
        pstmt.setInt(2, startRow - 1);
        pstmt.setInt(3, pageSize);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            ArticleDto adto = new ArticleDto(
                    rs.getInt("index"),
                    rs.getInt("boardId"),
                    rs.getString("userEmail"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("view"),
                    rs.getDate("createdAt"),
                    rs.getBoolean("status")
            );
            dtos.add(adto);
        }

        return dtos;
    }
}
