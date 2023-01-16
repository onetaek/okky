<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글</title>
    <jsp:include page="../layouts/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/articles/resources/css/article.css"/>
</head>
<body>
<jsp:include page="/layouts/header.jsp"/>
<div id="article-main-wrap">
    <main>
        <div class="article-category">
            <h2><a href="#">커뮤니티</a></h2>
        </div>
        <div class="article-container">
            <div class="article-user-info">
                <a href="#" class="profile"><img src="#" alt="프로필"/></a>
                <div class="article-user-info-etc">
                    <a href="#">${user.name}</a>
                    <div>
                        <span>${articleDto.createdAt}</span>
                        <span>${articleDto.view}</span>
                    </div>
                </div>
            </div>
            <h3 class="article-title">${articleDto.title}</h3>
            <div class="article-content">${articleDto.content}</div>
            <div class="article-bottom">
                <div class="tag-wrap">
                    <c:forEach var="tag" items="${tagOfArticle}">
                        <a href="#" class="tag"><span>${tag}</span></a>
                    </c:forEach>
                </div>
                <div class="btn-like">
                    <button class="btn-left"><i class="fa-solid fa-chevron-down"></i></button>
                    <div class="like-cnt">3</div>
                    <button class="btn-right"><i class="fa-solid fa-chevron-up"></i></button>
                </div>
            </div>
            <c:if test="${user.email == articleDto.userEmail}">
                <div class="btn-update-delete-wrap">
                    <button class="btn-ellipsis"><i class="fa-solid fa-ellipsis"></i></button>
                    <div class="btn-toggle off">
                        <a href="articleUpdateView.do?boardId=${boardId}&articleIndex=${articleDto.index}" class="btn-update"><i class="fa-regular fa-pen-to-square"></i>수정하기</a>
                        <a href="articleDelete.do?articleIndex=${articleDto.index}&boardId=${boardId}" class="btn-delete"><i class="fa-regular fa-trash-can"></i>삭제하기</a>
                    </div>
                </div>
            </c:if>
        </div>
    </main>
</div>
<jsp:include page="/layouts/footer.jsp"/>
<script src="${pageContext.request.contextPath}/articles/resources/js/article.js"></script>
</body>
</html>
