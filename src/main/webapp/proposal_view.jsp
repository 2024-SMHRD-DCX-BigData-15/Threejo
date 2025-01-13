<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>제안서 상세보기</title>
    <link rel="stylesheet" type="text/css" href="proposal_view.css">
</head>
<body>
    <div class="container">
        <h1>제안서 상세보기</h1>
        <p>아래 제안서를 검토한 후 채택 여부를 결정하세요.</p>
        <table class="proposal-detail">
            <tr>
                <th>제목</th>
                <td>${proposal.prop_title}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${proposal.prop_content}</td>
            </tr>
            <tr>
                <th>예산</th>
                <td>${proposal.prop_account}</td>
            </tr>
            <tr>
                <th>마감일</th>
                <td>${proposal.prop_ed_td}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${proposal.prop_id}</td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td>${proposal.svc_categori}</td>
            </tr>
        </table>
        <!-- 결제하기 버튼 -->
        <form action="PaymentController" method="post">
            <input type="hidden" name="prop_title" value="${proposal.prop_title}">
            <input type="hidden" name="prop_account" value="${proposal.prop_account}">
            <input type="hidden" name="user_email" value="${sessionScope.user_email}">
            <input type="hidden" name="pay_id" value="${sessionScope.user_id}">
            <input type="hidden" name="pay_tell" value="${sessionScope.user_tell}">
            <input type="hidden" name="prop_idx" value="${proposal.prop_idx}">
            <button type="submit" class="pay-button">결제하기</button>
        <a href="ProposalBoxController">목록으로 돌아가기</a>
        
    </div>


  <script>
    document.addEventListener("DOMContentLoaded", function () {
      // localStorage에서 데이터 가져오기
      const proposalData = JSON.parse(localStorage.getItem("proposalData"));

      // 데이터가 존재하면 화면에 표시
      if (proposalData) {
        document.getElementById("prop_title").textContent = proposalData.prop_title;
        document.getElementById("prop_content").textContent = proposalData.prop_content;
        document.getElementById("prop_account").textContent = proposalData.prop_account;
        document.getElementById("prop_ed_td").textContent = proposalData.prop_ed_td;
        document.getElementById("prop_tell").textContent = proposalData.prop_tell;
      }

      // 채택하기 버튼 클릭 이벤트
      document.getElementById("selectButton").addEventListener("click", function () {
        alert(`제안서가 채택되었습니다!\n\n제목: ${proposalData.title}`);
      });
    });
  </script>
</body>

</html>
