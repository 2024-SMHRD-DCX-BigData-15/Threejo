package com.smhrd.Controller;
//1
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.MessageDAO;
import com.smhrd.Model.MessageVO;

@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // MessageDAO 객체 생성
        MessageDAO messageDAO = new MessageDAO();
        
        // 메시지 리스트 가져오기 (사용자 ID나 기타 정보로 필터링 가능)
        List<MessageVO> messages = messageDAO.getMessages("user_id"); // 예시로 "user_id"로 필터링
        
        // JSP에 메시지 데이터 전달
        request.setAttribute("messages", messages);
        
        // 메시지 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("message_box.jsp");
        dispatcher.forward(request, response);
    }
}
