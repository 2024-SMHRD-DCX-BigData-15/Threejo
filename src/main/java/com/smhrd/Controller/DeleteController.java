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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        // 2. 사용자 비밀번호 요청 파라미터에서 가져오기
        String user_pw = request.getParameter("user_pw");

        // 3. DAO를 사용하여 회원 삭제 처리
        boolean isDeleted = MemberDAO.deleteMember(user_id, user_pw);

        // 4. 결과에 따라 페이지 이동
        if (isDeleted) {
            session.invalidate(); // 세션 종료
            response.sendRedirect("deleteSuccess.jsp");
        } else {
            request.setAttribute("error", "비밀번호가 일치하지 않거나 삭제 실패");
            request.getRequestDispatcher("delete_account.jsp").forward(request, response);
        }
    }
}
