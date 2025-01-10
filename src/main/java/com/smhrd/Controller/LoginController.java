package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.MemberVO;
import com.smhrd.Model.MemberDAO;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 데이터 인코딩
        request.setCharacterEncoding("UTF-8");

        // 파라미터 가져오기
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");

        // VO 객체 생성
        MemberVO vo = new MemberVO(user_id, user_pw, null, null, null);

        // DAO 호출
        MemberDAO dao = new MemberDAO();
        MemberVO result = dao.login(vo);

        if (result == null) {
            request.setAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // 로그인 성공 처리
            request.getSession().setAttribute("info", result);
            response.sendRedirect("main.jsp");
        }
    }

}
