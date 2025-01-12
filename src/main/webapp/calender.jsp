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
            <form id="controls" action="CalenderController" method="post">
                <input type="hidden" name="action" value="addEvent">
                <label for="eventTitle">일정:</label>
                <input type="text" id="eventTitle" name="sche_title" placeholder="일정 입력" required />
                <label for="startDate">시작 날짜:</label>
                <input type="date" id="startDate" name="sche_st_dt" required />
                <label for="endDate">종료 날짜:</label>
                <input type="date" id="endDate" name="sche_ed_dt" />
                <button id="addEventBtn" type="submit">일정 등록</button>
            </form>
        </aside>

        <!-- 메인 콘텐츠 -->
        <main class="main-content">
            <div id="calendar"></div>
        </main>
    </div>

    <!-- 모달 창 -->
    <div id="modal">
        <p>일정을 수정 또는 삭제합니다.</p>
        <input type="text" id="modalEventTitle" placeholder="일정 제목" /> <br>
        <input type="date" id="modalStartDate" />
        <input type="date" id="modalEndDate" />
        <button id="editEventBtn">수정</button>
        <button id="deleteEventBtn">삭제</button>
    </div>
    <div id="modal-overlay"></div>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var modal = document.getElementById("modal");
            var overlay = document.getElementById("modal-overlay");
            var calendarEl = document.getElementById("calendar");
            var currentEvent = null; // 클릭된 이벤트를 저장

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
                        id: "<%= event.getSche_idx() %>", // 일정 식별자
                        title: "<%= event.getSche_title() %>",
                        start: "<%= event.getSche_st_dt() %>",
                        end: "<%= event.getSche_ed_dt() %>",
                        allDay: true,
                    },
                    <% } %>
                ],
                eventClick: function (info) {
                    // 이벤트 클릭 시 모달 창 표시
                    currentEvent = info.event;
                    document.getElementById("modalEventTitle").value = currentEvent.title;
                    document.getElementById("modalStartDate").value = currentEvent.start.toISOString().split("T")[0];
                    document.getElementById("modalEndDate").value = currentEvent.end.toISOString().split("T")[0];
                    showModal();
                },
            });

            // 캘린더 렌더링
            calendar.render();

            // 일정 수정
            document.getElementById("editEventBtn").addEventListener("click", function () {
                var updatedTitle = document.getElementById("modalEventTitle").value;
                var updatedStartDate = document.getElementById("modalStartDate").value;
                var updatedEndDate = document.getElementById("modalEndDate").value;

                // 수정 요청
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "CalenderUpdateController", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        alert("일정이 수정되었습니다.");
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert("일정 수정에 실패했습니다.");
                    }
                };

                xhr.send(
                    `sche_idx=${currentEvent.id}&sche_title=${updatedTitle}&sche_st_dt=${updatedStartDate}&sche_ed_dt=${updatedEndDate}`
                );
                closeModal();
            });

            // 일정 삭제
            document.getElementById("deleteEventBtn").addEventListener("click", function () {
                if (confirm("정말로 이 일정을 삭제하시겠습니까?")) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "CalenderDeleteController", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.onload = function () {
                        if (xhr.status === 200) {
                            alert("일정이 삭제되었습니다.");
                            location.reload(); // 페이지 새로고침
                        } else {
                            alert("일정 삭제에 실패했습니다.");
                        }
                    };
                    xhr.send(`sche_idx=${currentEvent.id}`);
                    closeModal();
                }
            });

            function showModal() {
                modal.style.display = "block";
                overlay.style.display = "block";
            }

            function closeModal() {
                modal.style.display = "none";
                overlay.style.display = "none";
            }

            overlay.addEventListener("click", closeModal);
        });
    </script>
</body>
</html>
