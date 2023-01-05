<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="dataSource"
                   url="jdbc:mariadb://localhost:3306/okky"
                   driver="org.mariadb.jdbc.Driver"
                   user="kjh" password="9172"/>
