<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>받은 제안서 및 보낸 제안서</title>
  <!-- CSS 파일 연결 -->
  <link rel="stylesheet" href="proposal_box.css">
</head>

<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="loginmain.jsp">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="main.jsp">로그아웃</a>
    </div>
  </header>

  <!-- 컨테이너 -->
  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>마이페이지</h2>
      <ul>
        <li><a href="mypage.jsp">내 프로필</a></li>
        <li><a href="proposal_box.jsp">제안서보관함</a></li>
        <li><a href="OrderManageController">의뢰관리</a></li>
        <li><a href="delete_account.jsp">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <!-- 받은 제안서 섹션 -->
      <h1>받은 제안서</h1>
      <table class="board-table">
        <thead>
          <tr>
            <th>작성자</th>
            <th>카테고리</th>
            <th>제목</th>
            <th>마감기간</th>
            <th>예산</th>
            <th>결제</th>
          </tr>
        </thead>
        <tbody id="received-proposals">
          <!-- JavaScript로 데이터 추가 -->
        </tbody>
      </table>

      <!-- 보낸 제안서 섹션 -->
      <h1>보낸 제안서</h1>
      <table class="board-table">
        <thead>
          <tr>
            <th>수신자</th>
            <th>카테고리</th>
            <th>제목</th>
            <th>마감기간</th>
            <th>예산</th>
            <th>결제여부</th>
          </tr>
        </thead>
        <tbody id="sent-proposals">
          <!-- JavaScript로 데이터 추가 -->
        </tbody>
      </table>
    </main>
  </div>

  <!-- JavaScript 코드 삽입 -->
  <script>

    // 받은 제안서 렌더링
    function renderReceivedProposals() {
        const content = document.getElementById("received-proposals");
        content.innerHTML = "";

        receivedProposals.forEach((proposal) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${proposal.svc_title}</td>
                <td>${proposal.prop_title}</td>
                <td>${proposal.prop_content}</td>
                <td>${proposal.prop_account}</td>
                <td>${proposal.prop_td}</td>
                <td>${proposal.selected_yn == 'Y' ? 'Y' : 'N'}</td>
                <td>${proposal.seller_id}</td>
            `;
            content.appendChild(row);
        });
    }

    // 보낸 제안서 렌더링
    function renderSentProposals() {
        const content = document.getElementById("sent-proposals");
        content.innerHTML = "";

        sentProposals.forEach((proposal) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${proposal.svc_title}</td>
                <td>${proposal.prop_title}</td>
                <td>${proposal.prop_content}</td>
                <td>${proposal.prop_account}</td>
                <td>${proposal.prop_td}</td>
                <td>${proposal.selected_yn == 'Y' ? 'Y' : 'N'}</td>
                <td>${proposal.recipient_id}</td>
            `;
            content.appendChild(row);
        });
    }

    // 페이지 로드 시 데이터 렌더링
    document.addEventListener("DOMContentLoaded", () => {
        renderReceivedProposals();
        renderSentProposals();
    });
  </script>
</body>

</html>
