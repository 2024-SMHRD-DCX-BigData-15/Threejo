<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
		<!-- 로그인 실패 메시지 출력 (알림창) -->
	<c:if test="${not empty errorMessage}">
	    <script>
	        // JavaScript에서 조건 확인
	        const errorMessage = "${errorMessage}";
	        if (errorMessage) {
	            alert(errorMessage);
	        }
	    </script>
	</c:if>

    <form action="LoginController" method="post">
        <h2>로그인</h2>
        <label for="loginId">아이디:</label>
        <input type="text" id="loginId" name="user_id" placeholder="아이디를 입력하세요" required>
        
        <label for="loginPw">비밀번호:</label>
        <input type="password" id="loginPw" name="user_pw" placeholder="비밀번호를 입력하세요" required>
        
        <div class="button-group">
            <button type="submit">로그인</button>
            <a href="join.jsp" class="register-button">회원가입</a>
        </div>
    </form>
</body>
</html>
