<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>제안서 관리</title>
    <link rel="stylesheet" type="text/css" href="proposal_box.css">
</head>
<body>
    <!-- 헤더 -->
    <div class="header">
        <h1><a href="loginmain.jsp">재능을 IT다</a></h1>
        <div class="auth-buttons">
            <a href="mypage.jsp">마이페이지</a>
            <a href="logout.jsp">로그아웃</a>
        </div>
    </div>

    <!-- 컨테이너 -->
    <div class="container">
        <!-- 사이드바 -->
        <div class="sidebar">
            <h2>마이페이지</h2>
            <ul>
                <li><a href="mypage.jsp">내 프로필</a></li>
                <li><a href="proposal_box.jsp">제안서보관함</a></li>
                <li><a href="OrderManageController">의뢰 관리</a></li>
                <li><a href="delete_account.jsp">회원 탈퇴</a></li>
            </ul>
        </div>

        <!-- 메인 콘텐츠 -->
        <div class="main-content">
            <h1>제안서 목록</h1>

            <!-- 받은 제안서 -->
<h2>받은 제안서</h2>
<div class="table-wrapper">
    <table class="board-table">
        <thead>
            <tr>
                <th>작성자</th>
                <th>카테고리</th>
                <th>제목</th>
                <th>마감기간</th>
                <th>예산</th>
                <th>결제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="receivedProposal" items="${receivedProposals}">
                <tr>
                    <td>${receivedProposal.prop_id}</td>
                    <td>${receivedProposal.svc_categori}</td>
                    <td><a href="ProposalViewController?svc_idx=${receivedProposal.svc_idx}">${receivedProposal.prop_title}</a></td>
                    <td>${receivedProposal.prop_ed_td}</td>
                    <td>${receivedProposal.prop_account}</td>
                    <td>
                        <button onclick="alert('결제 시스템이 구현될 예정입니다.');">결제</button>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty receivedProposals}">
                <tr>
                    <td colspan="6" style="text-align: center;">받은 제안서가 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

            <!-- 보낸 제안서 -->
            <h2>보낸 제안서</h2>
            <div class="table-wrapper">
                <table class="board-table">
                    <thead>
                        <tr>
                            <th>수신자</th>
                            <th>카테고리</th>
                            <th>제목</th>
                            <th>마감기간</th>
                            <th>예산</th>
                            <th>결제여부</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sentProposal" items="${sentProposals}">
                            <tr>
                                <td>${sentProposal.svc_id}</td>
                                <td>${sentProposal.svc_categori}</td>
                                <td>${sentProposal.prop_title}</td>
                                <td>${sentProposal.prop_ed_td}</td>
                                <td>${sentProposal.prop_account}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sentProposal.selected_yn == 'Y'}">결제 완료</c:when>
                                        <c:otherwise>미결제</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty sentProposals}">
                            <tr>
                                <td colspan="6" style="text-align: center;">보낸 제안서가 없습니다.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
