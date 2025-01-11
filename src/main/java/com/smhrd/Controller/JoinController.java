package com.smhrd.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.MemberVO;
import com.smhrd.Model.MemberDAO;
@WebServlet("/JoinController")
public class JoinController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 요청 데이터 인코딩
        request.setCharacterEncoding("UTF-8");

        // 2. 요청 파라미터로부터 데이터 가져오기
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");
        String user_email = request.getParameter("user_email");
        String user_tell = request.getParameter("user_tell");
        String user_role = request.getParameter("user_role");

        // 3. VO 생성 및 값 설정
        MemberVO vo = new MemberVO(user_id, user_pw, user_email, user_tell, user_role);

        // 4. DAO를 사용하여 회원가입 처리
        MemberDAO dao = new MemberDAO();
        int result = dao.join(vo);

        // 5. 결과에 따른 페이지 이동
        if (result > 0) {
            request.getRequestDispatcher("joinsuccess.jsp").forward(request, response);
        } else {
            response.sendRedirect("join.jsp");
        }
    }
}
