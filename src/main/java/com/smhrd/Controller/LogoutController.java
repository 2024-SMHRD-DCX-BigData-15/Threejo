package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃 기능을 실행하는 공간
	      // 로그아웃 : 세션의 데이터를 모두 지우기
	      // 1. session 꺼내오기
	      HttpSession session = request.getSession();
	      // 2. session 무효화 시켜주기(모든 데이터 지우기)
	      session.invalidate();
	      // 3. redirect방식으로 이동
	      response.sendRedirect("index.jsp");
	}

}
