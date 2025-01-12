<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>제안서 보기</title>
  <!-- CSS 파일 연결 -->
  <link rel="stylesheet" href="proposal_view.css">
</head>

<body>
  <div class="proposal-container">
    <h1>제안서</h1>
    <p>아래 제안서를 검토한 후 채택 여부를 결정하세요.</p>

    <div class="proposal-card">
      <!-- 제목 -->
      <div class="proposal-section">
        <h2>제목</h2>
        <p id="viewTitle"></p>
      </div>

      <!-- 제안 내용 -->
      <div class="proposal-section">
        <h2>제안 내용</h2>
        <p id="viewContent"></p>
      </div>

      <!-- 예산 -->
      <div class="proposal-section">
        <h2>예상 예산</h2>
        <p id="viewBudget"></p>
      </div>

      <!-- 완료일 -->
      <div class="proposal-section">
        <h2>완료일</h2>
        <p id="viewDeadline"></p>
      </div>

      <!-- 연락 가능한 전화번호 -->
      <div class="proposal-section">
        <h2>전화번호</h2>
        <p id="viewPhoneNumber"></p>
      </div>

      <!-- 채택 버튼 -->
      <button id="selectButton">채택하기</button>
    </div>
  </div>

  <script>
    document.addEventListener("DOMContentLoaded", function () {
      // localStorage에서 데이터 가져오기
      const proposalData = JSON.parse(localStorage.getItem("proposalData"));

      // 데이터가 존재하면 화면에 표시
      if (proposalData) {
        document.getElementById("viewTitle").textContent = proposalData.title;
        document.getElementById("viewContent").textContent = proposalData.content;
        document.getElementById("viewBudget").textContent = proposalData.budget;
        document.getElementById("viewDeadline").textContent = proposalData.deadline;
        document.getElementById("viewPhoneNumber").textContent = proposalData.phoneNumber;
      }

      // 채택하기 버튼 클릭 이벤트
      document.getElementById("selectButton").addEventListener("click", function () {
        alert(`제안서가 채택되었습니다!\n\n제목: ${proposalData.title}`);
      });
    });
  </script>
</body>

</html>
