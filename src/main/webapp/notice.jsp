<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
      <a href="../main/main.jsp">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="../Profile/profile.jsp">마이페이지</a>
      <a href="../Main/main.jsp">로그아웃</a>
    </div>
  </header>

  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>고객센터</h2>
      <ul>
        <li><a href="../News/news.jsp">새 소식</a></li>
        <li><a href="../Notice/notice.jsp" class="active">공지사항</a></li>
        <li><a href="../Ask/ask.jsp">1:1 문의하기</a></li>
        <li><a href="../FAQ/faq.jsp">자주 묻는 질문</a></li>
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
    document.addEventListener("DOMContentLoaded", function () {
      const notices = [
        {
          category: "운영 정보",
          items: ["고객센터 운영 시간 변경 안내", "플랫폼 유지보수 작업 공지"]
        },
        {
          category: "서비스 정책",
          items: ["의뢰 보증 제도 시행 안내"]
        },
        {
          category: "신규 기능",
          items: ["신규 기능 안내: 프로젝트 협업 도구 추가"]
        }
      ];

      const container = document.querySelector(".main-content");

      notices.forEach((section) => {
        const sectionDiv = document.createElement("div");
        sectionDiv.className = "notice-section";

        const header = document.createElement("h2");
        header.textContent = section.category;
        sectionDiv.appendChild(header);

        const list = document.createElement("ul");
        section.items.forEach((item) => {
          const listItem = document.createElement("li");
          const link = document.createElement("a");
          link.href = "#";
          link.textContent = item;
          listItem.appendChild(link);
          list.appendChild(listItem);
        });

        sectionDiv.appendChild(list);
        container.appendChild(sectionDiv);
      });
    });
  </script>
</body>

</html>
