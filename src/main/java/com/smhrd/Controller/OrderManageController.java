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

    // 의뢰 관리 페이지 요청 처리
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[OrderManageController] 요청 처리 시작"); // 디버깅

        // 세션에서 사용자 ID 가져오기
        String userId = (String) request.getSession().getAttribute("user_id");
        System.out.println("[OrderManageController] 세션에서 가져온 사용자 ID: " + userId); // 디버깅

        if (userId != null) {
            // OrderDAO를 통해 사용자 ID에 해당하는 데이터 조회
            OrderDAO orderDAO = new OrderDAO();
            List<OrderVO> userOrders = orderDAO.getOrdersByUserId(userId);

            System.out.println("[OrderManageController] 조회된 의뢰 데이터 수: " + userOrders.size()); // 디버깅

            // 조회된 데이터를 JSP로 전달
            request.setAttribute("userOrders", userOrders);

            // order_manage.jsp로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("order_manage.jsp");
            dispatcher.forward(request, response);
        } else {
            System.err.println("[OrderManageController] 사용자 ID가 세션에 없습니다."); // 디버깅
            response.sendRedirect("login.jsp"); // 로그인 페이지로 리다이렉트
        }

        System.out.println("[OrderManageController] 요청 처리 완료"); // 디버깅
    }
}
