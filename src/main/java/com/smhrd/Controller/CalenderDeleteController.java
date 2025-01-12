package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.CalendarDAO;

@WebServlet("/CalenderDeleteController")
public class CalenderDeleteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 데이터 인코딩
        request.setCharacterEncoding("UTF-8");

        // 전달된 sche_idx 값 확인
        String sche_idx_str = request.getParameter("sche_idx");
        System.out.println("[DEBUG] 전달된 sche_idx: " + sche_idx_str);

        int sche_idx = Integer.parseInt(sche_idx_str);

        // DAO를 통해 데이터 삭제
        CalendarDAO dao = new CalendarDAO();
        boolean isDeleted = dao.deleteEvent(sche_idx);

        // 디버깅: 삭제 성공 여부 확인
        if (isDeleted) {
            System.out.println("[DEBUG] 일정 삭제 성공");
            response.sendRedirect("calender.jsp");
        } else {
            System.out.println("[ERROR] 일정 삭제 실패");
            request.setAttribute("error", "일정 삭제에 실패했습니다.");
            request.getRequestDispatcher("calender.jsp").forward(request, response);
        }
    }
}
