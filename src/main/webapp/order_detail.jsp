<!--  -->
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
      <h1>의뢰 상세 정보</h1>
        <!-- 상세 정보 테이블 -->
        <table class="detail-table">
            <tr>
                <th>제목</th>
                <td>${orderDetail.svc_title}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${orderDetail.svc_id}</td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td>${orderDetail.svc_categori}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${orderDetail.svc_content}</td>
            </tr>
            <tr>
                <th>예산</th>
                <td>${orderDetail.svc_account}</td>
            </tr>
            <tr>
                <th>마감일</th>
                <td>${orderDetail.svc_ed_td}</td>
            </tr>
        </table>

        <!-- 목록으로 돌아가기 버튼 -->
        <div class="button-container">
            <button onclick="location.href='OrderManageController'" class="back-button">목록으로 돌아가기</button>
        </div>
    </div>
</body>
</html>