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
        
        System.out.println("user_id: " + user_id);
        System.out.println("user_pw: " + user_pw);

        // 3. VO 객체 생성 및 설정
        MemberVO vo = new MemberVO();
        vo.setUser_id(user_id);
        vo.setUser_pw(user_pw);

        // 4. DAO를 사용하여 로그인 처리
        MemberDAO dao = new MemberDAO();
        MemberVO result = dao.login(vo);

        // 5. 비밀번호 비교
        if (result != null) {
            // 저장된 비밀번호와 입력한 비밀번호를 비교
            if (result.getUser_pw().equals(user_pw)) {
                // 로그인 성공: 세션에 사용자 정보 저장
                request.getSession().setAttribute("info", result);
                response.sendRedirect("main.jsp");  // 메인 페이지로 이동
            } else {
                // 비밀번호가 틀린 경우
                request.setAttribute("errorMessage", "비밀번호가 틀립니다.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // ID가 없는 경우
            request.setAttribute("errorMessage", "아이디가 존재하지 않습니다.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
