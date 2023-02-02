<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside id="aside">
    <h3>인기태그</h3>
    <ul>
        <c:forEach var="t" items="${tagRank}">
            <li><a href="#">${t.tagValue}</a><span>${t.tagRankCount}</span></li>
        </c:forEach>
    </ul>
</aside>
