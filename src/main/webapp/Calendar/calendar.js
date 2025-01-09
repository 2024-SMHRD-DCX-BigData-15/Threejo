document.addEventListener("DOMContentLoaded", function () {
    var calendarEl = document.getElementById("calendar");
    var modal = document.getElementById("modal");
    var overlay = document.getElementById("modal-overlay");
    var currentEvent = null; // 현재 클릭된 이벤트 저장
  
    // FullCalendar 초기화
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: "dayGridMonth",
      editable: true,
      locale: "ko",
      headerToolbar: {
        left: "prev,next today",
        center: "title",
        right: "",
      },
      eventColor: "#007bff", // 일정 색상
      events: [],
  
      // 일정 클릭 시 모달 창 표시
      eventClick: function (info) {
        currentEvent = info.event; // 클릭된 이벤트 저장
        showModal(); // 모달 창 표시
      },
    });
  
    calendar.render();
  
    // 일정 등록 버튼 클릭 시
    document.getElementById("addEventBtn").addEventListener("click", function () {
      var eventTitle = document.getElementById("eventTitle").value;
      var startDate = document.getElementById("startDate").value;
      var endDate = document.getElementById("endDate").value;
  
      if (eventTitle.trim() === "" || startDate.trim() === "") {
        alert("일정 제목과 시작 날짜는 필수입니다.");
        return;
      }
  
      if (endDate.trim() === "") {
        endDate = startDate;
      }
  
      if (new Date(startDate) > new Date(endDate)) {
        alert("종료일은 시작일보다 앞설 수 없습니다.");
        return;
      }
  
      calendar.addEvent({
        title: eventTitle,
        start: startDate,
        end: new Date(endDate).toISOString().split("T")[0],
        allDay: true,
      });
  
      // 입력 필드 초기화
      document.getElementById("eventTitle").value = "";
      document.getElementById("startDate").value = "";
      document.getElementById("endDate").value = "";
  
      alert("일정이 추가되었습니다.");
    });
  
    // 모달 창 열기
    function showModal() {
      modal.style.display = "block";
      overlay.style.display = "block";
    }
  
    // 모달 창 닫기
    function closeModal() {
      modal.style.display = "none";
      overlay.style.display = "none";
    }
  
    // 수정 버튼 클릭
    document.getElementById("editEventBtn").addEventListener("click", function () {
      var newTitle = prompt("새로운 일정 제목을 입력하세요:", currentEvent.title);
      if (newTitle && newTitle.trim() !== "") {
        currentEvent.setProp("title", newTitle);
        alert("일정이 수정되었습니다.");
      } else {
        alert("제목이 비어있어 수정이 취소되었습니다.");
      }
      closeModal(); // 모달 창 닫기
    });
  
    // 삭제 버튼 클릭
    document.getElementById("deleteEventBtn").addEventListener("click", function () {
      if (confirm("정말로 이 일정을 삭제하시겠습니까?")) {
        currentEvent.remove();
        alert("일정이 삭제되었습니다.");
      }
      closeModal(); // 모달 창 닫기
    });
  
    // 모달 창 외부 클릭 시 닫기
    overlay.addEventListener("click", closeModal);
  });
  