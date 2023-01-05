<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <jsp:include page="/layouts/head.jsp"/>
    <link rel="stylesheet" href="./resources/css/aside.css" />
    <link rel="stylesheet" href="./resources/css/main.css">
</head>
<body>
<jsp:include page="./layouts/header.jsp"/>
<main id="main">
        <jsp:include page="./layouts/aside.jsp"/>
        <div id="sectionWrap">
            <section id="section1" class="sec">
                <div class="title">
                    <h2>Q&A</h2>
                    <div class="cute-cat"></div>
                </div>
                <div class="board-container">
                    <ul class="view-container">
                        <li><a href="#"> <img src="./resources/img/logo.svg" alt="profile" />
                        </a></li>
                        <li><i class="fa-solid fa-bolt"></i> <span>4.7k</span></li>
                        <li><span>1시간전.</span></li>
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
            <section id="section2" class="sec">
                <div class="title">
                    <h2>커뮤니티</h2>
                    <div class="cute-cat"></div>
                </div>
                <div class="board-container">
                    <ul class="view-container">
                        <li><a href="#"> <img src="./resources/img/logo.svg" alt="profile" />
                        </a></li>
                        <li><i class="fa-solid fa-bolt"></i> <span>4.7k</span></li>
                        <li><span>1시간전.</span></li>
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
            <section id="section3" class="sec">
                <div class="title">
                    <h2>공지사항</h2>
                    <div class="cute-cat"></div>
                </div>
                <div class="board-container">
                    <ul class="view-container">
                        <li><a href="#"> <img src="./resources/img/logo.svg" alt="profile" />
                        </a></li>
                        <li><i class="fa-solid fa-bolt"></i> <span>4.7k</span></li>
                        <li><span>1시간전.</span></li>
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


    <jsp:include page="layouts/footer.jsp"/>
</body>
</html>