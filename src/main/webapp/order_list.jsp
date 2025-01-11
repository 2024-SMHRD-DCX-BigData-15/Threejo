<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>재능을IT다 | 의뢰 목록</title>
    <link rel="stylesheet" type="text/css" href="order_list.css">
</head>
<body>
    <!-- 헤더 섹션 -->
    <div class="header">
        <h1><a href="loginmain.jsp">재능을IT다</a></h1>
        <div class="auth-buttons">
            <a href="mypage.jsp">마이페이지</a>
            <a href="logout.jsp">로그아웃</a>
        </div>
    </div>

    <!-- 메인 컨테이너 -->
    <div class="container">
        <div class="main-content">
            <h1>의뢰 목록</h1>
            <!-- 테이블 -->
            <table class="board-table">
                <thead>
                    <tr>
                        <th>작성자</th>
                        <th>카테고리</th>
                        <th>제목</th>
                        <th>마감기간</th>
                        <th>예산</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 데이터가 있는 경우 -->
                    <c:if test="${not empty orderList}">
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.svc_id}</td> <!-- 작성자 ID -->
                                <td>${order.svc_categori}</td> <!-- 카테고리 -->
                                <td>${order.svc_title}</td> <!-- 제목 -->
                                <td>${order.svc_ed_td}</td> <!-- 마감기간 -->
                                <td>${order.svc_account}</td> <!-- 예산 -->
                            </tr>
                        </c:forEach>
                    </c:if>
                    <!-- 데이터가 없는 경우 -->
                    <c:if test="${empty orderList}">
                        <tr>
                            <td colspan="5" style="text-align: center;">등록된 의뢰글이 없습니다.</td> <!-- 데이터 없음 메시지 -->
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
