package com.smhrd.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.AskDAO;
import com.smhrd.Model.AskVO;


@WebServlet("/AskController")
public class AskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final AskDAO AskDAO = null;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	// 인코딩
			request.setCharacterEncoding("UTF-8");
			
			// 폼에서 전달된 데이터 받기
	        String title = request.getParameter("title");
	        String writer = request.getParameter("writer");
	        String content = request.getParameter("content");

	        // 입력 값 검증
	        if (title != null && !title.trim().isEmpty() && writer != null && !writer.trim().isEmpty() && content != null && !content.trim().isEmpty()) {
	            // VO 객체 생성 및 데이터 설정
	            AskVO inquiry = new AskVO(title, writer, content);

	            // DAO 객체를 사용하여 DB에 저장
	            AskDAO dao = new AskDAO();
	            try {
	                dao.insertInquiry(AskDAO);  // 데이터베이스에 문의사항 저장
	                response.getWriter().append("문의가 정상적으로 접수되었습니다.");
	            } catch (SQLException e) {
	                // SQL 예외 처리
	                e.printStackTrace();
	                response.getWriter().append("문의 접수 중 오류가 발생했습니다.");
	            }
	        } else {
	            // 필수 항목이 비어있으면 오류 메시지 출력
	            response.getWriter().append("모든 항목을 올바르게 입력해 주세요.");
	        }
	}
}