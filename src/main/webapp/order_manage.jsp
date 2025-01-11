<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>재능을IT다 | 의뢰 관리</title>
    <link rel="stylesheet" type="text/css" href="order_manage.css">
    
     <script>
        // 삭제 버튼 클릭 시 확인 알림창 표시
        function confirmDelete() {
            return confirm("삭제하시겠습니까?"); // 확인 창에서 사용자가 확인을 누르면 true 반환
        }
    </script>
    
</head>
<body>
    <!-- 헤더 -->
    <div class="header">
        <h1><a href="index.jsp">재능을IT다</a></h1>
        <div class="auth-buttons">
            <a href="mypage.jsp">마이페이지</a>
            <a href="logout.jsp">로그아웃</a>
        </div>
    </div>


    <!-- 메인 컨테이너 -->
    <div class="container">
    
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>마이페이지</h2>
      <ul>
        <li><a href="mypage.jsp">내 프로필</a></li>
        <li><a href="message_box.jsp">쪽지함</a></li>
        <li><a href="OrderManageController" class="active">의뢰관리</a></li>
        <li><a href="delete_account.jsp">회원탈퇴</a></li>
      </ul>
    </aside>
        <div class="main-content">
            <h1>내 의뢰 관리</h1>
            <!-- 의뢰 관리 테이블 -->
            <table class="board-table">
                <thead>
                    <tr>
                        <th>게시글 번호</th>
                        <th>제목</th>
                        <th>마감일</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 사용자 의뢰 데이터가 있을 경우 -->
                    <c:if test="${not empty userOrders}">
                        <c:forEach var="order" items="${userOrders}">
                            <tr>
                                <td>${order.svc_idx}</td> <!-- 게시글 번호 -->
                                <td>${order.svc_title}</td> <!-- 제목 -->
                                <td>${order.svc_ed_td}</td> <!-- 마감일 -->
                                <td>
                                <!-- 삭제 버튼 -->
                                    <form action="OrderDeleteController" method="post" onsubmit="return confirmDelete();" style="display:inline;">
                                        <input type="hidden" name="svc_idx" value="${order.svc_idx}" />
                                        <button type="submit" class="delete-btn">삭제</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <!-- 사용자 의뢰 데이터가 없을 경우 -->
                    <c:if test="${empty userOrders}">
                        <tr>
                            <td colspan="4" style="text-align: center;">등록된 의뢰가 없습니다.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
