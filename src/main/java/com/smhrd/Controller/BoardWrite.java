package com.smhrd.Controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.Model.BoardDAO;
import com.smhrd.Model.BoardVO;

@WebServlet("/write")
public class BoardWrite extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      // 1. 요청 인코딩
      response.setCharacterEncoding("UTF-8");
      
      // 2. 데이터 가져오기
      //     -> request.getParameter로는 파일(이미지)를 가져올 수 없다.
      //     -> Cos라이브러리 필요 : form.xml에 dependency추가
      //     -> form태그의 인코딩방식 추가
      //     * multipartRequest(이미지 파일 요청) => cos라이브러리
      //     multipartRequest(request, 파일 저장 경로, 최대 파일 크기, 인코딩 타입,
      //                    파일 이름 생성 규칙)
      //     파일 이름 생성 규칙 : 한 폴더 안에 동일한 파일명이 들어가지 않도록 자동 수정 규칙
      
      // 파일 저장 경로 (절대 경로)
      ServletContext context = request.getServletContext(); 
      // servletContext : 여러 서블릿들이 중요한 정보를 공유할 수 있게 해주는 객체!
      String uploadPath = context.getRealPath("upload");
      // getRealPath : 내부에 위치한 폴더 또는 파일 경로를 가져옴.
      System.out.println(uploadPath);
      int maxSize = 500*1024*124; // 5MB
      MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
      String svc_title = multi.getParameter("svc_title");
      String svc_content = multi.getParameter("svc_content");
      String user_id = multi.getParameter("user_id");
      String svc_file = multi.getFilesystemName("svc_file");
      // 파일명만 가져와서 DB에는 파일명만 저장한다.
      // 실제 파일은 현재 사용하고 있는 컴퓨터 서버에만 저장하고 있다.
//      System.out.println(title);
//      System.out.println(content);
//      System.out.println(writer);
//      System.out.println(img);
      
      BoardVO uploadBoard = new BoardVO(svc_title, svc_content, user_id, svc_file);
      BoardDAO dao = new BoardDAO();
      int result = dao.writeBoard(uploadBoard);
      System.out.println(result);
      
      // 결과값 처리
      // insert문이 올바르게 실행이 되었다면 : result에는 0이 아닌 어떤 정수가 들어있음.
      // insert문이 실행되지 않았다면 : result에는 0이 들어있음
      
      if(result>0) {
         response.sendRedirect("boardlist.jsp");
      } else {
         response.sendRedirect("boradform.jsp");
      }

}
}
