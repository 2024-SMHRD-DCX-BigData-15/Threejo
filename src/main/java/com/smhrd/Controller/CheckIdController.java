package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.MemberVO;
import com.smhrd.Model.MemberDAO;
//1
@WebServlet("/CheckIdController")
public class CheckIdController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. 요청 데이터 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 2. 클라이언트로부터 사용자 ID를 받아옴
        String user_id = request.getParameter("user_id");

        // 3. DAO를 사용하여 ID 중복 여부 확인
        MemberDAO dao = new MemberDAO();
        boolean isDuplicate = dao.checkId(user_id);

        // 4. 결과 반환
        response.getWriter().write(isDuplicate ? "duplicate" : "available");
    }
}
