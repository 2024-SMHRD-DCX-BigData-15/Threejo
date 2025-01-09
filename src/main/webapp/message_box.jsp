<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>쪽지함</title>
  <link rel="stylesheet" href="message_box.css">
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
        <li><a href="message_box.jsp" class="active">쪽지함</a></li>
        <li><a href="order_manage.jsp">의뢰관리</a></li>
        <li><a href="delete_account.jsp">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>쪽지함</h1>

      <!-- 쪽지 목록 -->
      <table class="message-table">
        <thead>
          <tr>
            <th>보낸 사람</th>
            <th>제목</th>
            <th>마감일</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody id="message-content">
          <!-- JS로 동적으로 데이터 추가 -->
        </tbody>
      </table>
    </main>
  </div>

  <script>
    // 임시 쪽지 데이터
    const messages = [
        { sender: "사용자1", title: "안녕하세요!", date: "2025-01-08", status: "읽음" },
        { sender: "사용자2", title: "프로젝트 진행 문의", date: "2025-01-07", status: "읽지 않음" },
        { sender: "관리자", title: "공지사항 알림", date: "2025-01-06", status: "읽음" },
    ];

    // 테이블에 데이터 렌더링
    function renderMessages() {
        const messageContent = document.getElementById("message-content");
        messageContent.innerHTML = "";

        messages.forEach((message) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${message.sender}</td>
                <td>${message.title}</td>
                <td>${message.date}</td>
                <td>${message.status}</td>
            `;
            messageContent.appendChild(row);
        });
    }

    // 페이지 로드 시 메시지 렌더링
    document.addEventListener("DOMContentLoaded", renderMessages);
  </script>
</body>
</html>