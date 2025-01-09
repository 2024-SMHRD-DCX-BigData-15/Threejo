const boardContent = document.getElementById("board-content");

// 의뢰 데이터 정의
const requests = [
  { 번호: 1, 제목: "첫 번째 의뢰", 마감일: "2025-01-15", 상태: "진행 중", id: "1" },
  { 번호: 2, 제목: "두 번째 의뢰", 마감일: "2025-01-20", 상태: "완료", id: "2" },
];

// 테이블에 게시물 렌더링
function renderTable() {
  boardContent.innerHTML = ""; // 기존 데이터 초기화
  requests.forEach((request) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${request.번호}</td>
      <td><a href="order_detail.html?id=${request.id}">${request.제목}</a></td>
      <td>${request.마감일}</td>
      <td>${request.상태}</td>
    `;
    boardContent.appendChild(row);
  });
}

renderTable();
