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
            // 수정 성공 시, 수정된 회원 정보를 request에 저장하고, success 페이지로 이동
            request.setAttribute("member", member);
            response.sendRedirect("updateSuccess.jsp"); // 수정 완료 페이지로 리디렉션
        } else {
            // 수정 실패 시, 오류 메시지 전달
            request.setAttribute("error", "회원 정보 수정에 실패했습니다.");
            request.getRequestDispatcher("updateForm.jsp").forward(request, response);
        }
    }
}

