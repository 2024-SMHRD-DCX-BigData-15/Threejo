package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.smhrd.Model.CalendarDAO;
import com.smhrd.Model.CalendarVO;

@WebServlet("/CalenderUpdateController")
public class CalenderUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 인코딩
        request.setCharacterEncoding("UTF-8");

        // 파라미터 수집
        String sche_title = request.getParameter("sche_title");
        String sche_st_dt = request.getParameter("sche_st_dt");
        String sche_ed_dt = request.getParameter("sche_ed_dt");

        // VO 객체 생성
        CalendarVO vo = new CalendarVO();
        vo.setSche_title(sche_title);
        vo.setSche_st_dt(sche_st_dt);
        vo.setSche_ed_dt(sche_ed_dt);

        // DAO 호출
        CalendarDAO dao = new CalendarDAO();
        boolean isSuccess = dao.updateEvent(vo);

        // 결과 처리
        if (isSuccess) {
            System.out.println("[DEBUG] 일정 수정 성공");
            response.sendRedirect("calender.jsp");
        } else {
            System.out.println("[DEBUG] 일정 수정 실패");
            response.sendRedirect("error.jsp");
        }
    }
}
