<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원탈퇴</title>
  <link rel="stylesheet" href="delete_account.css">
</head>
<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="main.jsp">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="logout.jsp">로그아웃</a>
    </div>
  </header>

  <!-- 컨테이너 -->
  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>마이페이지</h2>
      <ul>
        <li><a href="mypage.jsp">내 프로필</a></li>
        <li><a href="message_box.jsp">쪽지함</a></li>
        <li><a href="order_manage.jsp">의뢰관리</a></li>
        <li><a href="delete_account.jsp" class="active">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>회원탈퇴</h1>
      <p class="warning-text">
        회원탈퇴를 진행하시겠습니까? 회원탈퇴 시 모든 데이터가 삭제되며 복구할 수 없습니다.
      </p>

      <!-- 회원탈퇴 폼 -->
      <form id="deleteForm" action="delete_account_process.jsp" method="POST">
        <div class="form-item">
          <label for="password">비밀번호 확인</label>
          <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>
        </div>

        <div class="form-item">
          <label for="reason">탈퇴 이유</label>
          <select id="reason" name="reason" required>
            <option value="" disabled selected>탈퇴 사유를 선택하세요</option>
            <option value="서비스 불만족">서비스 불만족</option>
            <option value="이용 빈도 낮음">이용 빈도 낮음</option>
            <option value="다른 계정 사용">다른 계정 사용</option>
            <option value="기타">기타</option>
          </select>
        </div>

        <button type="submit" class="delete-button">회원탈퇴</button>
      </form>
    </main>
  </div>
</body>
</html>