document.addEventListener("DOMContentLoaded", () => {
  const newsLinks = document.querySelectorAll(".news-section ul li a");

  newsLinks.forEach((link) => {
    link.addEventListener("click", (event) => {
      event.preventDefault();

      const question = link.textContent;
      const answer = getAnswer(question);

      // 기존에 열려 있는 답변 숨기기
      document.querySelectorAll(".news-answer").forEach((answer) => answer.remove());

      // 새로운 답변 표시
      const answerDiv = document.createElement("div");
      answerDiv.classList.add("news-answer");
      answerDiv.textContent = answer;
      answerDiv.style.marginTop = "10px";
      answerDiv.style.padding = "10px";
      answerDiv.style.border = "1px solid #ddd";
      answerDiv.style.borderRadius = "5px";
      link.parentElement.appendChild(answerDiv);
    });
  });
});

// 질문에 따라 답변 반환
function getAnswer(question) {
  const answers = {
    "리뷰 및 평점 시스템 도입": "리뷰 및 평점 시스템이 추가되었습니다.",
    "대시보드 업데이트": "대시보드가 최신 버전으로 업데이트되었습니다.",
    "신규 의뢰 카테고리 추가": "새로운 카테고리가 추가되었습니다.",
    "신규 서비스 출시 안내": "새로운 서비스가 출시되었습니다.",
    "결제 시스템 업데이트": "결제 시스템이 업데이트되었습니다.",
    "의뢰 보증 제도 시행": "의뢰 보증 제도가 시행되었습니다.",
  };

  return answers[question] || "내용이 준비 중입니다.";
}
