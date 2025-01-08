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

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩 (post방식으로 요청을 받았기 때문에)
		request.setCharacterEncoding("UTF-8");
		
		// 폼에서 전달된 데이터를 받아옴
		String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");
        String user_email = request.getParameter("user_email");
        String user_tell = request.getParameter("user_tell");
        
        // Member 객체 생성 (입력된 데이터를 객체에 담음)
        Member member = new Member(user_id, user_pw, user_email, user_tell);

        // 회원 정보 수정 처리
        boolean isUpdated = MemberDAO.updateId(member);
        

        if (isUpdated) {
        	response.sendRedirect("UpdatSuccess.jsp"); // 수정 성공 시 success.jsp로 리다이렉트
        } else {
        	response.sendRedirect("Error.jsp");   // 실패 시 error.jsp로 리다이렉트
        }
    }
}

