<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>FullCalendar Event Styling with Edit/Delete</title>
    <!-- FullCalendar CDN -->
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/core@6.1.15/index.global.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@6.1.15/index.global.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/interaction@6.1.15/index.global.min.js"></script>
    <style>
      body {
        font-family: Arial, Helvetica, sans-serif;
      }
      #calendar {
        max-width: 900px;
        margin: 20px auto;
      }
      #controls {
        max-width: 900px;
        margin: 20px auto;
        padding: 15px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background-color: #f9f9f9;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        text-align: center;
      }
      label {
        font-size: 14px;
        margin-right: 10px;
      }
      input[type="text"],
      input[type="date"] {
        padding: 8px;
        margin-right: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 14px;
      }
      button {
        padding: 8px 16px;
        border: none;
        border-radius: 4px;
        background-color: #76818D; /* 일정 등록 버튼 색상 */
        color: white;
        font-size: 14px;
        cursor: pointer;
        transition: background-color 0.3s;
      }
      button:hover {
        background-color: #1E2B37;
      }
      /* 모달 창 스타일 */
      #modal {
        display: none;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 300px;
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        text-align: center;
        padding: 20px;
        z-index: 1000;
      }
      #modal button {
        margin: 10px;
      }
      #modal-overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 999;
      }
    </style>
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        var calendarEl = document.getElementById("calendar");
        var modal = document.getElementById("modal");
        var overlay = document.getElementById("modal-overlay");
        var currentEvent = null; // 현재 클릭된 이벤트 저장

        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: "dayGridMonth",
          editable: true,
          locale: "ko",
          headerToolbar: {
            left: "prev,next today",
            center: "title",
            right: "",
          },
          eventColor: "#76818D", // 이벤트 배경색을 일정 등록 버튼 색상과 동일하게 설정
          events: [],
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
    </script>
  </head>

  <body>
    <div id="calendar"></div>
    <div id="controls">
      <label>
        일정:
        <input type="text" id="eventTitle" placeholder="일정 입력" />
      </label>
      <label>
        시작 날짜:
        <input type="date" id="startDate" />
      </label>
      <label>
        종료 날짜:
        <input type="date" id="endDate" />
      </label>
      <button id="addEventBtn">일정 등록</button>
    </div>

    <!-- 모달 창 -->
    <div id="modal">
      <p>수정 또는 삭제를 선택하세요</p>
      <button id="editEventBtn">수정</button>
      <button id="deleteEventBtn">삭제</button>
    </div>
    <div id="modal-overlay"></div>
  </body>
</html>
