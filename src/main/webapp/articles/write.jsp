<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="ko">
<head>
  <title>글쓰기 페이지</title>
  <jsp:include page="../layouts/head.jsp"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/articles/resources/css/write.css">
</head>
<body>
<jsp:include page="/layouts/header.jsp"/>
<section class="section" id="community">
  <div id="write">
    <form action="write.do" method="post">
      <input type="hidden" name="email" value="${user.email}" >
      <input type="hidden" name="boardId" value="${boardId}"/>
      <h3>글작성하기</h3>
      <p>***님 지식공유 플랫폼 OKKY에서 최고의 개발자들과 함께 궁금증을 해결하세요.</p>
      <div id="wrap">
        <div id="writeHeader">
          <label for="title">제목</label>
          <input type="text" id="title" name="title" placeholder="제목을 입력해주세요"  >
        </div>
        <div id="writeTag">
          <label>태그 <span> - 내용을 대표하는 태그 3개 정도 입력해주세요</span></label>
          <c:forEach var="tag" items="${tags}">
            <input type="checkbox" name="tags" value="${tag.value}"/>${tag.value}
          </c:forEach>

        </div>
        <div id="writecontent">
          <label>본문</label>
          <textarea id="summernote" name="content" style="width: 100%" rows="20"></textarea>
        </div>
      </div>
      <div id="btnWrap">
        <button type="button">취소</button>
        <button type="submit">등록</button>
      </div>
    </form>
  </div>
</section>
<jsp:include page="/layouts/footer.jsp"/>

</body>
</html>