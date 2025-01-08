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
		 // 수정할 회원의 아이디를 요청 파라미터에서 받음
        String userid = request.getParameter("userid");

        // 회원 정보를 DB에서 가져오는 로직 (예시)
        Member member = MemberDAO.getMemberByUsername(userid);
        
        // 만약 회원 정보가 있으면 수정 페이지로 전달
        if (member != null) {
            // member 객체를 JSP로 전달
            request.setAttribute("member", member);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateForm.jsp");
            dispatcher.forward(request, response);
        } else {
            // 회원 정보가 없다면 오류 페이지로 이동
            response.sendRedirect("error.jsp");
        }
    }

    // doPost() 메서드는 수정된 회원 정보를 DB에 반영
    // 사용자가 수정한 정보를 처리하고 DB에 업데이트함
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 수정된 회원 정보를 요청 파라미터에서 받음
        String userid = request.getParameter("userid");
        String pw = request.getParameter("pw");
        String email = request.getParameter("email");
        String tell = request.getParameter("tell");

        // 수정된 정보를 Member 객체에 담음
        Member member = new Member(userid, pw, email, tell);
        
        // DB에 수정된 회원 정보 업데이트
        boolean result = MemberDAO.updateMember(member);
        
        // * 메인페이지로 수정 
        // 수정 성공 시 프로필 페이지로 이동
        if (result) {
            response.sendRedirect("profile.jsp");
        } else {
            // 수정 실패 시 오류 페이지로 이동
            response.sendRedirect("error.jsp");
        }
	}

}
