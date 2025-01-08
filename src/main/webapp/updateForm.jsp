<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정</title>
</head>
<body>
    <h2>회원정보 수정</h2>
    <!-- 회원 정보 수정 폼 -->
    <form action="updateMember" method="post">
        <!-- 아이디는 수정 불가능하게 표시, read-only -->
        <label for="id">아이디:</label>
        <input type="text" id="username" name="username" value="${member.username}" readonly /><br><br>

        <!-- 비밀번호 수정 -->
        <label for="pw">비밀번호:</label>
        <input type="password" id="password" name="password" value="${member.password}" /><br><br>

        <!-- 이메일 수정 -->
        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" value="${member.email}" /><br><br>

        <!-- 전화번호 수정 -->
        <label for="tell">전화번호:</label>
        <input type="text" id="phone" name="phone" value="${member.phone}" /><br><br>

        <!-- 수정 버튼 -->
        <input type="submit" value="수정하기" />
    </form>
</body>
</html>
