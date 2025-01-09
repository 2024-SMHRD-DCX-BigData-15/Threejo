const boardContent = document.getElementById("board-content");

// 테이블 데이터 렌더링
function renderTable() {
  boardContent.innerHTML = ""; // 초기화
  orders.forEach((order) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${order.번호}</td>
      <td class="clickable">${order.제목}</td>
      <td>${order.작성일}</td>
      <td>${order.상태}</td>
    `;
    row.querySelector(".clickable").addEventListener("click", () => {
      alert(`제목: ${order.제목}\n작성일: ${order.작성일}\n상태: ${order.상태}`);
    });
    boardContent.appendChild(row);
  });
}

// 초기 렌더링
renderTable();
