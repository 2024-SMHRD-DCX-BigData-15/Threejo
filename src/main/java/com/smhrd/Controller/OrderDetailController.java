package com.smhrd.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.OrderDAO;
import com.smhrd.Model.OrderVO;

@WebServlet("/OrderDetailController")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 요청 파라미터로 의뢰 ID 받기
        String orderId = request.getParameter("orderId");

        // OrderDAO 객체 생성
        OrderDAO orderDAO = new OrderDAO();
        
        // 의뢰 상세 정보 가져오기
        OrderVO order = orderDAO.getOrderById(orderId);
        
        // 의뢰 정보와 댓글 목록을 JSP로 전달
        request.setAttribute("order", order);

        // order_detail.jsp로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
        dispatcher.forward(request, response);
    }
}
