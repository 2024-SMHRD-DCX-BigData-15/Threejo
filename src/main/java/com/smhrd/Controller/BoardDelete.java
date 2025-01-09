package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.BoardDAO;

@WebServlet("/BoardDelete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setCharacterEncoding("UTF-8");
	      int idx = Integer.parseInt(request.getParameter("idx"));
	      
	      BoardDAO dao = new BoardDAO();
	      int result = dao.delete(idx);
	      
	      if(result>0) {
	         response.sendRedirect("boardlist.jsp");
	      } else {
	         response.sendRedirect("boardcontent?"+idx);
	    }	   
	}
}