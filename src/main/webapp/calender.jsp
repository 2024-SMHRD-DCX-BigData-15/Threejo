<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>일정 관리</title>
  <link rel="stylesheet" href="calendar.css" />
  <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/core@6.1.15/index.global.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@6.1.15/index.global.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/interaction@6.1.15/index.global.min.js"></script>
</head>

<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="loginmain.jsp">재능을IT다 | 일정 관리</a>
    </h1>
    <div class="auth-buttons">
      <a href="mypage.jsp">마이페이지</a>
      <a href="logout.jsp">로그아웃</a>
    </div>
  </header>

  <!-- 전체 레이아웃 -->
  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>일정 추가</h2>
      <form id="controls">
        <label for="eventTitle">일정:</label>
        <input type="text" id="eventTitle" placeholder="일정 입력" required />
        <label for="startDate">시작 날짜:</label>
        <input type="date" id="startDate" required />
        <label for="endDate">종료 날짜:</label>
        <input type="date" id="endDate" />
        <button id="addEventBtn" type="button">일정 등록</button>
      </form>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <div id="calendar"></div>
    </main>
  </div>

  <!-- 모달 창 -->
  <div id="modal">
    <p>수정 또는 삭제를 선택하세요</p>
    <button id="editEventBtn">수정</button>
    <button id="deleteEventBtn">삭제</button>
  </div>
  <div id="modal-overlay"></div>

  <script>
  document.addEventListener("DOMContentLoaded", function () {
	  var calendarEl = document.getElementById("calendar");
	  var modal = document.getElementById("modal");
	  var overlay = document.getElementById("modal-overlay");
	  var currentEvent = null;

	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    initialView: "dayGridMonth",
	    editable: true,
	    locale: "ko",
	    headerToolbar: {
	      left: "prev,next today",
	      center: "title",
	      right: "",
	    },
	    eventColor: "#007bff",
	    events: [],

	    eventClick: function (info) {
	      currentEvent = info.event;
	      showModal();
	    },
	  });

	  calendar.render();

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

	    // 종료일 다음 날 계산
	    var endDateWithNextDay = new Date(endDate);
	    endDateWithNextDay.setDate(endDateWithNextDay.getDate() + 1);

	    calendar.addEvent({
	      title: eventTitle,
	      start: startDate,
	      end: endDateWithNextDay.toISOString().split("T")[0],
	      allDay: true,
	    });

	    // 입력 필드 초기화
	    document.getElementById("eventTitle").value = "";
	    document.getElementById("startDate").value = "";
	    document.getElementById("endDate").value = "";

	    alert("일정이 추가되었습니다.");
	  });

	  function showModal() {
	    modal.style.display = "block";
	    overlay.style.display = "block";
	  }

	  function closeModal() {
	    modal.style.display = "none";
	    overlay.style.display = "none";
	  }

	  document.getElementById("editEventBtn").addEventListener("click", function () {
	    var newTitle = prompt("새로운 일정 제목을 입력하세요:", currentEvent.title);
	    if (newTitle && newTitle.trim() !== "") {
	      currentEvent.setProp("title", newTitle);
	      alert("일정이 수정되었습니다.");
	    } else {
	      alert("제목이 비어있어 수정이 취소되었습니다.");
	    }
	    closeModal();
	  });

	  document.getElementById("deleteEventBtn").addEventListener("click", function () {
	    if (confirm("정말로 이 일정을 삭제하시겠습니까?")) {
	      currentEvent.remove();
	      alert("일정이 삭제되었습니다.");
	    }
	    closeModal();
	  });

	  overlay.addEventListener("click", closeModal);
	});

  </script>
</body>

</html>