<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>결제 요청</title>
</head>
<body>
    <form action="payment" method="post">
        <label for="amount">결제 금액:</label>
        <input type="number" name="amount" id="amount" required>
        <button type="submit">결제 요청</button>
    </form>
</body>
</html>
