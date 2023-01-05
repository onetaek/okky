<%@ page import="com.example.okky.dtos.members.MemberDto" %>
<%@ page import="com.example.okky.dtos.members.TelecomDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 2023/01/05
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="dataSource"
                   url="jdbc:mariadb://localhost:3306/okky"
                   driver="org.mariadb.jdbc.Driver"
                   user="kjh" password="9172"/>
<%@include file="./dbConn.jsp"%>
<%--<sql:query dataSource="${dataSource}" var="resultSet">--%>
<%--  select * form `okky`.`telecoms`--%>
<%--</sql:query>--%>

<%--<sql:update dataSource="${dataSource}" var="resultSet">--%>
<%--  insert into `WebMarketDB`.`members` values (?,?,?,?,?,?,?,?,NOW())--%>
<%--  <sql:param value="<%=id%>"/>--%>
<%--  <sql:param value="<%=password%>"/>--%>
<%--  <sql:param value="<%=name%>"/>--%>
<%--  <sql:param value="<%=gender%>"/>--%>
<%--  <sql:param value="<%=birth%>"/>--%>
<%--  <sql:param value="<%=mail%>"/>--%>
<%--  <sql:param value="<%=phone%>"/>--%>
<%--  <sql:param value="<%=address%>"/>--%>
<%--</sql:update>--%>
<%
  String sql = "select * from `okky`.`telecoms`";
  pstmt = conn.prepareStatement(sql);
  rs = pstmt.executeQuery();

  ArrayList<String> list = new ArrayList<>();

  while (rs.next()) {
    list.add(rs.getString("value"));
  }
%>
  <form>
  <select name="tel" id="tel">
    <c:forEach var="l" items="<%=list%>">
      <option value="${l}"> ${l} </option>
    </c:forEach>
<%--    <option value="<%=list.get(0)%>"><%=list.get(0)%></option>--%>
<%--    <option value="<%=list.get(1)%>"><%=list.get(1)%></option>--%>
<%--    <option value="<%=list.get(2)%>"><%=list.get(2)%></option>--%>
<%--    <option value="<%=list.get(3)%>"><%=list.get(3)%></option>--%>
  </select>
  </form>


<%--<%--%>
<%--  }--%>
<%--%>--%>
