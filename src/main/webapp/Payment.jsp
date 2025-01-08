<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>결제 요청</title>
</head>
<body>
	<h1>결제 요청</h1>
	<form action="payment" method="post" onsubmit="return validateForm();">
		<label for="amount">결제 금액:</label> <input type="number" name="amount"
			id="amount" min="1" required>
		<button type="submit">결제 요청</button>
	</form>

	<script>
		function validateForm() {
			const amount = document.getElementById('amount').value;
			if (amount <= 0) {
				alert("결제 금액은 0보다 커야 합니다.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
