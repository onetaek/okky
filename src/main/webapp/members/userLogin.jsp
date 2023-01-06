<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/reset.css">
    <link rel="stylesheet" href="resources/css/userLogin.css">
</head>
<body>
<div id="headerWrap">
    <!-- --------------------------------------로고----------------------------------- -->
    <a href="../main/welcome.jsp"><img class="logo" src="../resources/img/okky.svg" alt="OKKY Logo"></a>
    <h2>OKKY에 오신것을 환영합니다.</h2>
    <p class="Explanation">OKKY는 소프트웨어 개발자를 위한 지식공유 플랫폼입니다.</p>
    <!-- ------------------------------------SNS로그인----------------------------------- -->
    <div class="SNSlogin">
        <span>SNS 로그인</span>
        <div class="SNSimages">
            <a href="https://github.com/login?client_id=ecbb8f7b235f3b66ae8e&return_to=%2Flogin%2Foauth%2Fauthorize%3Fclient_id%3Decbb8f7b235f3b66ae8e%26redirect_uri%3Dhttps%253A%252F%252Fapi.okky.kr%252Flogin%252Foauth2%252Fcode%252Fgithub%26response_type%3Dcode%26scope%3Dread%253Auser%26state%3Dth37thGwVHWTM0N-vVvhvsiIA3xQVYRRVGFkMN9-pJ0%253D"><svg class="im1"></svg></a>
            <a href="https://accounts.google.com/o/oauth2/v2/auth/identifier?response_type=code&client_id=1095456345592-lli7avm065sg4fakic8434am7gs1rscb.apps.googleusercontent.com&scope=email%20profile&state=8hl2PBampcmRnlOyUBl8wSLp-oNUPfbdtELy7-XYH0s%3D&redirect_uri=https%3A%2F%2Fapi.okky.kr%2Flogin%2Foauth2%2Fcode%2Fgoogle&service=lso&flowName=GeneralOAuthFlow"><svg class="im2"></svg></a>
            <a href="https://www.facebook.com/login.php?skip_api_login=1&api_key=1451111438499030&kid_directed_site=0&app_id=1451111438499030&signed_next=1&next=https%3A%2F%2Fwww.facebook.com%2Fv2.8%2Fdialog%2Foauth%3Fresponse_type%3Dcode%26client_id%3D1451111438499030%26scope%3Demail%26state%3D1s_onDC1MCR0wP8a1xtUHdFMAHl6KSqeeMHrH4_40yE%253D%26redirect_uri%3Dhttps%253A%252F%252Fapi.okky.kr%252Flogin%252Foauth2%252Fcode%252Ffacebook%26ret%3Dlogin%26fbapp_pres%3D0%26logger_id%3D7d56204e-c3ab-41b7-a15d-dfd7d40a88a0%26tp%3Dunspecified&cancel_url=https%3A%2F%2Fapi.okky.kr%2Flogin%2Foauth2%2Fcode%2Ffacebook%3Ferror%3Daccess_denied%26error_code%3D200%26error_description%3DPermissions%2Berror%26error_reason%3Duser_denied%26state%3D1s_onDC1MCR0wP8a1xtUHdFMAHl6KSqeeMHrH4_40yE%253D%23_%3D_&display=page&locale=ko_KR&pl_dbl=0"><svg class="im3"></svg></a>
            <a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=Vr0VLhUtPT00W8PYss0h&state=NHD8HmMSxzfPP7JuDSI17cdImrl9trHQnlspvpwkyBA%3D&redirect_uri=https://api.okky.kr/login/oauth2/code/naver"><svg class="im4"></svg></a>
            <a href="https://accounts.kakao.com/login?continue=https%3A%2F%2Fkauth.kakao.com%2Foauth%2Fauthorize%3Fscope%3Dprofile%2520account_email%26response_type%3Dcode%26state%3DiZnYSV7W2B4M2nic1aXeA31Mgob1YiJVMNW8F_cypCk%253D%26redirect_uri%3Dhttps%253A%252F%252Fapi.okky.kr%252Flogin%252Foauth2%252Fcode%252Fkakao%26through_account%3Dtrue%26client_id%3D71f448832727a1037722d48ee92a0701"><svg class="im5"></svg></a>
        </div>
    </div>
    <!-- -----------------------------------일반로그인----------------------------------- -->
    <div id="logintitle">
        <span>OKKY 아이디로 로그인</span>
    </div>
    <div id="login">
        <form action="loginProc.jsp" method="post">
            <div>
                <div class="idbox">
                    <label>아이디</label>
                    <input type="text" name="id" class="box" placeholder="아이디를 입력해 주세요."></input>

                </div>
                <div class="pwbox">
                    <label >비밀번호</label>
                    <input type="password" name="password" class="box" placeholder="비밀번호를 입력해 주세요."></input>
                </div>
            </div>
            <div class="find"><a class="findlink" href="./loginfind.html">계정찾기</a></div>
            <div>
                <input class="loginbutton" type="submit"  value="로그인"></input>
            </div>
            <!-- -----------------------------------회원가입----------------------------------- -->
            <p class="SignUp">
                <span>아직 회원이 아니신가요?</span>
                <a href="./userRegister.jsp">회원가입</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>
