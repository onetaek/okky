<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="headerWrap">
    <header id="header">
        <div class="left-nav">
            <h1><a href="/main/welcome">로고</a></h1>
            <nav>
                <h2 class="hidden">주요이용메뉴</h2>
                <ul class="gnb">
                    <li><a href="/board?boardId=1">Q&A</a></li>
                    <li class="left-line"><a href="/board?boardId=2">커뮤니티</a></li>
                    <li class="left-line"><a href="/board?boardId=3">공지사항</a></li>
                </ul>
            </nav>
        </div>
        <nav class="right-nav">
            <h2 class="hidden">계정관련이용메뉴</h2>
            <ul class="gnb">
                <li class="search">
                    <form>
                        <label>
                            <button type="submit">
                                <i class="fa fa-light fa-magnifying-glass"></i>
                            </button>
                            <input type="search" placeholder="검색"/>
                        </label>
                    </form>
                </li>
                <c:if test="${sessionMember == null}">
                    <li class="login admin"><a href="/member/login">로그인</a></li>
                    <li class="join admin"><a href="/member/register">회원가입</a></li>
                </c:if>

                <c:if test="${sessionMember != null}">
                    <li class="login admin"><a href="/member/my">${sessionMember.nickName} 님</a></li>
                    <li class="join admin"><a href="/member/logout">로그 아웃</a></li>
                </c:if>

            </ul>
        </nav>
    </header>
</div>