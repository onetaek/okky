<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Okky</title>
    <jsp:include page="../layouts/head2.jsp"/>
    <link rel="stylesheet" href="../members/resources/css/userRegister.css">
    <script defer src="../members/resources/js/userRegister.js"></script>
</head>
<body>
<!-- ----------------------------------------로고-------------------------------------------- -->
<div id="headerWrap">
    <a href="../main/welcome.jsp"><img class="logo" src="../resources/img/okky.svg" alt="OKKY Logo"></a>
    <h2>OKKY에 오신것을 환영합니다.</h2>
    <p class="Explanation">OKKY는 소프트웨어 개발자를 위한 지식공유 플랫폼입니다.</p>
    <!-- --------------------------------------SNS로그인----------------------------------------------- -->
    <div class="SNSlogin">
        <span>SNS 회원가입</span>
        <div class="SNSimages">
            <a
                    href="#"><svg
                    class="im1"></svg></a>
            <a
                    href="#"><svg
                    class="im2"></svg></a>
            <a
                    href="#"><svg
                    class="im3"></svg></a>
            <a
                    href="#"><svg
                    class="im4"></svg></a>
            <a
                    href="#"><svg
                    class="im5"></svg></a>
        </div>
    </div>
    <!-- ---------------------------------------회원가입정보--------------------------------------------- -->

    <div id="signUptitle">
        <span>회원가입에 필요한 기본정보를 입력해주세요.</span>
    </div>
    <div id="lnformation">
        <form action="userRegister.do" id="form" method="post">
            <div>
                <div class="emaillabel">
                    <label for="email">이메일</label>
                    <input name="email" id="email" type="email" class="box" placeholder="이메일 주소를 입력해 주세요.">
                </div>
                <div class="pwbox">
                    <label for="password">비밀번호</label>
                    <input name="password" id="password" minlength="6" maxlength="15" type="password" class="box"
                           placeholder="최소 8자 이상(알바벳, 숫자 필수)">
                </div>
                <div class="pwConfirm">
                    <label for="passwordCheck">비밀번호 재확인</label>
                    <input name="passwordCheck" id="passwordCheck" minlength="6" maxlength="15" type="password"
                           class="box" placeholder="최소 8자 이상(알바벳, 숫자 필수)">
                </div>

                <div class="name">
                    <label for="name">이름</label>
                    <input name="name" id="name" type="text" class="box" placeholder="이름을 입력해 주세요.">
                </div>
                <div class="nickname">
                    <label for="nickName">닉네임</label>
                    <input name="nickName" id="nickName" type="text" class="box"
                           placeholder="별명을 한글, 숫자를 20자 이하로 입력해주세요.">
                </div>
                <div class="phoneNumber">
                    <label>휴대폰 번호</label>
                    <div class="boxbundle">
                        <div id="phoneinfo">
                            <select name="telecom" id="telecom">
                                <%--
                                <c:forEach var="l" items="<%=list%>">
                                    <option value="${l}"> ${l} </option>
                                </c:forEach>
                                --%>

                                <c:forEach var="telecomDto" items="${telecomDtoList}">
                                    <option>${telecomDto.value}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="contactCountryValue" value="082">
                            <input name="contact" id="contact" class="phoneBox" type="text"
                                   placeholder="전화번호 입력.(숫자만 입력해주세요)">
                        </div>
                        <input class="certification" id="requestAuthBtn" type="button" value="인증요청">
                    </div>
                    <div class="CertificationNumber">
                        <label>인증번호 확인</label>
                        <div class="CNbox">
                            <input name="contactAuthCode" id="CNumber" class="CNumber" type="tel"
                                   placeholder="인증번호를 입력하세요">
                            <input class="CNConfirm" id="successAuthBtn" type="button" value="인증확인">
                        </div>
                    </div>
                </div>
            </div>
            <!-- -------------------------------이메일수신 동의 및 가입----------------------------------------------- -->
            <div class="emailbox">
                <div class="email">
                    <label for="emailReceive" class="emailtext">이메일 수신동의</label>
                    <input type="checkbox" name="policyEmailSend" id="emailReceive">
                </div>
                <p>OKKY에서 주최하는 다양한 이벤트, 정보성 뉴스레터 및 광고 수신여부를 설정할 수 있습니다.</p>
            </div>
            <div>
                <input class="submit" type="submit" value="회원가입"/>
            </div>
            <p class="loginbox">
                <span>이미 회원이신가요?</span>
                <a href="./userLogin.jsp">로그인</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>