function enableEdit() {
  const emailInput = document.getElementById("email");
  const phoneInput = document.getElementById("phone");
  const saveBtn = document.querySelector(".save-btn");
  const editBtn = document.querySelector(".edit-btn");

  // 이메일과 전화번호 필드를 활성화
  emailInput.disabled = false;
  phoneInput.disabled = false;

  // 저장 버튼 표시 및 수정 버튼 숨기기
  saveBtn.style.display = "inline-block";
  editBtn.style.display = "none";

  // 포커스를 전화번호 필드로 이동
  phoneInput.focus();

  // 알림 메시지
  alert("이메일과 전화번호를 수정할 수 있습니다.");
}

function saveChanges() {
  const emailInput = document.getElementById("email");
  const phoneInput = document.getElementById("phone");
  const saveBtn = document.querySelector(".save-btn");
  const editBtn = document.querySelector(".edit-btn");

  // 이메일과 전화번호 필드를 비활성화
  emailInput.disabled = true;
  phoneInput.disabled = true;

  // 저장 버튼 숨기기 및 수정 버튼 표시
  saveBtn.style.display = "none";
  editBtn.style.display = "inline-block";

  // 알림 메시지
  alert("수정이 완료되었습니다!");
}
