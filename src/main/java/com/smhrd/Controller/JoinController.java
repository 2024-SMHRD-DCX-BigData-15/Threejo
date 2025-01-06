package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JoinController")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String tell = request.getParameter("email");
		String address = request.getParameter("tell");
		
		Member vo = new Member();
		vo.setId(id);
		vo.setPw(pw);
		vo.setTell(tell);
		vo.setAddress(address);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.join(vo);
		
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
