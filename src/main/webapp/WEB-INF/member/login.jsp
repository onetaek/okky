<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../resources/css/layouts/reset.css">
    <link rel="stylesheet" href="../../resources/css/members/userLogin.css">
    <script src="${pageContext.request.contextPath}/resources/js/members/userLogin.js" defer></script>
</head>
<body>
<div id="headerWrap">
    <!-- --------------------------------------로고----------------------------------- -->
    <a href="/main/welcome"><img class="logo" src="../../resources/img/okky.svg" alt="OKKY Logo"></a>
    <h2>OKKY에 오신것을 환영합니다.</h2>
    <p class="Explanation">OKKY는 소프트웨어 개발자를 위한 지식공유 플랫폼입니다.</p>
    <!-- ------------------------------------SNS로그인----------------------------------- -->
    <div class="SNSlogin">
        <span>SNS 로그인</span>
        <div class="SNSimages">
            <a href="#"><svg class="im1"></svg></a>
            <a href="#"><svg class="im2"></svg></a>
            <a href="#"><svg class="im3"></svg></a>
            <a href="#"><svg class="im4"></svg></a>
            <a href="#"><svg class="im5"></svg></a>
        </div>
    </div>
    <!-- -----------------------------------일반로그인----------------------------------- -->
    <div id="logintitle">
        <span>OKKY 아이디로 로그인</span>
    </div>
    <div id="login">

        <form id="form" >
            <div>
                <div class="idbox">
                    <label>아이디</label>
                    <input type="text" name="email" class="box" placeholder="아이디를 입력해 주세요."></input>

                </div>
                <div class="pwbox">
                    <label >비밀번호</label>
                    <input type="password" name="password" class="box" placeholder="비밀번호를 입력해 주세요."></input>
                </div>
            </div>
            <div class="find"><a class="findlink" href="#">계정찾기</a></div>

            <div class="warning-container" id="warning-container">
                <span class="warning" id="warning"></span>
            </div>

            <div>
                <input class="loginbutton" type="submit"  value="로그인"/>
            </div>
            <!-- -----------------------------------회원가입----------------------------------- -->
            <p class="SignUp">
                <span>아직 회원이 아니신가요?</span>
                <a href="/member/register">회원가입</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>
