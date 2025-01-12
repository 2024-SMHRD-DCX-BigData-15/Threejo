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

            // 세션에 user_id가 없으면 로그인 페이지로 리다이렉트
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

            // 디버깅: 일정 추가 성공 여부
            System.out.println("[DEBUG] 일정 추가 성공 여부: " + isSuccess);

            // 일정 추가 결과에 따라 페이지 이동
            if (isSuccess) {
                // 일정 추가 성공 시 일정 관리 페이지로 이동
                request.setAttribute("message", "일정이 성공적으로 추가되었습니다.");
                request.getRequestDispatcher("calender.jsp").forward(request, response);
            } else {
                // 일정 추가 실패 시 에러 메시지와 함께 일정 관리 페이지로 이동
                request.setAttribute("error", "일정 추가에 실패했습니다. 다시 시도해주세요.");
                request.getRequestDispatcher("calender.jsp").forward(request, response);
            }
        }
    }
}
