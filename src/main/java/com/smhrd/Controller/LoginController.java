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
        // 1. 요청 데이터 인코딩
        request.setCharacterEncoding("UTF-8");

        // 2. 요청 데이터 가져오기
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");

        // 3. VO 객체 생성 및 설정
        MemberVO vo = new MemberVO();
        vo.setUser_id(user_id);
        vo.setUser_pw(user_pw);

        // 4. DAO를 사용하여 로그인 처리
        MemberDAO dao = new MemberDAO();
        MemberVO result = dao.login(vo);

        // 5. 결과에 따른 세션 저장 및 페이지 이동
        if (result != null) {
            request.getSession().setAttribute("info", result);
        }
        response.sendRedirect("main.jsp");
    }
}
