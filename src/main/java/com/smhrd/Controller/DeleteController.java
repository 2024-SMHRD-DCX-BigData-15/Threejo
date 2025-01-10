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

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 세션에서 로그인된 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        // 2. 클라이언트에서 입력받은 비밀번호 가져오기
        String user_pw = request.getParameter("user_pw");

        // 3. MemberVO 객체 생성 및 값 설정
        MemberVO member = new MemberVO();
        member.setUser_id(user_id);
        member.setUser_pw(user_pw);

        // 4. DAO를 통해 회원 삭제 로직 호출
        MemberDAO dao = new MemberDAO();
        boolean isDeleted = dao.deleteMember(member);

        // 5. 삭제 성공 여부에 따라 처리
        if (isDeleted) {
            session.invalidate(); // 세션 종료
            response.sendRedirect("deleteSuccess.jsp"); // 성공 페이지로 이동
        } else {
            // 삭제 실패 시 에러 메시지와 함께 삭제 페이지로 이동
            request.setAttribute("error", "비밀번호가 일치하지 않거나 삭제 실패");
            request.getRequestDispatcher("delete_account.jsp").forward(request, response);
        }
    }
}
