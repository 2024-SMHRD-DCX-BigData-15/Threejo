package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.CalendarDAO;
import com.smhrd.Model.CalendarVO;

@WebServlet("/CalenderController")
public class CalenderController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 작업 구분
        String action = request.getParameter("action");
        if ("addEvent".equals(action)) {
            // 파라미터 수집
            String sche_title = request.getParameter("sche_title");
            String sche_st_dt = request.getParameter("sche_st_dt");
            String sche_ed_dt = request.getParameter("sche_ed_dt");
            String user_id = (String) request.getSession().getAttribute("user_id");

            if (user_id == null) {
                System.out.println("[DEBUG] 세션에 user_id가 없어 로그인 페이지로 리다이렉트합니다.");
                response.sendRedirect("login.jsp");
                return;
            }

            // VO 생성 및 값 설정
            CalendarVO vo = new CalendarVO(sche_title, sche_st_dt, sche_ed_dt, user_id);

            // DAO를 통한 일정 추가
            CalendarDAO dao = new CalendarDAO();
            boolean isSuccess = dao.addEvent(vo);

            // 결과를 JSON 형태로 반환
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": " + isSuccess + "}");
        }
    }
}
