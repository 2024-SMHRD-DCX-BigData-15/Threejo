package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.MemberDAO;
import com.smhrd.Model.MemberVO;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션 무효화
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate(); // 세션 제거
        }

        // 메인 페이지로 리디렉션
        response.sendRedirect("main.jsp");
    }
}
