<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <h2>회원 탈퇴</h2>
    <!-- 사용자로부터 비밀번호를 입력받는 폼 -->
    <form action="DeleteSuccess.jsp" method="POST">
    
   		<label for="user_id">아이디 확인:</label>
        <input type="text" id="user_id" name="user_id" required/><br><br>
        
        <label for="user_pw">비밀번호 확인:</label>
        <input type="password" id="user_pw" name="user_pw" required/><br><br>
        
        <!-- 탈퇴하기 버튼 -->
        <button type="submit">탈퇴하기</button>
    </form>
    
    <!-- 탈퇴 실패 시 오류 메시지 출력 -->
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p> <!-- 오류 메시지 출력 -->
    </c:if>

</body>
</html>