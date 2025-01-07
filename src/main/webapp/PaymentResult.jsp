<!DOCTYPE html>
<html>
<head>
	<%@ page contentType="text/html; charset=UTF-8" %>
    <title>결제 결과</title>
</head>
<body>
    <h1>결제 성공!</h1>
    <p>주문 ID: ${merchant_uid}</p>
    <p>결제 금액: ${amount} 원</p>
    <p>결제 상태: 성공</p>
    <a href="Payment.jsp">다시 결제하기</a>
</body>
</html>
