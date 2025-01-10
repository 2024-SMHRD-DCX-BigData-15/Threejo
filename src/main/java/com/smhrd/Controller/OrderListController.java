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

@WebServlet("/OrderListController")
public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// OrderDAO 객체 생성
        OrderDAO orderDAO = new OrderDAO();
        
        // 의뢰 목록을 데이터베이스에서 불러옴
        List<OrderVO> orderList = orderDAO.getAllOrders();

        // JSP로 데이터 전달
        request.setAttribute("orderList", orderList);

        // order_list.jsp로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("order_list.jsp");
        dispatcher.forward(request, response);
    }
}