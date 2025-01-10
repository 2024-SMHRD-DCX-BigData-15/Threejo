<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>의뢰 관리</title>
  <link rel="stylesheet" href="order_manage.css">
</head>

<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="main.jsp">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="logou
      t.jsp">로그아웃</a>
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
        <li><a href="order_manage.jsp" class="active">의뢰관리</a></li>
        <li><a href="delete_account.jsp">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>의뢰관리</h1>
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
    const boardContent = document.getElementById("board-content");

    // 의뢰 데이터 정의
    const requests = [
    { number: 1, title: "첫 번째 의뢰", deadline: "2025-01-15", status: "진행 중", id: "1" },
    { number: 2, title: "두 번째 의뢰", deadline: "2025-01-20", status: "완료", id: "2" },
];

    // 테이블에 게시물 렌더링
function renderTable() {
    boardContent.innerHTML = ""; // 기존 데이터 초기화
    requests.forEach((request) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${request.number}</td>
            <td><a href="order_detail.jsp?id=${request.id}">${request.title}</a></td>
            <td>${request.deadline}</td>
            <td>${request.status}</td>
        `;
        boardContent.appendChild(row);
    });
}

renderTable();
  </script>
</body>

</html>
    