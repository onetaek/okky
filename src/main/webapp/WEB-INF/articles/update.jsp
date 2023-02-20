<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="ko">
<head>
    <title>글수정 페이지</title>
    <jsp:include page="../layouts/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/articles/write.css">
</head>
<body>
<jsp:include page="/WEB-INF/layouts/header.jsp"/>
<section class="section" id="community">
    <div id="write">
        <form action="articleUpdate.do" method="post">
            <input type="hidden" name="boardId" value="${boardId}"/>
            <input type="hidden" name="articleIndex" value="${articleDto.index}"/>
            <input type="hidden" name="userEmail" value="${user.email}"/>
            <h3>글수정하기</h3>
            <p>***님 지식공유 플랫폼 OKKY에서 최고의 개발자들과 함께 궁금증을 해결하세요.</p>
            <div id="wrap">
                <div id="writeHeader">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title"  value="${articleDto.title}">
                </div>
                <div id="writeTag">
                    <label>태그 <span> - 내용을 대표하는 태그 3개 정도 입력해주세요</span></label>
                    <c:forEach var="tag" items="${tags}">
                        <input type="checkbox" name="tags" value="${tag.value}"
                                <c:forEach var="myTag" items="${tagOfArticle}">
                                    <c:if test="${myTag == tag.value}">
                                         checked
                                    </c:if>
                                </c:forEach>
                        />${tag.value}
                    </c:forEach>
                </div>
                <div id="writecontent">
                    <label>본문</label>
                    <textarea id="summernote" name="content" style="width: 100%" rows="20">${articleDto.content}</textarea>
                </div>
            </div>
            <div id="btnWrap">
                <button type="button" onclick="history.go(-1)">취소</button>
                <button type="submit">등록</button>
            </div>
        </form>
    </div>
</section>
<jsp:include page="/WEB-INF/layouts/footer.jsp"/>
</body>
</html>