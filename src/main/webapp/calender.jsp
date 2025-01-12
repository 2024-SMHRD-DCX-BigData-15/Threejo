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

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var calendarEl = document.getElementById("calendar");

            // FullCalendar 초기화
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: "dayGridMonth",
                locale: "ko",
                events: [
                    <% for (CalendarVO event : events) { %>
                    {
                        title: "<%= event.getSche_title() %>",
                        start: "<%= event.getSche_st_dt() %>",
                        end: "<%= event.getSche_ed_dt() %>",
                        allDay: true,
                    },
                    <% } %>
                ],
            });

            // 캘린더 렌더링
            calendar.render();
        });
    </script>
</body>
</html>
