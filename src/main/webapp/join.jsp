<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 페이지</title>
    <link rel="stylesheet" href="join.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        // 아이디 중복 확인 함수
        function checkIdController() {
            var user_id = $('#userId').val(); 
            // 아이디 입력값 가져오기
            if (!user_id) {
                alert('아이디를 입력해주세요.');
                return;
            }

            // Ajax 요청
            $.ajax({
                url: "CheckIdController", // 서버의 중복 확인 API 경로
                type: "post",
                data: { user_id: user_id }, // JSON 형태로 데이터 전송
                success: function(response) {
                    if (response === "duplicate") {
                        alert("이미 사용 중인 아이디입니다.");
                    } else if (response === "available") {
                        alert("사용 가능한 아이디입니다.");
                    } else {
                        alert("알 수 없는 오류가 발생했습니다.");
                    }
                },
                error: function() {
                    alert("서버와의 통신 오류가 발생했습니다.");
                }
            });
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>회원가입</h2>
        <form action="JoinController" method="post">
            <!-- 아이디 입력 -->
            <div class="input-group">
                <label for="userId">아이디:</label>
                <input type="text" id="userId" name="userId" placeholder="아이디를 입력하세요" required>
                <button onclick="checkIdController()" type="button" class="check-btn" id="checkIdButton">중복 확인</button>
            </div>

            <!-- 비밀번호 입력 -->
            <label for="userPassword">비밀번호:</label>
            <input type="password" id="userPassword" name="userPassword" placeholder="비밀번호를 입력하세요" required>
            
            <!-- 비밀번호 확인 -->
            <label for="confirmPassword">비밀번호 확인:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 다시 입력하세요" required>
            
            <!-- 이메일 입력 -->
            <label for="userEmail">이메일:</label>
            <input type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요" required>
            
            <!-- 전화번호 입력 -->
            <label for="userPhone">전화번호:</label>
            <input type="tel" id="userPhone" name="userPhone" placeholder="전화번호를 입력하세요" required>
            
            <!-- 회원가입 버튼 -->
            <button type="submit" class="submit-btn">회원가입</button>
        </form>
    </div>
</body>
</html>
