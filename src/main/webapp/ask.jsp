<!--  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>1:1문의사항 페이지</title>
  <link rel="stylesheet" href="ask.css">
</head>

<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="loginmain.jsp">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="mypage.jsp">마이페이지</a>
      <a href="logout.jsp">로그아웃</a>
    </div>
  </header>

  <!-- 컨테이너 -->
  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>고객센터</h2>
      <ul>
        <li><a href="news.jsp">새 소식</a></li>
        <li><a href="notice.jsp">공지사항</a></li>
        <li><a href="ask.jsp">1:1 문의하기</a></li>
        <li><a href="faq.jsp">자주 묻는 질문</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>1:1 문의하기</h1>
      <form action="submit_inquiry.jsp" method="POST">
        <div class="form-group">
          <label for="title">제목:</label>
          <input type="text" id="title" name="title" placeholder="문의사항의 제목을 입력해주세요." class="search-txt">
        </div>
        <div class="form-group">
          <label for="writer">작성자:</label>
          <input type="text" id="writer" name="writer" placeholder="작성자의 이름을 입력해주세요.">
        </div>
        <div class="form-group">
          <label for="content">내용:</label>
          <textarea id="content" name="content" placeholder="문의사항의 내용을 입력해주세요."></textarea>
        </div>
        <div class="form-group">
          <button type="submit" class="search-btn">작성 완료</button>
        </div>
      </form>
    </main>
  </div>

  <script>
    document.querySelector('.search-btn').addEventListener('click', (event) => {
      const searchText = document.querySelector('.search-txt').value;
      if (!searchText.trim()) {
        alert('제목을 입력해주세요.');
        event.preventDefault(); // 폼 제출 방지
      } else {
        alert(`입력된 제목: ${searchText}`);
      }
    });
  </script>
</body>
</html>
