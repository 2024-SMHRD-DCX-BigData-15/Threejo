package com.smhrd.Controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.MemberDAO;
import com.smhrd.Model.MemberDTO;

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // 0. 한글 인코딩 (post방식으로 요청을 받았기 때문에)
      request.setCharacterEncoding("UTF-8");

      // 2. 요청객체로부터 데이터 값 꺼내기
      String user_id = request.getParameter("user_id");
      String user_email = request.getParameter("user_email");
      String user_tell = request.getParameter("user_tell");
      // 3. 회원정보 수정을 위한 데이터 처리 -> DB 데이터 저장하는 과정!

      MemberDTO dto = new MemberDTO(user_id, user_email, user_tell);
      MemberDAO dao = new MemberDAO();
      int result = dao.update(dto);

      // 4. 데이터 처리결과에 따른 결과 화면 처리
      if (result>0) {
         // 회원정보 수정 성공 : 회원 정보를 가지고 페이지 이동
         // email이라는 이름으로 email데이터를 넣을거임.
         HttpSession session = request.getSession();
         session.setAttribute("info", dto);
         response.sendRedirect("updatesuccess.jsp");

      } else {
         // 회원정보 수정 실패
         System.out.println("정보 수정 실패");
         response.sendRedirect("mypage.jsp");
      }

   }

}
