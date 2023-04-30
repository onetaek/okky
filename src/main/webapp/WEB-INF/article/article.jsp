<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시글</title>
    <jsp:include page="../layouts/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/articles/article.css"/>
    <script defer src="${pageContext.request.contextPath}/resources/js/articles/article.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/layouts/header.jsp"/>
<div id="article-main-wrap">
    <main>
        <div class="article-category">
            <h2><a href="#">${board.text}</a></h2>
        </div>
        <div class="article-container">
            <div class="article-user-info">
                <a href="#" class="profile"><img src="#" alt="프로필"/></a>
                <div class="article-user-info-etc">
                    <a href="#">${sessionMember.name}</a>
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
                    <button class="btn-left down"><i class="fa-solid fa-chevron-down"></i></button>
                    <div class="like-cnt"></div>
                    <button class="btn-right up"><i class="fa-solid fa-chevron-up"></i></button>
                </div>
            </div>


            <c:if test="${sessionMember.email == articleDto.memberEmail}">
                <div class="btn-update-delete-wrap">
                    <button class="btn-ellipsis"><i class="fa-solid fa-ellipsis"></i></button>
                    <div class="btn-toggle off">
                        <a href="/article/update?boardId=${boardId}&articleIndex=${articleDto.index}" class="btn-update">
                            <i class="fa-regular fa-pen-to-square"></i>수정하기
                        </a>
                        <span href="/article/delete?articleIndex=${articleDto.index}&boardId=${boardId}" class="btn-delete">
                            <i class="fa-regular fa-trash-can"></i>삭제하기
                        </span>
                    </div>
                </div>
                <form id="formDelete">
                    <input type="hidden" name="boardId">
                    <input type="hidden" name="articleIndex">
                </form>
            </c:if>
        </div>

        <form class="articleForm" id="form" method="post">
            <c:if test="${sessionMember != null}">
                <textarea class="textContent" type="text" cols="90" rows="5" name="content"></textarea>
                <input class="submitBtn" name="insertBtn" type="button" value="전송">
                <input type="hidden" name="boardId" value="${boardId}">
                <input class="articleIndex" type="hidden" name="articleIndex" value="${articleDto.index}">
                <input type="hidden" name="memberEmail" value="${sessionMember.email}">
                <input type="hidden" name="userNickName" value="${sessionMember.nickName}">
            </c:if>
            <c:if test="${sessionMember == null}">
                <textarea class="textContent" type="text" cols="90" rows="5" name="content" placeholder="로그인을 하여야만 작성하실 수 있습니다." disabled></textarea>
                <input class="submitBtn" type="submit" value="전송" disabled>
            </c:if>
        </form>

    </main>
</div>
<div class="commentContainerWrap">
    <c:forEach var="comment" items="${commentList}" >
        <div class="commentContainer">
            <div class="replyComment" >
                <div class="comment_nickNameAndTime"></div>
                <div class="comment_content"></div>
                <span class="comment_delete_btn">삭제</span>
                <span class="replyBtn">답글</span>
            </div>
            <form class="replyInsert">
                <input class="comment_group" type="hidden" value="">
                <input class="comment_sequence" type="hidden" value="">
                <input class="comment_level" type="hidden" value="" >
                <input class="comment_boardId" type="hidden" value="" >
                <input class="comment_articleIndex" type="hidden" value="">
                <input class="comment_memberEmail" type="hidden" value="" >

                <textarea class="textContent" type="text" cols="90" rows="5" name="content"></textarea>
                <input class="submitBtn" type="submit" value="전송">
            </form>
        </div>

    </c:forEach>
</div>


<jsp:include page="/WEB-INF/layouts/footer.jsp"/>


<%--<script>--%>
<%--    window.addEventListener("DOMContentLoaded", function (){--%>
<%--        selectLikeCount();--%>
<%--        up.addEventListener('click', likeUp);--%>
<%--        down.addEventListener('click', likeDown);--%>
<%--    });--%>
<%--    const up = window.document.querySelector('.up');--%>
<%--    const down = window.document.querySelector('.down');--%>

<%--    const form = window.document.getElementById('form');--%>
<%--    const articleIndex = form['articleIndex'];--%>
<%--    const memberEmail = form['memberEmail'];--%>
<%--    const likeCount = window.document.querySelector('.like-cnt');--%>
<%--    console.log(up, down);--%>
<%--    function likeUp () {--%>
<%--        const xhr = new XMLHttpRequest();--%>
<%--        xhr.open('post',"ArticleLikeView.do?action=up&memberEmail="+memberEmail.value+"&articleIndex="+articleIndex.value)--%>
<%--        xhr.send();--%>
<%--        xhr.onreadystatechange = () => {--%>

<%--            if (xhr.readyState !== XMLHttpRequest.DONE) return;--%>
<%--            if (xhr.status >= 200 && xhr.status < 300) {--%>
<%--                const responseJson = xhr.responseText;--%>
<%--                console.log(responseJson);--%>
<%--                likeCount.innerHTML = "";--%>
<%--                likeCount.innerText = responseJson;--%>
<%--                selectLikeCount();--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>

<%--    function likeDown() {--%>
<%--        const xhr = new XMLHttpRequest();--%>
<%--        xhr.open('post',"ArticleLikeView.do?action=down&memberEmail="+memberEmail.value+"&articleIndex="+articleIndex.value)--%>
<%--        xhr.send();--%>
<%--        xhr.onreadystatechange = () => {--%>

<%--            if (xhr.readyState !== XMLHttpRequest.DONE) return;--%>
<%--            if (xhr.status >= 200 && xhr.status < 300) {--%>
<%--                const responseJson = xhr.responseText;--%>
<%--                console.log(responseJson);--%>
<%--                likeCount.innerHTML = "";--%>
<%--                likeCount.innerText = responseJson;--%>
<%--                selectLikeCount();--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>


<%--    function selectLikeCount() {--%>
<%--        const xhr = new XMLHttpRequest();--%>
<%--        xhr.open('GET', "ArticleLikeView.do?articleIndex="+articleIndex.value);--%>
<%--        xhr.send();--%>
<%--        xhr.onreadystatechange = () => {--%>
<%--            if (xhr.readyState !== XMLHttpRequest.DONE) return;--%>

<%--            if (xhr.status >= 200 && xhr.status < 300) {--%>
<%--                const responseJson = xhr.responseText;--%>
<%--                console.log(responseJson);--%>
<%--                    likeCount.innerHTML = "";--%>
<%--                    likeCount.innerText = responseJson;--%>


<%--            }--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>
