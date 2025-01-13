<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>제안서 작성</title>
  <!-- CSS 파일 연결 -->
  <link rel="stylesheet" href="proposal_write.css">
</head>

<body>
  <div class="container">
    <h1>제안서를 작성하세요</h1>
    <p>여기서 제안서를 작성하고 제출해주세요.</p>

    <form action="ProposalWriteController" method="post" accept-charset="UTF-8">
      <!-- 제안서 제목 -->
      <div class="form-section">
        <div class="left">
          <label for="proposalTitle">제목</label>
          <p>귀하의 제안서 제목을 간결하고 명확하게 작성해 주세요.</p>
        </div>
        <div class="right">
          <input type="text" id="prop_title" name="prop_title" placeholder="예) 홈페이지 제작 제안" required>
        </div>
      </div>

      <!-- 제안 내용 -->
      <div class="form-section">
        <div class="left">
          <label for="proposalContent">제안 내용</label>
          <p>구체적인 제안 내용을 작성해주세요. 고객의 요구를 충족하는 방법을 설명해주세요.</p>
        </div>
        <div class="right">
          <textarea id="prop_content" name="prop_content" rows="6" placeholder="원하는 서비스를 작성해주세요." required></textarea>
        </div>
      </div>

      <!-- 예산 -->
      <div class="form-section">
        <div class="left">
          <label for="budget">예상 예산</label>
          <p>이 프로젝트에 대한 예상 예산을 입력해주세요.</p>
        </div>
        <div class="right">
          <input type="text" id="prop_account" name="prop_account" placeholder="예) 1,500,000원" required>
        </div>
      </div>

      <!-- 완료일 -->
      <div class="form-section">
        <div class="left">
          <label for="deadline">완료일</label>
          <p>프로젝트 완료일을 설정해 주세요.</p>
        </div>
        <div class="right">
          <input type="date" id="prop_ed_td" name="prop_ed_td" required>
        </div>
      </div>

      <!-- 연락 가능한 전화번호 -->
      <div class="form-section">
        <div class="left">
          <label for="phoneNumber">연락 가능한 전화번호</label>
          <p>연락 가능한 전화번호를 입력해주세요.</p>
        </div>
        <div class="right">
          <input type="tel" id="prop_tell" name="prop_tell" placeholder="예) 010-1234-5678" required>
        </div>
      </div>
      
       <!-- 글 식별자 (svc_idx) -->
        <input type="hidden" name="svc_idx" value="<%= request.getParameter("svc_idx") %>" />

        <!-- 등록자 아이디 (prop_id) -->
        <input type="hidden" name="prop_id" value="<%= (String) session.getAttribute("user_id") %>" />

      <!-- 제출 버튼 -->
      <button type="submit" class="submit-btn">제출</button>
    </form>
  </div>

  <script>
    document.getElementById("proposalForm").addEventListener("submit", function (event) {
      event.preventDefault(); // 폼 제출 막기

      // 입력값 가져오기
      const proposalData = {
        title: document.getElementById("proposalTitle").value,
        content: document.getElementById("proposalContent").value,
        budget: document.getElementById("budget").value,
        deadline: document.getElementById("deadline").value,
        phoneNumber: document.getElementById("phoneNumber").value,
      };

      // 데이터를 localStorage에 저장
      localStorage.setItem("proposalData", JSON.stringify(proposalData));

      // 알림 후 보기 페이지로 이동
      alert("제안서가 성공적으로 저장되었습니다!");
      window.location.href = "proposal_view.jsp";
    });
  </script>
</body>

</html>
