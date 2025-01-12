<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>전문가 매칭</title>
  <!-- CSS 파일 연결 -->
  <link rel="stylesheet" href="order.css">
</head>

<body>
  <div class="container">
    <!-- 제목 섹션 -->
    <h1>전문가와의 매칭을 시작합니다.</h1>
    <p align="center">전문가에게 어떤 서비스가 필요한지 요청해보세요</p>

    <!-- 폼 시작 -->
    <form id="writeForm" action="OrderController" method="post" onsubmit="return validateForm();">
      <!-- 서비스 제목 입력 섹션 -->
      <div class="form-section">
        <div class="left">
          <label for="svc_title">서비스 요청 제목을 작성하세요.</label>
          <p>짧고 간단하게 작성하세요. 이렇게 하면 전문가에게 빠르게 연결하는데 도움이 됩니다.</p>
        </div>
        <div class="right">
          <input type="text" id="svc_title" name="svc_title" placeholder="예) 우리 회사를 위한 홈페이지 만들기" required>
        </div>
      </div>

      <!-- 서비스 내용 입력 섹션 -->
      <div class="form-section">
        <div class="left">
          <label for="svc_content">어떤 서비스를 원하시나요?</label>
          <p>필요한 서비스 내용을 구체적으로 작성하시면 좀 더 많은 견적서를 받을 확률이 높아집니다.</p>
        </div>
        <div class="right">
          <textarea id="svc_content" name="svc_content" rows="6" placeholder="원하는 서비스 내용을 작성해주세요." required></textarea>
        </div>
      </div>

      <!-- 카테고리 선택 섹션 -->
      <div class="form-section">
        <div class="left">
          <label for="svc_categori">귀하의 서비스 요청에 가장 적합한 카테고리는 무엇입니까?</label>
          <p>카테고리에 맞는 전문가들이 제안을 하게 됩니다.</p>
        </div>
        <div class="right">
          <div class="category-group">
            <select id="svc_categori" name="svc_categori" required>
              <option value="" disabled selected>카테고리 선택</option>
              <option value="웹빌더">웹빌더</option>
              <option value="웹제작">웹제작</option>
              <option value="웹 유지보수">웹 유지보수</option>
              <option value="프로그램">프로그램</option>
              <option value="모바일">모바일</option>
              <option value="AI">AI</option>
              <option value="데이터">데이터</option>
              <option value="보안, 품질관리">보안, 품질관리</option>
              <option value="트렌드">트렌드</option>
              <option value="직무직군">직무직군</option>
              <option value="기타">기타</option>
            </select>
          </div>
        </div>
      </div>

      <!-- 예산 입력 섹션 -->
      <div class="form-section">
        <div class="left">
          <label for="svc_account">예상 지출비용을 작성해주세요</label>
          <p>원하는 예산을 적어주세요. 상황에 따라 예산을 변경할 수 있다면 체크박스를 체크해주세요.</p>
        </div>
        <div class="right">
          <input type="text" id="svc_account" name="svc_account" placeholder="예) 1,000,000원">
          <div class="checkbox-group">
            <input type="checkbox" id="budgetFlexible">
            <label for="budgetFlexible" >예산은 상황에 따라 유연합니다.</label>
          </div>
        </div>
      </div>

      <!-- 완료일 입력 섹션 -->
      <div class="form-section">
        <div class="left">
          <label for="svc_ed_td">완료일은 언제까지인가요?</label>
          <p>가능한 전문가를 찾아드리겠습니다.</p>
        </div>
        <div class="right">
          <input type="date" id="svc_ed_td" name="svc_ed_td">
        </div>
      </div>

      <!-- 제출 버튼 -->
      <button type="submit" class="submit-btn">완료</button>
    </form>
    <!-- 폼 끝 -->
  </div>

</body>

</html>
