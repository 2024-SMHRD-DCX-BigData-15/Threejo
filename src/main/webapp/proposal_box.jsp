<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>제안서 보관함</title>
  <link rel="stylesheet" href="proposal_box.css">
</head>

<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="../Main/main.html">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="../Main/main.html">로그아웃</a>
    </div>
  </header>

  <!-- 컨테이너 -->
  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>마이페이지</h2>
      <ul>
        <li><a href="../Profile/profile.html">내 프로필</a></li>
        <li><a href="../Proposal_box/proposal_box.jsp">제안서보관함</a></li>
        <li><a href="../Order_manage/order_manage.html">의뢰관리</a></li>
        <li><a href="../Delete/delete.html">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>제안서보관함</h1>
      <table class="board-table">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>마감일</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody id="board-content">
          <!-- JavaScript로 데이터 추가 -->
        </tbody>
      </table>
    </main>
  </div>

  <script>
    const messageContent = document.getElementById("board-content");

    // 임시 쪽지 데이터
    const messages = [
      { sender: "사용자1", title: "안녕하세요!", date: "2025-01-08", status: "읽음" },
      { sender: "사용자2", title: "프로젝트 진행 문의", date: "2025-01-07", status: "읽지 않음" },
      { sender: "관리자", title: "공지사항 알림", date: "2025-01-06", status: "읽음" },
    ];

    // 테이블에 데이터 렌더링
    function renderMessages() {
      messageContent.innerHTML = "";

      messages.forEach((message) => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${message.sender}</td>
          <td>${message.title}</td>
          <td>${message.date}</td>
          <td>${message.status}</td>
          <td>
            <button class="pay-button" onclick="location.href='/Pay/payment.html';">결제하기</button>
          </td>
        `;
        messageContent.appendChild(row);
      });
    }

    // 페이지 로드 시 메시지 렌더링
    document.addEventListener("DOMContentLoaded", renderMessages);
  </script>
</body>

</html>
