<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>의뢰 상세</title>
  <link rel="stylesheet" href="order_detail.css">
</head>

<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="loginmain.jsp">재능을IT다</a>
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
        <li><a href="mypage.jsp">내 프로필 수정</a></li>
        <li><a href="message_box.jsp">쪽지함</a></li>
        <li><a href="OrderManageController">의뢰관리</a></li>
        <li><a href="delete_account.jsp">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>의뢰 상세</h1>
      <table class="detail-table">
        <tr>
          <th>작성자</th>
          <td>사용자1</td>
        </tr>
        <tr>
          <th>작성일</th>
          <td>2025-01-09</td>
        </tr>
        <tr>
          <th>상태</th>
          <td>진행 중</td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <p>
              이 의뢰는 다음과 같은 요구사항을 포함합니다:
              <ul>
                <li>요구사항 1: 디자인 제작</li>
                <li>요구사항 2: 프론트엔드 개발</li>
                <li>요구사항 3: 백엔드 연동</li>
              </ul>
            </p>
          </td>
        </tr>
      </table>

      <!-- 댓글 영역 -->
      <section class="comments-section">
        <h2>댓글</h2>
        <ul class="comments-list">
          <li>
            <p><strong>사용자2:</strong> 의뢰에 대해 궁금한 점이 있습니다.</p>
            <span>2025-01-09 14:30</span>
          </li>
          <li>
            <p><strong>사용자3:</strong> 작업 내용이 명확하여 좋습니다.</p>
            <span>2025-01-09 15:00</span>
          </li>
        </ul>

        <!-- 댓글 작성 -->
        <form id="commentForm" action="submit_comment.jsp" method="POST">
          <textarea id="comment" name="comment" placeholder="댓글을 입력하세요" required></textarea>
          <button type="submit">댓글 작성</button>
        </form>
      </section>

      <!-- 목록으로 돌아가기 버튼 -->
      <div class="back-button-container">
        <a href="order_manage.jsp" class="back-button">목록으로 돌아가기</a>
      </div>
    </main>
  </div>
</body>

</html>
    