package com.example.okky.daos;

import com.example.okky.DBConntection.JDBCConnection;
import com.example.okky.vo.TagVo;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDao {
    private static final TagDao INSTANCE = new TagDao();

    public static TagDao getInstance() {
        return INSTANCE;
    }

    private TagDao() {
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

    public List<TagVo> selectTagTop5() throws SQLException, ClassNotFoundException {
        List<TagVo> vos = new ArrayList<>();
        String sql = "select `tagValue`, count(*) as `count` from `okky`.tagOfArticle group by `tagValue` order by count(*) desc limit 5";
        @Cleanup Connection conn = JDBCConnection.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            TagVo vo = new TagVo(
                    rs.getString("tagValue"),
                    rs.getInt("count")
            );
            vos.add(vo);
        }
        return vos;
    }


}
