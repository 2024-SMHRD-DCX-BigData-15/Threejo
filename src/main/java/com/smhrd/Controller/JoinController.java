package com.smhrd.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.Member;
import com.smhrd.Model.MemberDAO;

@WebServlet("/JoinController")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// index.jsp -> 회원가입 폼으로부터 넘어온 회원 가입의 데이터를
		// 데이터베이스로 전달해서 회원가입 기능을 실행하는 Servlet
		
		// 0. 한글 인코딩 (post방식으로 요청을 받았기 때문에)
		request.setCharacterEncoding("UTF-8");
		
		// 1. 받아온 요청 데이터 꺼내오기 -> 4개
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String tell = request.getParameter("tell");
		
		//System.out.println(id + pw + tell + address);
		
		// 2. Mybatis의 특징 -> 데이터를 하나로 묶어서 전달해야한다!
		// Member -> 우리만의 새로운 자료형을 만들어보자! -> 객체형    
		Member vo = new Member();
		vo.setId(id);
		vo.setPw(pw);
		vo.setEmail(email);
		vo.setTell(tell);
		
		// 3. 데이터베이스에 해당 값을 전달 -> vo
		MemberDAO dao = new MemberDAO();
		int result = dao.join(vo);
		
		// 4. 결과에 따른 페이지 이동! 
		
		if(result > 0) {
			// joinsuccess.jsp로 이동
			RequestDispatcher rd = request.getRequestDispatcher("joinSuccess.jsp");
			rd.forward(request, response);
			
			
		}else {
			// 그렇지 않다면 index.jsp
			// -> 리다이렉트 방식으로 index.jsp
			response.sendRedirect("index.jsp");
		}
		
		
	}

}
