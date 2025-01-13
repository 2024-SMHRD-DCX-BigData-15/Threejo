<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>마이페이지</title>
  <link rel="stylesheet" href="mypage.css">
  <script>
    function enableEdit() {
      document.getElementById("email").removeAttribute("disabled");
      document.getElementById("phone").removeAttribute("disabled");
      document.querySelector(".save-btn").style.display = "inline-block";
      document.querySelector(".edit-btn").style.display = "none";
    }

    function validateAndSubmit() {
      const form = document.getElementById("profileForm");
      const email = document.getElementById("email").value;
      const phone = document.getElementById("phone").value;

      if (!email || !phone) {
        alert("이메일과 전화번호를 입력해주세요.");
        return;
      }
      form.submit();
    }
  </script>
</head>

<body>
  <header class="header">
    <h1><a href="loginmain.jsp">재능을IT다</a></h1>
    <div class="auth-buttons">
      <a href="logout.jsp">로그아웃</a>
    </div>
  </header>

  <div class="container">
    <aside class="sidebar">
      <h2>마이페이지</h2>
      <ul>
        <li><a href="mypage.jsp">내 프로필</a></li>
        <li><a href="ProposalBoxController">제안서보관함</a></li>
        <li><a href="OrderManageController">의뢰관리</a></li>
        <li><a href="delete_account.jsp">회원탈퇴</a></li>
      </ul>
    </aside>

    <main class="main-content">
      <h1>내 프로필</h1>
      <form id="profileForm" action="UpdateController" method="post">
        <input type="hidden" name="user_id" value="${info.user_id}">

        <div class="info-item">
          <label for="nickname">아이디:</label>
          <input type="text" id="nickname" value="${info.user_id}" disabled>
        </div>

        <div class="info-item">
          <label for="email">이메일:</label>
          <input type="email" id="email" name="user_email" value="${info.user_email}" disabled>
        </div>

        <div class="info-item">
          <label for="phone">전화번호:</label>
          <div class="phone-wrapper">
            <input type="text" id="phone" name="user_tell" value="${info.user_tell}" disabled>
            
            <button type="button" class="edit-btn" onclick="enableEdit()">수정</button>
            <button type="button" class="save-btn" style="display: none;" onclick="validateAndSubmit()">저장</button>
          </div>
        </div>
      </form>
    </main>
  </div>
</body>

</html>