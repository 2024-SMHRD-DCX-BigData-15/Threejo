<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smhrd.Model.OrderVO" %>

<%
    // 전달받은 데이터 가져오기
    OrderVO order = (OrderVO) request.getAttribute("order");

    // 디버깅: 전달받은 데이터 확인
    System.out.println("[DEBUG] 전달받은 게시글: " + order);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" href="order_view.css">
</head>
<body>
    <header>
        <h1>게시글 상세보기</h1>
    </header>
    <div>
        <p><strong>제목:</strong> <%= order.getSvc_title() %></p>
        <p><strong>작성자:</strong> <%= order.getSvc_id() %></p>
        <p><strong>예산:</strong> <%= order.getSvc_account() %>원</p>
        <p><strong>완료일:</strong> <%= order.getSvc_ed_td() %></p>
        <p><strong>내용:</strong> <%= order.getSvc_content() %></p>
    </div>
</body>
</html>
