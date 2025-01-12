<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.smhrd.Model.OrderDAO" %>
<%@ page import="com.smhrd.Model.OrderVO" %>

<%
    // DAO를 통해 데이터베이스에서 게시글 목록 가져오기
    OrderDAO dao = new OrderDAO();
    List<OrderVO> orders = dao.getAllOrders();

    // 디버깅: 가져온 게시글 확인
    System.out.println("[DEBUG] 가져온 게시글 수: " + (orders != null ? orders.size() : 0));
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 목록</title>
    <link rel="stylesheet" href="order_list.css">
</head>
<body>
    <!-- 상단 헤더 -->
    <header class="header">
        <h1><a href="main.jsp">재능을IT다 | 게시글 목록</a></h1>
        <div class="auth-buttons">
            <a href="login.jsp">로그인</a>
            <a href="register.jsp">회원가입</a>
        </div>
    </header>

    <!-- 컨테이너 -->
    <div class="container">
        <!-- 게시글 목록 테이블 -->
        <h1>게시글 목록</h1>
        <table class="board-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (orders != null && !orders.isEmpty()) {
                        int rowNum = 1;
                        for (OrderVO order : orders) { 
                %>
                <tr>
                    <td><%= rowNum++ %></td>
                    <!-- 제목을 클릭하면 ListController를 통해 order_view.jsp로 이동 -->
                    <td><a href="ListController?orderId=<%= order.getSvc_idx() %>"><%= order.getSvc_title() %></a></td>
                    <td><%= order.getSvc_id() %></td>
                    <td><%= order.getSvc_ed_td() %></td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="4">등록된 게시글이 없습니다.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
