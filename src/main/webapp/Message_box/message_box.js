// 임시 쪽지 데이터
const messages = [
    { sender: "사용자1", title: "안녕하세요!", date: "2025-01-08", status: "읽음" },
    { sender: "사용자2", title: "프로젝트 진행 문의", date: "2025-01-07", status: "읽지 않음" },
    { sender: "관리자", title: "공지사항 알림", date: "2025-01-06", status: "읽음" },
];

// 테이블에 데이터 렌더링
function renderMessages() {
    const messageContent = document.getElementById("message-content");
    messageContent.innerHTML = "";

    messages.forEach((message) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${message.sender}</td>
            <td>${message.title}</td>
            <td>${message.date}</td>
            <td>${message.status}</td>
        `;
        messageContent.appendChild(row);
    });
}

// 페이지 로드 시 메시지 렌더링
document.addEventListener("DOMContentLoaded", renderMessages);
