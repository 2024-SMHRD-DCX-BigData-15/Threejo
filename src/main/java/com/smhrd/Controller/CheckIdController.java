package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.Member;
import com.smhrd.Model.MemberDAO;

@WebServlet("/CheckIdController")
public class CheckIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 중복된 주소를 판별하기 위한 기능
		// 0. 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		// 1. 주소 중복 체크
		String user_id = request.getParameter("user_id");
		MemberDAO dao = new MemberDAO();
		
		// MemberDao에서 checkAddr 메소드 생성 -> mapper까지 연결
		// sql문 : login과 비슷한 맥락으로 유추! (집 주소가 DB에 동일한 값이 있는지)
		boolean isDuplicate = dao.checkId(user_id);
		// checkAddr 메소드의 결과 값 : boolean(true, false)
		// checkAddr의 기능 : 중복값인지 판단하여 true, false를 전달!
		if (isDuplicate == true) {
			response.getWriter().write("duplicate");
		} else {
			// isDuplicate가 false인 경우
			response.getWriter().write("available");
		}

	}
}
