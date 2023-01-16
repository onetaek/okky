<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글</title>
</head>
<body>
    <h1>${user.nickName}님의 게시글</h1>
    <hr>
        index: ${articleDto.index}
        userEmail:${articleDto.userEmail}
        title: ${articleDto.title}
        content: ${articleDto.content}
        view: ${articleDto.view}
        <hr>
        태그:
        <c:forEach var="tag" items="${tagOfArticle}">
            ${tag}
        </c:forEach>
        <br>
        <c:if test="${user.email == articleDto.userEmail}">
            <a href="articleUpdateView.do?boardId=${boardId}&articleIndex=${articleDto.index}">수정</a>
            <a href="articleDelete.do?articleIndex=${articleDto.index}&boardId=${boardId}">삭제</a>
        </c:if>

    <hr>

</body>
</html>
