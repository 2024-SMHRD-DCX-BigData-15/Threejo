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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    // 디버깅: 요청 데이터 확인
	    System.out.println("[DEBUG] 전달된 데이터 확인");
	    System.out.println("[DEBUG] sche_idx: " + request.getParameter("sche_idx"));
	    System.out.println("[DEBUG] sche_title: " + request.getParameter("sche_title"));
	    System.out.println("[DEBUG] sche_st_dt: " + request.getParameter("sche_st_dt"));
	    System.out.println("[DEBUG] sche_ed_dt: " + request.getParameter("sche_ed_dt"));

	    try {
	        // 요청 데이터 수집
	        String sche_idx = request.getParameter("sche_idx");
	        String sche_title = request.getParameter("sche_title");
	        String sche_st_dt = request.getParameter("sche_st_dt");
	        String sche_ed_dt = request.getParameter("sche_ed_dt");

	        // 필수 데이터 유효성 검사
	        if (sche_idx == null || sche_title == null || sche_st_dt == null || sche_ed_dt == null) {
	            throw new IllegalArgumentException("필수 데이터가 누락되었습니다.");
	        }

	        // VO 객체 생성
	        CalendarVO vo = new CalendarVO();
	        vo.setSche_idx(Integer.parseInt(sche_idx));
	        vo.setSche_title(sche_title);
	        vo.setSche_st_dt(sche_st_dt);
	        vo.setSche_ed_dt(sche_ed_dt);

	        // DAO 호출
	        CalendarDAO dao = new CalendarDAO();
	        int result = dao.updateEvent(vo);

	        // 처리 결과 확인
	        if (result > 0) {
	            System.out.println("[DEBUG] 일정 수정 성공");
	            response.sendRedirect("calender.jsp");
	        } else {
	            System.out.println("[DEBUG] 일정 수정 실패");
	            response.sendRedirect("calender.jsp?error=update_failed");
	        }
	    } catch (Exception e) {
	        System.out.println("[ERROR] 일정 수정 중 오류 발생");
	        e.printStackTrace();
	        response.sendRedirect("calender.jsp?error=exception");
	    }
	}
}