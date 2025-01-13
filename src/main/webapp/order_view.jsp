<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smhrd.Model.OrderVO" %>

<%
    // request에서 전달된 order 객체 가져오기
    OrderVO order = (OrderVO) request.getAttribute("order");

    if (order == null) {
        out.println("<script>alert('조회된 데이터가 없습니다.'); location.href='order_list.jsp';</script>");
        return;
    }

    // 디버깅: 전달된 데이터 확인
    System.out.println("[DEBUG] order_view.jsp에서 확인된 데이터: " + order);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" type="text/css" href="order_view.css">
</head>
<body>
    <header class="header">
        <h1>게시글 상세보기</h1>
        <a href="OrderListController">목록으로 돌아가기</a>
    </header>

    <main>
        <h2><%= order.getSvc_title() %></h2>
        <p><strong>작성자:</strong> <%= order.getSvc_id() %></p>
        <p><strong>내용:</strong> <%= order.getSvc_content() %></p>
        <p><strong>카테고리:</strong> <%= order.getSvc_categori() %></p>
        <p><strong>예산:</strong> <%= order.getSvc_account() %>원</p>
        <p><strong>완료일:</strong> <%= order.getSvc_ed_td() %></p>
    </main>
</body>		
</html>
	