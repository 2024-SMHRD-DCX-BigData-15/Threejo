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

// OrderListController 서블릿: 의뢰 데이터를 불러와 JSP로 전달하는 역할
@WebServlet("/OrderListController")
public class OrderListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP 요청을 처리하고 의뢰 데이터를 가져와 JSP로 전달
     * 
     * @param request  클라이언트의 요청 객체
     * @param response 서버의 응답 객체
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[OrderListController] 요청 처리 시작"); // 디버깅: 요청 시작 메시지

        // OrderDAO 객체 생성 (DB 연동을 위한 객체)
        OrderDAO orderDAO = new OrderDAO();
        System.out.println("[OrderListController] OrderDAO 객체 생성 완료"); // 디버깅: DAO 생성 확인

        // 의뢰 목록을 데이터베이스에서 불러옴
        List<OrderVO> orderList = orderDAO.getAllOrders();
        if (orderList != null && !orderList.isEmpty()) {
            System.out.println("[OrderListController] 데이터베이스에서 의뢰 데이터 불러오기 성공"); // 디버깅
            System.out.println("[OrderListController] 불러온 데이터 수: " + orderList.size()); // 디버깅
        } else {
            System.out.println("[OrderListController] 불러온 데이터가 없습니다."); // 디버깅
        }

        // JSP로 데이터 전달
        request.setAttribute("orderList", orderList);
        System.out.println("[OrderListController] 의뢰 데이터를 JSP로 전달 완료"); // 디버깅

        // order_list.jsp로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("order_list.jsp");
        dispatcher.forward(request, response);
        System.out.println("[OrderListController] 요청을 order_list.jsp로 포워딩 완료"); // 디버깅

        System.out.println("[OrderListController] 요청 처리 완료"); // 디버깅: 요청 종료 메시지
    }
}
