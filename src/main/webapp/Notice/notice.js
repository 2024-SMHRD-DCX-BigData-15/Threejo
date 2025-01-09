document.addEventListener("DOMContentLoaded", () => {
  const noticeLinks = document.querySelectorAll(".notice-section ul li a");

  noticeLinks.forEach((link) => {
      link.addEventListener("click", (event) => {
          event.preventDefault();

          const question = link.textContent;
          const answer = getAnswer(question);

          // 기존에 열려 있는 답변 숨기기
          document.querySelectorAll(".notice-answer").forEach((answer) => answer.remove());

          // 새로운 답변 표시
          const answerDiv = document.createElement("div");
          answerDiv.classList.add("notice-answer");
          answerDiv.innerHTML = answer; // HTML 포함
          link.parentElement.appendChild(answerDiv);
      });
  });
});

function getAnswer(question) {
  const answers = {
      "운영 시간 변경 안내": "고객센터 운영 시간이 월~금 오전 9시 ~ 오후 6시로 변경되었습니다.",
      "의뢰 보증 제도 시행 안내": "의뢰 보증 제도가 준비중입니다. 보다 안전한 거래를 지원합니다.",
      "플랫폼 유지보수 작업 공지": "유지보수 작업이 2025년 1월 15일 오전 1시~5시까지 진행됩니다.",
      "신규 기능 안내: 프로젝트 협업 도구 추가": "프로젝트 협업 도구가 추가되었습니다. 실시간 소통이 가능합니다."
  };

  return answers[question] || "현재 답변을 준비 중입니다.";
}
