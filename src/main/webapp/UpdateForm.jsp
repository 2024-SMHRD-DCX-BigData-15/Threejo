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

    <!-- 수정 폼 -->
    <form action="UpdateSuccess.jsp" method="post">
        	
        	<label for="user_pw">비밀번호 수정 : </label>
            <input type="password" id="user_pw" name="user_pw" value="${member.user_pw}" /><br><br>


            <label for="user_email">이메일 수정 : </label>
            <input type="email" id="user_email" name="user_email" value="${member.user_email}" /><br><br>
   
   
            <label for="user_tell">전화번호 수정 : </label>
            <input type="text" id="user_tell" name="user_tell" value="${member.user_tell}" /><br><br>


            <input type="submit" value="수정하기" /><br><br>
            
    </form>
    
    <!-- 취소 버튼 -->
    <form action="index.jsp" method="get">
        <input type="submit" value="취소" />
    </form>
    
</body>
</html>
