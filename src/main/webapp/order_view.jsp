<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>전문가 매칭 의뢰서</title>
  <link rel="stylesheet" href="order_view.css">
</head>

<body>
  <div class="container">
    <h1>전문가 매칭 의뢰서</h1>
    <div id="summary">
      <!-- 의뢰서 내용이 동적으로 추가됩니다 -->
    </div>
    <div class="button-group">
      <button id="editRequest">제안하기</button>
      <button id="deleteRequest">목록으로</button>
    </div>
  </div>

  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const order = JSON.parse(localStorage.getItem("selectedOrder"));

      // 의뢰 데이터 출력
      if (order) {
        document.getElementById("summary").innerHTML = `
          <h2>1. 서비스 요청 제목</h2>
          <p>${order.제목}</p>

          <h2>2. 필요한 서비스 내용</h2>
          <p>${order.상세내용}</p>

          <h2>3. 카테고리</h2>
          <p>${order.카테고리}</p>

          <h2>4. 마감 기간</h2>
          <p>${order.마감기간}</p>

          <h2>5. 상태</h2>
          <p>${order.상태}</p>
        `;
      } else {
        alert("의뢰 정보를 불러오지 못했습니다.");
        window.location.href = "order_list.html"; // 목록 페이지로 이동
      }

      // 제안하기 버튼 클릭 이벤트
      document.getElementById("editRequest").addEventListener("click", function () {
        localStorage.setItem("proposalOrder", JSON.stringify(order)); // 제안서로 전달할 데이터 저장
        window.location.href = "../Proposal/proposal_write.html"; // 제안서 작성 페이지로 이동
      });

      // 목록으로 버튼 클릭 이벤트
      document.getElementById("deleteRequest").addEventListener("click", function () {
        window.location.href = "order_list.html"; // 목록 페이지로 이동
      });
    });
  </script>
</body>

</html>
