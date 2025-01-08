<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 성공</title>
</head>
<body>

  <h2>회원 정보 수정이 완료되었습니다.</h2>
    <p>수정된 정보: </p>
    <ul>
        <li>비밀번호 수정 완료</li>
        <li>이메일: ${member.user_email}</li>
        <li>전화번호: ${member.user_tell}</li>
    </ul>
    <a href="index.jsp">메인 페이지로 가기</a>
    
</body>
</html>