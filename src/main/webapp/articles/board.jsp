<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${boardDto.text}</title>
    <jsp:include page="../layouts/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/articles/resources/css/community.css">
<%--    <script src="${pageContext.request.contextPath}/articles/resources/js/board.js" defer></script>--%>
</head>
<body>
<jsp:include page="../layouts/header.jsp"/>
<main id="main">
    <jsp:include page="../layouts/aside.jsp"/>
    <!-- section community -->
    <div id="sectionWrap">
        <section id="section1">
            <div class="title">

                <c:if test="${boardId != 4}">
                    <span>${boardDto.text}</span>
                </c:if>
                <c:if test="${boardId == 4}">
                    <span>${tagValue} 관련 게시글</span>
                </c:if>
                    <c:set var="boardId" value="${boardDto.id}"/>
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
                        <c:when test="${boardId == 4}">
                            <span>많은 사람들의 궁금한 사항을 함께 공유해 봅시다.</span>
                        </c:when>
                    </c:choose>


            </div>




            <div id="top-container">
                <div class="container">
                    <a class="w_btn" href="writeView.do?boardId=${boardId}">
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

                <form class="search-c focus" name="searchForm" action="articleListView.do">
                    <input type="hidden" name="boardId" id="boardId" value="${boardDto.id}"/>
                    <input type="hidden" name="pageNum" class="p-page-num" value="${p.pageNum}"/>
<%--                    <input type="hidden" class="p-start-page" value="${p.startPage}"/>--%>
<%--                    <input type="hidden" class="p-end-page" value="${p.endPage}"/>--%>
<%--                    <input type="hidden" class="p-total-count" value="${p.totalCount}">--%>
                    <i class="icon fa-solid fa-magnifying-glass"></i>
                    <input class="search-input" name="keyWord" type="search" placeholder="Q&A 내에서 검색">
                    <!--<input class="s-btn" type="submit" value="검색">-->
                    <button class="s-btn" type="submit">검색</button>
                </form>

                <div class="page-container">
                    <span>${p.pageNum} / ${p.endPage}</span>
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
                        <a href="articleView.do?articleIndex=${article.index}&boardId=${boardDto.id}"><p><c:out value="${article.title}"/></p></a>
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
        <div>
            <a href="articleListView.do?pageNum=1&boardId=${boardId}&tagValue=${tagValue}" class="most-prev-btn">[쩰앞]</a>
            <c:if test="${p.prev}">
                <a href="articleListView.do?pageNum=${p.startPage-1}&boardId=${boardId}&tagValue=${tagValue}" class="prev-btn">[이전]</a>
            </c:if>
            <c:forEach var="i" begin="${p.startPage}" end="${p.endPage}">
                <c:choose>
                    <c:when test="${p.pageNum==i}">
                        <a href="articleListView.do?pageNum=${i}&boardId=${boardId}&tagValue=${tagValue}" class="page-num-btn" style="color:red;">
                            [<span class="page-num-value">${i}</span>]
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="articleListView.do?pageNum=${i}&boardId=${boardId}&tagValue=${tagValue}" class="page-num-btn">[<span class="page-num-value">${i}</span>]</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${p.next}">
                <a href="articleListView.do?pageNum=${p.endPage+1}&boardId=${boardId}&tagValue=${tagValue}" class="next-btn">[다음]</a>
            </c:if>
            <a href="articleListView.do?pageNum=${p.totalCount}&boardId=${boardId}&tagValue=${tagValue}" class="most-next-btn">[쩰뒤]</a>
        </div>
    </div>
</main>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
