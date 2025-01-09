package com.smhrd.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.MemberDAO;


@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 // 사용자의 탈퇴를 처리하는 메소드
    private boolean deleteMember(String user_id, String user_pw) {
        // MemberDAO의 deleteMember 메소드를 호출하여 비밀번호 확인 및 삭제 처리
        return MemberDAO.deleteMember(user_id, user_pw);
    }

    // POST 요청을 처리하는 메소드
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 로그인된 사용자의 ID를 가져옴
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id"); // 로그인한 사용자의 ID

        // 사용자로부터 입력받은 비밀번호
        String user_pw = request.getParameter("user_pw");

        // 입력한 비밀번호와 사용자가 맞는지 확인하고, 맞다면 삭제 시도
        boolean isDeleted = deleteMember(user_id, user_pw);

        if (isDeleted) {
            // 탈퇴가 성공적으로 처리되면 세션 종료
            session.invalidate();  // 세션 무효화
            response.sendRedirect("deleteSuccess.jsp");  // 탈퇴 성공 페이지로 리디렉션
        } else {
            // 삭제 실패 시 오류 메시지 처리
            request.setAttribute("error", "비밀번호가 일치하지 않거나 삭제에 실패했습니다.");
            request.getRequestDispatcher("DeleteMember.jsp").forward(request, response);  // 탈퇴 페이지로 돌아감
        }
    }
}