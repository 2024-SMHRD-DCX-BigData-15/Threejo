// 페이지가 로드되었을 때 실행
document.addEventListener("DOMContentLoaded", () => {
    // 모든 FAQ 질문 링크를 선택
    const FAQLinks = document.querySelectorAll(".FAQ-section ul li a");
  
    // 각 질문에 클릭 이벤트 추가
    FAQLinks.forEach(link => {
      link.addEventListener("click", (event) => {
        event.preventDefault(); // 기본 동작 방지 (페이지 이동 X)
  
        // 이미 답변이 표시 중이라면 숨기고 종료
        const currentAnswer = link.nextElementSibling;
        if (currentAnswer && currentAnswer.classList.contains("FAQ-answer")) {
          currentAnswer.remove();
          return;
        }
  
        // 기존에 열려 있는 답변 숨기기
        document.querySelectorAll(".FAQ-answer").forEach(answer => answer.remove());
  
        // 새 답변 표시
        const answer = document.createElement("div");
        answer.classList.add("FAQ-answer");
        answer.textContent = getAnswer(link.textContent); // 질문 텍스트에 따라 답변 가져오기
        link.parentElement.appendChild(answer);
      });
    });
  });
  
  // 질문 텍스트에 따라 답변을 반환하는 함수
  function getAnswer(question) {
    const answers = {
      "결제 수단은 어떤 것이 있나요?": "결제 수단으로는 신용카드, 체크카드, 계좌이체, 휴대폰 결제를 지원합니다.",
      "환불 정책은 어떻게 되나요?": "환불 요청은 구매 후 7일 이내에 가능합니다. 단, 이용한 서비스는 환불이 불가합니다.",
      "결제 영수증을 받을 수 있나요?": "결제 완료 후 마이페이지에서 영수증을 출력할 수 있습니다.",
      "회원 가입은 어떻게 하나요?": "회원 가입은 상단의 회원가입 버튼을 눌러 진행할 수 있습니다.",
      "로그인을 못 하겠어요. 어떻게 해야 하나요?": "로그인 문제는 고객센터에 문의하거나 비밀번호 찾기 기능을 이용해 주세요.",
      "서비스 이용 중 문제가 발생했습니다.": "고객센터에 문의를 남겨주시면 최대한 빠르게 해결해 드리겠습니다.",
      "의뢰 등록 방법은 무엇인가요?": "의뢰 등록은 의뢰관리 페이지에서 '새 의뢰 추가' 버튼을 클릭해 등록할 수 있습니다.",
      "의뢰를 수정하거나 삭제할 수 있나요?": "의뢰 수정 및 삭제는 마이페이지에서 가능합니다.",
      "진행 중인 의뢰를 확인하려면 어떻게 하나요?": "진행 중인 의뢰는 마이페이지의 의뢰 내역에서 확인할 수 있습니다."
    };
  
    // 질문에 해당하는 답변을 반환, 없으면 기본 답변
    return answers[question] || "현재 답변을 준비 중입니다. 고객센터에 문의해 주세요.";
  }
  