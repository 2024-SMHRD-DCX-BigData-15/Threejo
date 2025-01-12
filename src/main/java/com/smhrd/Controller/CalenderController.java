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
        request.setCharacterEncoding("UTF-8");

        // 작업 구분
        String action = request.getParameter("action");
        if ("addEvent".equals(action)) {
        	
        	System.out.println("[DEBUG] action 값: " + action);
        	System.out.println("[DEBUG] 요청된 일정 추가 작업 시작");
        	
            // 파라미터 수집
            String sche_title = request.getParameter("sche_title");
            String sche_st_dt = request.getParameter("sche_st_dt");
            String sche_ed_dt = request.getParameter("sche_ed_dt");
            String user_id = (String) request.getSession().getAttribute("user_id");
            
         // 디버깅 코드 추가
            System.out.println("[DEBUG] 전달된 sche_title: " + sche_title);
            System.out.println("[DEBUG] 전달된 sche_st_dt: " + sche_st_dt);
            System.out.println("[DEBUG] 전달된 sche_ed_dt: " + sche_ed_dt);
            System.out.println("[DEBUG] 전달된 user_id: " + user_id);
            
            // 세션에 user_id가 없으면 로그인 페이지로 리다이렉트
            if (user_id == null) {
                System.out.println("[DEBUG] 세션에 user_id가 없어 로그인 페이지로 리다이렉트합니다.");
                response.sendRedirect("login.jsp");
                return;
            }

            // VO 생성 및 값 설정
            CalendarVO vo = new CalendarVO();
            vo.setSche_title(sche_title);
            vo.setSche_st_dt(sche_st_dt);
            vo.setSche_ed_dt(sche_ed_dt);
            vo.setUser_id(user_id);

            // DAO를 통한 일정 추가
            CalendarDAO dao = new CalendarDAO();
            boolean isSuccess = dao.addEvent(vo);

            // 디버깅: 일정 추가 성공 여부
            System.out.println("[DEBUG] 일정 추가 성공 여부: " + isSuccess);

            // 일정 추가 결과에 따라 페이지 이동
            if (isSuccess) {
                request.setAttribute("message", "일정이 성공적으로 추가되었습니다.");
                request.getRequestDispatcher("calender.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "일정 추가에 실패했습니다. 다시 시도해주세요.");
                request.getRequestDispatcher("calender.jsp").forward(request, response);
            }
        }
    }
}
