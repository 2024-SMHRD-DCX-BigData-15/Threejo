package com.smhrd.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.OrderDAO;
import com.smhrd.Model.OrderVO;

@WebServlet("/OrderManageController")
public class OrderManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDAO = new OrderDAO();
        List<OrderVO> orders = orderDAO.getAllOrders();  // OrderDAO에서 모든 의뢰 목록을 가져옵니다.

        // 의뢰 목록을 request 속성에 설정
        request.setAttribute("orders", orders);

        // 의뢰 관리 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("order_manage.jsp");
        dispatcher.forward(request, response);
    }
}