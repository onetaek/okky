<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Q&A게시판</title>
  <jsp:include page="../layouts/head.jsp"/>
  <link rel="stylesheet" href="/articles/resources/css/community.css">
</head>
<script type="text/javascript">
  let searchRequest = new XMLHttpRequest();
  function articleSearchFunction(){
    //let boardId = document.getElementById("boardId").value;
    //let searchItem = document.getElementById("searchItem").value;
    searchRequest.open("POST","articleSearch.do?boardId",true);
    searchRequest.onreadystatechange = articleSearchProcess;
  }

  function articleSearchProcess(){
    if(searchRequest.readyState == 4 && searchRequest.status == 200){
      let object = eval('(' + searchRequest.responseText + ')');
      let result = object.result;
    }

  }
</script>
<body>
<jsp:include page="../layouts/header.jsp"/>
<main id="main">
  <jsp:include page="../layouts/aside.jsp"/>
  <!-- section community -->
  <div id="sectionWrap">
    <section id="section1">
      <div class="title">
        <span>${boardDto.text}</span>
        <span>좋은 질문과 답변으로 동료의 시간을 아껴주세요.</span>
      </div>
      <div id="top-container">
        <div class="container">
          <a class="w_btn" href="writeView.do?boardId=${boardDto.value}">
            <i class="icon fa-regular fa-pen-to-square"></i>
            작성하기
          </a>
        </div>
        <ul class="list">
          <li class="item"><a href="#">기술</a></li>
          <li class="item"><a href="#">커리어</a></li>
          <li class="item"><a href="#">기타</a></li>
          <li class="item"><a href="#">전체</a></li>
        </ul>

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
          <input type="hidden" name="boardId" id="boardId" value="${boardDto.value}" />
          <i class="icon fa-solid fa-magnifying-glass"></i>
          <input class="search-input" type="search" id="searchItem" name="searchItem" placeholder="Q&A 내에서 검색">
          <!--<input class="s-btn" type="submit" value="검색">-->
          <button class="s-btn" onclick="articleSearchFunction();" type="button">검색</button>
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
            <span>1시간전.</span>
          </li>
        </ul>

        <div class="title-container">
          <a href="#"><p>인텔리제이 얼티메이터 질문드립니다!</p></a>
        </div>

        <ul class="tag-container">
          <li class="category"><a href="#">기술</a></li>
          <li><a href="#">#운영</a></li>
          <li><a href="#">#q&a</a></li>
          <li><a href="#">#관리</a></li>
          <li><a href="#">#okky</a></li>
        </ul>
      </div>
    </section>
  </div>
</main>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
