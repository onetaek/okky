<%@ page import="java.sql.*" %>
<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        String url = "jdbc:mariadb://localhost:3306/okky";
        String user = "kjh";
        String password  = "9172";

        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
    } catch (SQLException e ) {
        out.println("데이터 베이스 연결 실패!");
        out.println("SQLException : " + e.getMessage());
    }
%>
