package com.smhrd.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.CalendarDAO;
import com.smhrd.Model.CalendarVO;
//1
@WebServlet("/CalenderController")
public class CalenderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 이 'action' 값에 따라 수행할 작업을 구분합니다.
        String action = request.getParameter("action");

        // 'action' 값이 "addEvent"인 경우, 일정을 추가하는 작업을 진행합니다.
        if ("addEvent".equals(action)) {
            // 클라이언트에서 전달된 일정의 제목, 시작일, 종료일을 각각 가져옵니다.
            String sche_title = request.getParameter("sche_title");
            String sche_st_dt = request.getParameter("sche_st_dt");
            String sche_ed_dt = request.getParameter("sche_ed_dt");

            // CalendarVO 객체를 생성하여 일정을 담을 준비를 합니다.
            CalendarVO calendarVO = new CalendarVO();
            calendarVO.setSche_title(sche_title);    // 일정을 저장할 VO 객체에 제목을 설정
            calendarVO.setSche_st_dt(sche_st_dt);  // 시작일 설정
            calendarVO.setSche_ed_dt(sche_ed_dt);  // 종료일 설정

            // CalendarDAO 객체를 생성하여 데이터베이스와 상호작용할 준비를 합니다.
            CalendarDAO calendarDAO = new CalendarDAO();

            // DAO를 통해 데이터베이스에 일정을 추가하는 메소드를 호출합니다.
            // 'addEvent' 메소드는 boolean 값을 반환하여 일정 추가 성공 여부를 나타냅니다.
            boolean isSuccess = calendarDAO.addEvent(calendarVO);

            // 클라이언트에게 결과를 JSON 형태로 응답합니다.
            // 'success' 값은 일정 추가가 성공했는지 실패했는지 알려주는 플래그입니다.
            response.setContentType("application/json");  // 응답 타입을 JSON으로 설정
            PrintWriter out = response.getWriter();  // 응답 출력 스트림을 가져옴
            out.print("{\"success\": " + isSuccess + "}");  // 'success' 결과를 JSON 형태로 응답
            out.flush();  // 응답 버퍼를 비워서 데이터를 클라이언트에 전달
        }
    }
}