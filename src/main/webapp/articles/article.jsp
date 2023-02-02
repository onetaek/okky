<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h2><a href="#">${board.text}</a></h2>
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
                    <button class="btn-left down"><i class="fa-solid fa-chevron-down"></i></button>
                    <div class="like-cnt"></div>
                    <button class="btn-right up"><i class="fa-solid fa-chevron-up"></i></button>
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
        <form action="/CommentInsert.do" id="form" method="post">
            <input type="text" name="content">
            <input type="hidden" name="boardId" value="${boardId}">
            <input type="hidden" name="articleIndex" value="${articleDto.index}">
            <input type="hidden" name="userEmail" value="${user.email}">
            <input type="hidden" name="userNickName" value="${user.nickName}">
            <input type="submit" value="전송">
        </form>
    </main>
</div>
<jsp:include page="/layouts/footer.jsp"/>
<script defer src="${pageContext.request.contextPath}/articles/resources/js/article.js"></script>

<script>
    window.addEventListener("DOMContentLoaded", function (){
        selectLikeCount();
        up.addEventListener('click', likeUp);
        down.addEventListener('click', likeDown);
    });
    const up = window.document.querySelector('.up');
    const down = window.document.querySelector('.down');

    const form = window.document.getElementById('form');
    const articleIndex = form['articleIndex'];
    const userEmail = form['userEmail'];
    const likeCount = window.document.querySelector('.like-cnt');
    console.log(up, down);
    function likeUp () {
        const xhr = new XMLHttpRequest();
        xhr.open('post',"ArticleLikeView.do?action=up&userEmail="+userEmail.value+"&articleIndex="+articleIndex.value)
        xhr.send();
        xhr.onreadystatechange = () => {

            if (xhr.readyState !== XMLHttpRequest.DONE) return;
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = xhr.responseText;
                console.log(responseJson);
                likeCount.innerHTML = "";
                likeCount.innerText = responseJson;
                selectLikeCount();
            }
        }
    }

    function likeDown() {
        const xhr = new XMLHttpRequest();
        xhr.open('post',"ArticleLikeView.do?action=down&userEmail="+userEmail.value+"&articleIndex="+articleIndex.value)
        xhr.send();
        xhr.onreadystatechange = () => {

            if (xhr.readyState !== XMLHttpRequest.DONE) return;
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = xhr.responseText;
                console.log(responseJson);
                likeCount.innerHTML = "";
                likeCount.innerText = responseJson;
                selectLikeCount();
            }
        }
    }


    function selectLikeCount() {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', "ArticleLikeView.do?articleIndex="+articleIndex.value);
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState !== XMLHttpRequest.DONE) return;

            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = xhr.responseText;
                console.log(responseJson);
                    likeCount.innerHTML = "";
                    likeCount.innerText = responseJson;


            }
        }
    }
</script>
</body>
</html>
