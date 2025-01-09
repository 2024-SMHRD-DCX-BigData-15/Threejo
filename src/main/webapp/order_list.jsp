<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>s
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>의뢰 목록</title>
  <link rel="stylesheet" href="order_list.css">
</head>

<body>
  <!-- 헤더 -->
  <header class="header">
    <h1>
      <a href="main.jsp" class="logo">재능을IT다 ㅣ 의뢰 목록</a>
    </h1>
    <div class="auth-buttons">
      <a href="mypage.jsp" class="auth-link">마이페이지</a>
      <a href="logout.jsp" class="auth-link">로그아웃</a>
    </div>
  </header>

  <div class="container">
    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <table class="board-table">
        <thead>
          <tr>
            <th>작성자</th>
            <th>
              <div class="category-filter-container">
                <label for="category-filter">카테고리</label>
                <select id="category-filter">
                  <option value="all">전체 보기</option>
                  <optgroup label="웹 빌더">
                    <option value="워드프레스">워드프레스</option>
                    <option value="카페24">카페24</option>
                    <option value="아임웹">아임웹</option>
                    <option value="노션">노션</option>
                  </optgroup>
                  <optgroup label="웹 제작">
                    <option value="홈페이지 신규 제작">홈페이지 신규 제작</option>
                    <option value="쇼핑몰 신규 제작">쇼핑몰 신규 제작</option>
                    <option value="랜딩페이지">랜딩페이지</option>
                  </optgroup>
                  <optgroup label="웹 유지보수">
                    <option value="홈페이지 수정, 유지보수">홈페이지 수정, 유지보수</option>
                    <option value="쇼핑몰 수정, 유지보수">쇼핑몰 수정, 유지보수</option>
                    <option value="퍼블리싱">퍼블리싱</option>
                    <option value="검색최적화, SEO">검색최적화, SEO</option>
                    <option value="애널리틱스">애널리틱스</option>
                  </optgroup>
                  <!-- 추가 카테고리는 기존 내용을 참고 -->
                </select>
              </div>
            </th>
            <th>제목</th>
            <th>마감기간</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody id="board-content">
          <!-- JavaScript로 데이터를 동적으로 추가 -->
        </tbody>
      </table>
    </main>
  </div>

  <script>
    // 의뢰글 데이터를 불러와 게시판에 표시
    function loadPosts() {
      const posts = JSON.parse(localStorage.getItem('posts')) || [];
      const tbody = document.querySelector('#board-content');

      // 기존 내용 초기화
      tbody.innerHTML = '';

      if (posts.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5">등록된 의뢰글이 없습니다.</td></tr>';
        return;
      }

      // 의뢰글 데이터 표시
      posts.forEach((post) => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${post.category}</td>
          <td>${post.title}</td>
          <td>${post.content}</td>
          <td>${post.price}</td>
          <td>${post.fileName || '없음'}</td>
        `;
        tbody.appendChild(row);
      });
    }

    // 페이지 로드 시 의뢰글 로드
    document.addEventListener('DOMContentLoaded', loadPosts);
  </script>
</body>
</html>
