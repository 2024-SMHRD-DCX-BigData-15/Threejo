<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>전문가 매칭 의뢰서</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/order_view.css">
</head>

<body>
  <!-- 컨테이너        -->
  <div class="container">
    <h1>전문가 매칭 의뢰서</h1>
    <!-- 요약 정보 -->
    <div id="summary">
      <h2>요약</h2>
      <p><strong>작성자:</strong> ${order.svc_id}</p>
      <p><strong>카테고리:</strong> ${order.svc_categori}</p>
      <p><strong>제목:</strong> ${order.svc_title}</p>
      <p><strong>내용:</strong> ${order.svc_content}</p>
      <p><strong>마감일:</strong> ${order.svc_ed_td}</p>
      <p><strong>예산:</strong> ${order.svc_account}</p>
    </div>
    <!-- 버튼 그룹 -->
    <div class="button-group">
      <button id="editRequest" onclick="location.href='<%= request.getContextPath() %>/proposal_write.jsp?svc_idx=${order.svc_idx}'">제안하기</button>
      <button id="deleteRequest" onclick="location.href='<%= request.getContextPath() %>/OrderListController'">목록으로</button>
    </div>
  </div>
  <script>
  document.addEventListener("DOMContentLoaded", function () {
    // 세션에 저장된 user_id 가져오기
    const sessionUserId = "<%= (String) session.getAttribute("user_id") %>";
    const currentUserId = "${order.svc_id}";

    // 제안하기 버튼 클릭 이벤트
    document.getElementById("editRequest").addEventListener("click", function (event) {
      if (sessionUserId === currentUserId) {
        event.preventDefault(); // 기본 동작 중단
        alert("자신의 의뢰에는 제안할 수 없습니다.");
        // OrderListController로 이동
        location.href = '<%= request.getContextPath() %>/OrderListController';
      }
    });
  });
</script>

</body>

</html>
