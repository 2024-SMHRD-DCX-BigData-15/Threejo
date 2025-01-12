package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.MemberVO;
import com.smhrd.Model.MemberDAO;
import com.smhrd.Model.MemberDTO;
@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 세션에서 로그인 정보를 가져오기
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        if (session == null || session.getAttribute("info") == null) {
            // 세션이 없거나 로그인 정보가 없으면 로그인 페이지로 리다이렉트
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. 세션에 저장된 객체 타입 확인 및 처리
        Object sessionInfo = session.getAttribute("info");
        if (sessionInfo != null) {
            System.out.println("세션에 저장된 객체 타입: " + sessionInfo.getClass().getName());
        } else {
            System.out.println("[ERROR] 세션에 저장된 객체가 null입니다.");
            response.sendRedirect("login.jsp");
            return;
        }

        if (sessionInfo instanceof MemberVO) {
            // MemberVO로 처리
            MemberVO sessionUser = (MemberVO) sessionInfo;
            String user_id = sessionUser.getUser_id();
            String user_pw = request.getParameter("user_pw");

            System.out.println("세션에서 가져온 user_id: " + user_id);
            System.out.println("클라이언트에서 입력받은 user_pw: " + user_pw);

            // 탈퇴 처리를 위한 MemberVO 생성
            MemberVO member = new MemberVO();
            member.setUser_id(user_id);
            member.setUser_pw(user_pw);

            // 3. DAO를 통해 회원 삭제 처리 호출
            MemberDAO dao = new MemberDAO();
            boolean isDeleted = dao.deleteMember(member);

            // 4. 삭제 결과에 따른 처리
            if (isDeleted) {
                System.out.println("회원 탈퇴 성공");
                session.invalidate(); // 세션 종료
                response.sendRedirect("deleteSuccess.jsp");
            } else {
                System.out.println("회원 탈퇴 실패");
                request.setAttribute("error", "비밀번호가 일치하지 않거나 삭제 실패");
                request.getRequestDispatcher("delete_account.jsp").forward(request, response);
            }
        } else if (sessionInfo instanceof MemberDTO) {
            // MemberDTO로 처리
            MemberDTO sessionUser = (MemberDTO) sessionInfo;
            String user_id = sessionUser.getUser_id();
            String user_pw = request.getParameter("user_pw");

            System.out.println("세션에서 가져온 user_id: " + user_id);
            System.out.println("클라이언트에서 입력받은 user_pw: " + user_pw);

            // 탈퇴 처리를 위한 MemberVO 생성
            MemberVO member = new MemberVO();
            member.setUser_id(user_id);
            member.setUser_pw(user_pw);

            // 3. DAO를 통해 회원 삭제 처리 호출
            MemberDAO dao = new MemberDAO();
            boolean isDeleted = dao.deleteMember(member);

            // 4. 삭제 결과에 따른 처리
            if (isDeleted) {
                System.out.println("회원 탈퇴 성공");
                session.invalidate(); // 세션 종료
                response.sendRedirect("deleteSuccess.jsp");
            } else {
                System.out.println("회원 탈퇴 실패");
                request.setAttribute("error", "비밀번호가 일치하지 않거나 삭제 실패");
                request.getRequestDispatcher("delete_account.jsp").forward(request, response);
            }
        } else {
            System.out.println("[ERROR] 세션에 저장된 객체가 MemberVO 또는 MemberDTO 타입이 아닙니다.");
            response.sendRedirect("login.jsp");
        }
    }
}
