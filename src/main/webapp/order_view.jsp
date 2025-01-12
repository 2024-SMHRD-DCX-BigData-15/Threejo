<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.smhrd.Model.CalendarDAO" %>
<%@ page import="com.smhrd.Model.CalendarVO" %>

<%
    // 세션에서 user_id 가져오기
    String user_id = (String) session.getAttribute("user_id");

    if (user_id == null) {
        System.out.println("[DEBUG] 세션에 user_id가 없습니다. 로그인 페이지로 이동합니다.");
        response.sendRedirect("login.jsp");
        return;
    }

    // DAO를 통해 DB에서 해당 user_id의 일정 데이터를 가져오기
    CalendarDAO dao = new CalendarDAO();
    List<CalendarVO> events = dao.getUserEvents(user_id);

    // 디버깅: 가져온 일정 확인
    System.out.println("[DEBUG] 가져온 일정 수: " + (events != null ? events.size() : 0));
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일정 관리</title>
    <link rel="stylesheet" href="calendar.css">
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/core@6.1.15/index.global.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@6.1.15/index.global.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@fullcalendar/interaction@6.1.15/index.global.min.js"></script>
</head>
<body>
    <!-- 상단 헤더 -->
    <header class="header">
        <h1><a href="loginmain.jsp">재능을IT다 | 일정 관리</a></h1>
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
            <form id="controls" method="POST" action="CalenderController?action=addEvent">
                <label for="eventTitle">일정 제목:</label>
                <input type="text" id="eventTitle" name="sche_title" placeholder="일정 입력" required>
                <label for="startDate">시작 날짜:</label>
                <input type="date" id="startDate" name="sche_st_dt" required>
                <label for="endDate">종료 날짜:</label>
                <input type="date" id="endDate" name="sche_ed_dt">
                <button id="addEventBtn" type="submit">일정 등록</button>
            </form>
        </aside>

        <!-- 메인 콘텐츠 -->
        <main class="main-content">
            <div id="calendar"></div>
        </main>
    </div>

    <!-- 수정 및 삭제 모달창 -->
    <div id="modal" style="display: none;">
        <form id="editEventForm" method="POST" action="CalenderUpdateController">
            <input type="hidden" name="sche_idx" id="modal-sche-idx">
            <label for="modal-sche-title">일정 제목:</label> 
            <input type="text" name="sche_title" id="modal-sche-title" required> <br>
            <label for="modal-sche-st-dt">시작 날짜:</label>
            <input type="date" name="sche_st_dt" id="modal-sche-st-dt" required><br>
            <label for="modal-sche-ed-dt">종료 날짜:</label>
            <input type="date" name="sche_ed_dt" id="modal-sche-ed-dt">
            <button type="submit">수정 완료</button>
            <button type="button" onclick="closeModal()">취소</button>
        </form>
        <button id="deleteEventBtn" onclick="deleteEvent()">삭제</button>
    </div>
    <div id="modal-overlay" style="display: none;" onclick="closeModal()"></div>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var calendarEl = document.getElementById("calendar");
            var modal = document.getElementById("modal");
            var overlay = document.getElementById("modal-overlay");
            var currentEvent = null;

            // FullCalendar 초기화
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: "dayGridMonth",
                locale: "ko",
                editable: true,
                headerToolbar: {
                    left: "prev,next today",
                    center: "title",
                    right: "",
                },
                events: [
                    <% for (CalendarVO event : events) { %>
                    {
                        id: "<%= event.getSche_idx() %>",
                        title: "<%= event.getSche_title() %>",
                        start: "<%= event.getSche_st_dt() %>",
                        end: "<%= event.getSche_ed_dt() != null ? java.time.LocalDate.parse(event.getSche_ed_dt().substring(0, 10)).plusDays(1).toString() : null %>",
                        allDay: true,
                    },
                    <% } %>
                ],
                eventClick: function (info) {
                    currentEvent = info.event;
                    openModal(currentEvent);
                },
            });

            calendar.render();

            // 모달창 열기
            function openModal(event) {
                document.getElementById("modal-sche-idx").value = event.id;
                document.getElementById("modal-sche-title").value = event.title;
                document.getElementById("modal-sche-st-dt").value = event.start.toISOString().split("T")[0];
                document.getElementById("modal-sche-ed-dt").value = event.end ? event.end.toISOString().split("T")[0] : "";
                modal.style.display = "block";
                overlay.style.display = "block";
            }

            // 모달창 닫기
            function closeModal() {
                modal.style.display = "none";
                overlay.style.display = "none";
            }

            // 일정 삭제
            function deleteEvent() {
                if (confirm("정말로 이 일정을 삭제하시겠습니까?")) {
                    var eventId = document.getElementById("modal-sche-idx").value;

                    var form = document.createElement("form");
                    form.method = "POST";
                    form.action = "CalenderDeleteController";

                    var input = document.createElement("input");
                    input.type = "hidden";
                    input.name = "sche_idx";
                    input.value = eventId;

                    form.appendChild(input);
                    document.body.appendChild(form);
                    form.submit();
                }
            }
        });
    </script>
</body>
</html>
