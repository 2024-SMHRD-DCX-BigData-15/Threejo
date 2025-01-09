<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>의뢰글 작성 게시판</title>
  <link rel="stylesheet" href="order.css">
</head>

<body>
  <!-- 페이지 제목 -->
  <h1>의뢰글 작성</h1>
  
  <!-- 의뢰글 작성 폼 -->
  <form id="writeForm">
    <!-- 카테고리 선택 -->
    <label for="category">카테고리</label>
    <select id="category" required>
      <optgroup label="웹빌더">
        <option value="워드프레스">워드프레스</option>
        <option value="카페24">카페24</option>
        <option value="아임웹">아임웹</option>
        <option value="노션">노션</option>
      </optgroup>
      <optgroup label="웹제작">
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
      <optgroup label="프로그램">
        <option value="프로그램 스토어">프로그램 스토어</option>
        <option value="수익 자동화">수익 자동화</option>
        <option value="업무 자동화">업무 자동화</option>
        <option value="크롤링, 스크래핑">크롤링, 스크래핑</option>
        <option value="일반 프로그램">일반 프로그램</option>
        <option value="프로그램 수정, 유지보수">프로그램 수정, 유지보수</option>
        <option value="서버, 클라우드">서버, 클라우드</option>
        <option value="엑셀, 스프레드시트">엑셀, 스프레드시트</option>
        <option value="봇, 챗봇">봇, 챗봇</option>
      </optgroup>
      <!-- 추가 카테고리는 기존 내용 참고 -->
    </select>

    <!-- 제목 입력란 -->
    <label for="title">제목</label>
    <input type="text" id="title" placeholder="의뢰 제목을 입력하세요" required>

    <!-- 첨부파일 -->
    <label for="file">첨부파일</label>
    <input type="file" id="file" accept=".pdf,.doc,.docx,.zip">

    <!-- 가격 입력란 -->
    <label for="price">가격</label>
    <input type="number" id="price" placeholder="가격을 입력하세요" required>

    <!-- 내용 입력란 -->
    <label for="content">내용</label>
    <textarea id="content" rows="5" placeholder="의뢰 내용을 입력하세요" required></textarea>

    <!-- 작성완료 버튼 -->
    <button type="button" onclick="submitPost()">완료</button>
  </form>

  <script>
    // 의뢰글 작성 완료 버튼 클릭 시
    function submitPost() {
      const category = document.getElementById('category').value;
      const title = document.getElementById('title').value;
      const content = document.getElementById('content').value;
      const price = document.getElementById('price').value;

      // 첨부파일 처리 (옵션)
      const file = document.getElementById('file').files[0];
      let fileName = '';
      if (file) {
        fileName = file.name; // 파일 이름 저장
      }

      savePost(category, title, content, price, fileName);
    }

    // 의뢰글 데이터를 로컬 스토리지에 저장
    function savePost(category, title, content, price, fileName) {
      const posts = JSON.parse(localStorage.getItem('posts')) || [];
      const newPost = {
        id: Date.now(), // 고유 ID 생성
        category,
        title,
        content,
        price,
        fileName,
      };
      posts.push(newPost);
      localStorage.setItem('posts', JSON.stringify(posts));

      alert('의뢰글이 작성되었습니다!');
      window.location.href = 'order_list.jsp'; // 게시판 페이지로 이동
    }
  </script>
</body>

</html>