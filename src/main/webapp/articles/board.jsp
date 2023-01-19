<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Q&A게시판</title>
    <jsp:include page="../layouts/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/articles/resources/css/community.css">
</head>
<body>
<jsp:include page="../layouts/header.jsp"/>
<main id="main">
    <jsp:include page="../layouts/aside.jsp"/>
    <!-- section community -->
    <div id="sectionWrap">
        <section id="section1">
            <div class="title">
                <span>${boardDto.text}</span>
                <c:set var="boardId" value="${boardDto.value}"/>

                <c:choose>
                    <c:when test="${boardId == 1}">
                        <span>좋은 질문과 답변으로 동료의 시간을 아껴주세요.</span>
                    </c:when>
                    <c:when test="${boardId == 2}">
                        <span>다양한 사람을 만나고 생각의 폭을 넓혀보세요.</span>
                    </c:when>
                    <c:when test="${boardId == 3}">
                        <span>OKKY의 새소식, 이벤트, 행사 정보를 공유하는 공간입니다.</span>
                    </c:when>
                </c:choose>


            </div>
            <div id="top-container">
                <div class="container">
                    <a class="w_btn" href="writeView.do?boardId=${boardDto.value}">
                        <i class="icon fa-regular fa-pen-to-square"></i>
                        작성하기
                    </a>
                </div>


                <ul class="select">
                    <li class="item">
                        <i class="icon fa-solid fa-bars"></i>
                        최신순
                    </li>
                </ul>
            </div>

            <div class="search-container">
                <div class="reset">
                    <i class="icon fa-solid fa-repeat"></i>
                </div>

                <form class="search-c focus">
                    <input type="hidden" name="boardId" id="boardId" value="${boardDto.value}"/>
                    <i class="icon fa-solid fa-magnifying-glass"></i>
                    <input class="search-input" type="search" placeholder="Q&A 내에서 검색">
                    <!--<input class="s-btn" type="submit" value="검색">-->
                    <button class="s-btn" type="button">검색</button>
                </form>

                <div class="page-container">
                    <span>1 / 11800 페이지</span>
                    <a href="#" class="left">
                        <i class="icon fa-solid fa-arrow-left"></i>
                    </a>
                    <a href="#" class="right">
                        <i class="icon fa-solid fa-arrow-right"></i>
                    </a>
                </div>
            </div>

            <c:forEach var="article" items="${articleDtoList}">
                <c:if test="${article.status == true}">


                <div class="board-container">
                    <ul class="view-container">
                        <li>
                            <a href="#">
                                <img src="./img/logo.svg" alt="profile">
                            </a>
                        </li>
                        <li>
                            <i class="fa-solid fa-bolt"></i>
                            <span>4.7k</span>
                        </li>
                        <li>
                            <span><c:out value="${article.createdAt}"/></span>
                        </li>
                    </ul>
                    <div class="title-container">
                        <a href="articleView.do?articleIndex=${article.index}&boardId=${boardDto.value}"><p><c:out value="${article.title}"/></p></a>
                    </div>
                    <ul class="tag-container">
                        <c:forEach var="t" items="${tagsList}">
                            <c:if test="${article.index == t.articleIdx}">
                                <li><a href="#">${t.tagValue}</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>

                <%--        <c:if  article.index == tagifarticle.index>--%>
                <%--        tags.value--%>
                </c:if>
            </c:forEach>


        </section>
    </div>
</main>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
