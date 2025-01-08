<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정</title>
</head>
<body>
   <h2>회원 정보 수정</h2>

    <!-- 오류 메시지가 있을 경우 화면에 표시 -->
    <c:if test="${not empty error}">
        <div style="color: red; font-weight: bold;">
            <p>${error}</p>
        </div>
    </c:if>

    <!-- 수정 폼 -->
    <form action="updateMember" method="post">
        
            <label for="user_id">아이디:</label>
            <input type="text" id="userId" name="userId" value="${member.user_id}" required/><br><br>


            <label for="user_pw">비밀번호:</label>
            <input type="password" id="password" name="password" value="${member.user_pw}" required/><br><br>


            <label for="user_email">이메일:</label>
            <input type="email" id="email" name="email" value="${member.user_email}" /><br><br>
   
   
            <label for="user_tell">전화번호:</label>
            <input type="text" id="phone" name="phone" value="${member.user_tell}" /><br><br>


            <input type="submit" value="수정하기" /><br><br>
            
    </form>
    
    <!-- 취소 버튼 -->
    <form action="main.jsp" method="get">
        <input type="submit" value="취소" />
    </form>
    
</body>
</html>
