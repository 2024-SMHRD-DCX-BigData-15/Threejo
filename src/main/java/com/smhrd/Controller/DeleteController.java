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

        // 2. 세션에서 MemberVO 객체 가져오기
        MemberDTO sessionUser = (MemberDTO) session.getAttribute("info");
        String user_id = sessionUser.getUser_id(); // 세션에서 user_id 추출

        // 3. 요청에서 클라이언트가 입력한 비밀번호 가져오기
        String user_pw = request.getParameter("user_pw");

        // 디버깅: 가져온 값 출력
        System.out.println("세션에서 가져온 user_id: " + user_id);
        System.out.println("클라이언트에서 입력받은 user_pw: " + user_pw);

        // 4. 탈퇴 처리를 위한 MemberVO 생성
        MemberVO member = new MemberVO();
        member.setUser_id(user_id);
        member.setUser_pw(user_pw);

        // 5. DAO를 통해 회원 삭제 처리 호출
        MemberDAO dao = new MemberDAO();
        boolean isDeleted = dao.deleteMember(member);

        // 6. 삭제 결과에 따른 처리
        if (isDeleted) {
            // 회원탈퇴 성공 시 세션 무효화 및 성공 페이지로 이동
            System.out.println("회원 탈퇴 성공");
            session.invalidate(); // 세션 종료
            response.sendRedirect("deleteSuccess.jsp");
        } else {
            // 회원탈퇴 실패 시 에러 메시지와 함께 삭제 페이지로 이동
            System.out.println("회원 탈퇴 실패");
            request.setAttribute("error", "비밀번호가 일치하지 않거나 삭제 실패");
            request.getRequestDispatcher("delete_account.jsp").forward(request, response);
        }
    }
}
