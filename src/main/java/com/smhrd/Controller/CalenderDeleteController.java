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
        request.setCharacterEncoding("UTF-8");

        // 삭제 요청 데이터 수집
        String sche_idx = request.getParameter("sche_idx");

        // 디버깅: 전달된 데이터 확인
        System.out.println("[DEBUG] 전달된 sche_idx: " + sche_idx);

        if (sche_idx == null || sche_idx.trim().isEmpty()) {
            System.out.println("[ERROR] sche_idx가 전달되지 않았습니다.");
            response.sendRedirect("calender.jsp?error=invalidData");
            return;
        }

        // DAO 호출
        CalendarDAO dao = new CalendarDAO();
        boolean isDeleted = dao.deleteEvent(Integer.parseInt(sche_idx));

        // 결과 처리
        if (isDeleted) {
            System.out.println("[DEBUG] 일정 삭제 성공");
            response.sendRedirect("calender.jsp");
        } else {
            System.out.println("[ERROR] 일정 삭제 실패");
            response.sendRedirect("calender.jsp?error=deleteFailed");
        }
    }
}
