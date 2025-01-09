<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>마이페이지</title>
  <link rel="stylesheet" href="mypage.css">
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
        <li><a href="mypage.jsp" class="active">내 프로필</a></li>
        <li><a href="message_box.jsp">쪽지함</a></li>
        <li><a href="order_manage.jsp">의뢰관리</a></li>
        <li><a href="delete_account.jsp">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>내 프로필</h1>
      <div class="info-item">
        <label for="nickname">아이디:</label>
        <input type="text" id="nickname" disabled>
      </div>

      <div class="info-item">
        <label for="email">이메일:</label>
        <input type="email" id="email" disabled>
      </div>

      <div class="info-item">
        <label for="phone">전화번호:</label>
        <div class="phone-wrapper">
          <input type="text" id="phone" disabled>
          <button class="edit-btn" onclick="enableEdit()">수정</button>
          <button class="save-btn" onclick="saveChanges()" style="display: none;">저장</button>
        </div>
      </div>
    </main>
  </div>

  <script>
    function enableEdit() {
      const emailInput = document.getElementById("email");
      const phoneInput = document.getElementById("phone");
      const saveBtn = document.querySelector(".save-btn");
      const editBtn = document.querySelector(".edit-btn");

      // 이메일과 전화번호 필드를 활성화
      emailInput.disabled = false;
      phoneInput.disabled = false;

      // 저장 버튼 표시 및 수정 버튼 숨기기
      saveBtn.style.display = "inline-block";
      editBtn.style.display = "none";

      // 포커스를 전화번호 필드로 이동
      phoneInput.focus();

      // 알림 메시지
      alert("이메일과 전화번호를 수정할 수 있습니다.");
    }

    function saveChanges() {
      const emailInput = document.getElementById("email");
      const phoneInput = document.getElementById("phone");
      const saveBtn = document.querySelector(".save-btn");
      const editBtn = document.querySelector(".edit-btn");

      // 이메일과 전화번호 필드를 비활성화
      emailInput.disabled = true;
      phoneInput.disabled = true;

      // 저장 버튼 숨기기 및 수정 버튼 표시
      saveBtn.style.display = "none";
      editBtn.style.display = "inline-block";

      // 알림 메시지
      alert("수정이 완료되었습니다!");
    }
  </script>
</body>

</html>
    