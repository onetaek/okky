<%--
  Created by IntelliJ IDEA.
  User: dog
  Date: 2023/01/14
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>개인 정보 페이지 - okky</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/members/userMy.css">
    <script src="${pageContext.request.contextPath}/resources/js/members/userMy.js" defer></script>
    <jsp:include page="../layouts/head.jsp"/>
</head>
<body>
<jsp:include page="../layouts/header.jsp"/>
<div class="full-container">
    <div class="h2">
        <h2>My Page</h2>
    </div>
    <form action="userUpdate.do" class="form" id="form">
        <div class="myContainer">

            <div class="explanation">
                <div class="chr-container">
                    <span class="chr"></span>
                </div>
                <span class="user">[${user.nickName}] 님 반갑습니다.</span>
                <div class="text-container">
                    <span class="text">개인 정보를 수정하기 위해서 기존 비밀번호 인증이 필요합니다.</span>
                </div>

                <div class="btn-container">
                    <input class="btn btn-submit" type="submit" value="수정하기">
    <%--                <input class="btn btn-reset" type="reset" value="되돌리기">--%>
                    <button class="btn btn-rollBack" href="#">돌아가기</button>
                </div>

            </div>

            <div class="inputContainer">
    <%--            여긴 readonly    --%>
                <div>
                    <label>
                        <span class="labelTitle">Email :</span>
                        <input type="email" name="email" class="email _input" id="email" value="${user.email}" readonly>
                    </label>
                </div>

                <div>
                    <label>
                        <span class="labelTitle">이 름 :</span>
                        <input type="text" name="name" class="name _input" id="name" value="${user.name}">
                    </label>
                </div>

                <div>
                    <label>
                        <span class="labelTitle">별 명 :</span>
                        <input type="text" name="nickName" class="nickName _input" id="nickName" value="${user.nickName}">
                    </label>
                </div>

                <div>
                    <label>
                        <span class="labelTitle">연락처 :</span>
                        <input type="text" name="contact" class="contact _input" id="contact" value="${user.contact}" >
                    </label>
                </div>

    <%--            여긴 전부 disabled--%>
                <div class="changePwCheckBox">
                    <label class="checkLabel">
                        <span>비밀번호 변경을 원하시면 체크박스를 클릭 해주세요.</span>
                        <input class="btn-pw-check" name="pwCheck" type="checkbox">
                    </label>
                </div>

                <div>
                    <label>
                        <span class="labelTitle">현재 비밀번호 :</span>
                        <div class="pwCheck-container">
                            <input type="password" name="OldPw" class="OldPw _input" id="OldPw" disabled>
                            <button class="btn btn-check" disabled >비밀번호 확인</button>
                        </div>
                    </label>
                </div>
                <div>
                    <label>
                        <span class="labelTitle">새로운 비밀번호 :</span>
                        <input type="password" name="NewPw" class="NewPw _input" id="NewPw" disabled>
                    </label>
                </div>
                <div>
                    <label>
                        <span class="labelTitle">비밀번호 재확인 :</span>
                        <input type="password" name="NewPwCheck" class="NewPwCheck _input" id="NewPwCheck" disabled>
                    </label>
                </div>

            </div>

        </div>

    </form>
</div>


<jsp:include page="/WEB-INF/layouts/footer.jsp"/>

</body>
</html>