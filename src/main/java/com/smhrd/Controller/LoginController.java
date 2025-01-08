package com.smhrd.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.Member;
import com.smhrd.Model.MemberDAO;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
      // 1. 인코딩
      request.setCharacterEncoding("UTF-8");
      // 2. index.jsp에서 받아온 데이터를 꺼내오기
      String id = request.getParameter("id");
      String pw = request.getParameter("pw");
      // 3. 데이터 처리하기(DB에 값을 보내서 원하는 조건 판단) Member DAO로 보내야한다.
      MemberDAO dao = new MemberDAO();
      // Member자료형의 vo라는 객체를 만든 이유 : id과 pw를 한번에 묶어서 전송하기 위함
      Member vo = new Member();
      vo.setUser_id(id);
      vo.setUser_pw(pw);
      
      Member result = dao.login(vo);// DB에 접근하기 위해 만들어놓은 메소드를 사용하겠다.
      // 4. 결과 출력하기 (result에 값이 있는지 없는지를 판별)
      if (result != null) {
         // result에 값이 있다면
         // (1)세션 영역 활용!
         HttpSession session = request.getSession();
         session.setAttribute("info", result);
         System.out.println("일치하는 값을 세션에 저장");
      }
      // result에 값이 없다면 => DB에 일치하는 로그인 정보가 없다. => index.jsp이동
      // result에 값이 있다면 => session에 데이터를 저장한 후, index.jsp이동
      response.sendRedirect("index.jsp");
      
      

   }
}