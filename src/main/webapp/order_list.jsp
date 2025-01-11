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
      <a href="loginmain.jsp" class="logo">재능을IT다 ㅣ 의뢰 목록</a>
    </h1>
    <div class="auth-buttons">
      <a href="mypage.jsp" class="auth-link">마이페이지</a>
      <a href="logout.jsp" class="auth-link">로그아웃</a>
    </div>
  </header>

  <div class="container">
    <!-- 메인 콘텐츠 -->
    <main class="main-content" >
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
  /**
   * 의뢰글 데이터를 서버에서 불러와 게시판에 표시하는 함수
   */
  async function loadPosts() {
    try {
      console.log('[loadPosts] 데이터 로드 시작'); // 디버깅: 데이터 로드 시작
      
      // 서버에서 데이터 가져오기
      const response = await fetch('/OrderListController'); // OrderListController에서 데이터 요청
      if (!response.ok) {
        throw new Error(`[loadPosts] 서버 요청 실패: ${response.statusText}`);
      }

      // JSON 데이터를 파싱
      const posts = await response.json();
      console.log('[loadPosts] 서버에서 받은 데이터:', posts); // 디버깅: 서버에서 받은 데이터

      const tbody = document.querySelector('#board-content');

      // 기존 내용 초기화
      tbody.innerHTML = '';

      // 데이터가 없는 경우 처리
      if (!posts || posts.length === 0) {
        console.warn('[loadPosts] 등록된 의뢰글이 없습니다.'); // 디버깅: 데이터 없음
        tbody.innerHTML = '<tr><td colspan="5">등록된 의뢰글이 없습니다.</td></tr>';
        return;
      }

      // 의뢰글 데이터를 테이블에 추가
      posts.forEach((post) => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${post.svc_id}</td>
          <td>${post.svc_categori}</td>
          <td>${post.svc_title}</td>
          <td>${post.svc_ed_td}</td>
        `;
        tbody.appendChild(row);
        console.log('[loadPosts] 의뢰글 추가됨:', post); // 디버깅: 개별 의뢰글 추가
      });

      console.log('[loadPosts] 데이터 로드 완료'); // 디버깅: 데이터 로드 완료
    } catch (error) {
      console.error('[loadPosts] 데이터 로드 중 오류 발생:', error); // 디버깅: 오류 발생
    }
  }

  // 페이지 로드 시 의뢰글 데이터를 서버에서 불러오기
  document.addEventListener('DOMContentLoaded', loadPosts);
</script>
</body>
</html>
