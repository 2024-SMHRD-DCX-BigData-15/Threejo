<!DOCTYPE html>
<html>
<head>
	<%@ page contentType="text/html; charset=UTF-8" %>
    <title>결제 요청</title>
</head>
<body>
    <h1>결제 요청</h1>
    <form action="payment" method="post">
        <label for="merchant_uid">주문 ID:</label>
        <input type="text" name="merchant_uid" id="merchant_uid" required><br>
        <label for="amount">결제 금액:</label>
        <input type="number" name="amount" id="amount" required><br>
        <button type="submit">결제 요청</button>
    </form>
</body>
</html>