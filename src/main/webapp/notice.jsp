<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>공지사항 페이지</title>
  <link rel="stylesheet" href="notice.css">
</head>

<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="main.jsp">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="mypage.jsp">마이페이지</a>
      <a href="logout.jsp">로그아웃</a>
    </div>
  </header>

  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>고객센터</h2>
      <ul>
        <li><a href="news.jsp">새 소식</a></li>
        <li><a href="notice.jsp" class="active">공지사항</a></li>
        <li><a href="ask.jsp">1:1 문의하기</a></li>
        <li><a href="faq.jsp">자주 묻는 질문</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>공지사항</h1>

      <div class="notice-section">
        <h2>운영 정보</h2>
        <ul>
          <li><a href="#">고객센터 운영 시간 변경 안내</a></li>
          <li><a href="#">플랫폼 유지보수 작업 공지</a></li>
        </ul>
      </div>

      <div class="notice-section">
        <h2>서비스 정책</h2>
        <ul>
          <li><a href="#">의뢰 보증 제도 시행 안내</a></li>
        </ul>
      </div>

      <div class="notice-section">
        <h2>신규 기능</h2>
        <ul>
          <li><a href="#">신규 기능 안내: 프로젝트 협업 도구 추가</a></li>
        </ul>
      </div>
    </main>
  </div>

  <script>
    document.addEventListener("DOMContentLoaded", () => {
      const noticeLinks = document.querySelectorAll(".notice-section ul li a");

      noticeLinks.forEach((link) => {
        link.addEventListener("click", (event) => {
          event.preventDefault();

          const question = link.textContent;
          const answer = getAnswer(question);

          // 기존에 열려 있는 답변 숨기기
          document.querySelectorAll(".notice-answer").forEach((answer) => answer.remove());

          // 새로운 답변 표시
          const answerDiv = document.createElement("div");
          answerDiv.classList.add("notice-answer");
          answerDiv.innerHTML = answer; // HTML 포함
          link.parentElement.appendChild(answerDiv);
        });
      });
    });

    function getAnswer(question) {
      const answers = {
        "고객센터 운영 시간 변경 안내": "고객센터 운영 시간이 월~금 오전 9시 ~ 오후 6시로 변경되었습니다.",
        "플랫폼 유지보수 작업 공지": "유지보수 작업이 2025년 1월 15일 오전 1시~5시까지 진행됩니다.",
        "의뢰 보증 제도 시행 안내": "의뢰 보증 제도가 준비 중입니다. 보다 안전한 거래를 지원합니다.",
        "신규 기능 안내: 프로젝트 협업 도구 추가": "프로젝트 협업 도구가 추가되었습니다. 실시간 소통이 가능합니다."
      };

      return answers[question] || "현재 답변을 준비 중입니다.";
    }
  </script>
</body>

</html>
    