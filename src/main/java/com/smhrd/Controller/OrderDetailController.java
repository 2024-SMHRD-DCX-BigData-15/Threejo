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

    // 의뢰 상세 정보 요청 처리
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[OrderDetailController] 요청 처리 시작"); // 디버깅

        try {
            // 요청으로부터 svc_idx 파라미터 가져오기
            int svc_idx = Integer.parseInt(request.getParameter("svc_idx"));
            System.out.println("[OrderDetailController] 요청받은 svc_idx: " + svc_idx); // 디버깅

            // DAO를 통해 데이터 가져오기
            OrderDAO dao = new OrderDAO();
            OrderVO orderDetail = dao.getOrderBySvcIdx(svc_idx);

            // 조회된 데이터 JSP로 전달
            request.setAttribute("orderDetail", orderDetail);

            // order_detail.jsp로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
            dispatcher.forward(request, response);
            System.out.println("[OrderDetailController] 데이터 전달 및 포워딩 완료"); // 디버깅

        } catch (Exception e) {
            System.err.println("[OrderDetailController] 요청 처리 중 예외 발생");
            e.printStackTrace();
        }
    }
}
