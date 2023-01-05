<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="ko">
<head>
  <title>글쓰기 페이지</title>
  <jsp:include page="../layouts/head2.jsp"/>
  <link rel="stylesheet" href="resources/css/write.css">
</head>
<body>
<jsp:include page="/layouts/header.jsp"/>
<section class="section" id="community">
  <div id="write">
    <form method="post">
      <h3>글작성하기</h3>
      <p>***님 지식공유 플랫폼 OKKY에서 최고의 개발자들과 함께 궁금증을 해결하세요.</p>
      <div id="wrap">
        <div>
          <label for="">토픽</label>
          <select name="" id="">
            <option value>토픽을 선택해주세요.</option>
            <option value="tech">기술</option>
            <option value="career">커리어</option>
            <option value="qna-etc">기타</option>
          </select>
        </div>
        <div id="writeHeader">
          <label for="title">제목</label>
          <input type="text" id="title" name="title" placeholder="제목을 입력해주세요"  >
        </div>
        <div id="writeTag">
          <label for="tag">태그 <span> - 내용을 대표하는 태그 3개 정도 입력해주세요</span></label>
          <input type="text" id="tag" name="tag" placeholder="태그를 입력해주세요"  >
        </div>
        <div id="writecontent">
          <label>본문</label>
          <textarea id="summernote" name="editordata"></textarea>
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