package com.smhrd.Controller;

import com.smhrd.Model.PaymentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentServletController extends HttpServlet {
    private final PaymentDAO paymentDAO = new PaymentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // 인증 토큰 가져오기
            String authToken = paymentDAO.getAuthToken();
            if (authToken == null) {
                throw new RuntimeException("Failed to retrieve auth token.");
            }

            // 고유한 merchant_uid 생성
            String merchantUid = "ORDER_" + System.currentTimeMillis();
            int amount = Integer.parseInt(request.getParameter("amount"));

            // 결제 준비 요청
            String prepareResult = paymentDAO.preparePayment(authToken, merchantUid, amount);

            if (prepareResult.contains("\"code\":1")) {
                throw new RuntimeException("Duplicate merchant_uid detected. Please use a unique ID.");
            }

            // 결과를 JSP로 전달
            request.setAttribute("result", prepareResult);
            request.setAttribute("merchant_uid", merchantUid);
            request.setAttribute("amount", amount);
            request.getRequestDispatcher("PaymentResult.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String merchantUid = request.getParameter("merchant_uid");
            if (merchantUid == null || merchantUid.isEmpty()) {
                throw new RuntimeException("merchant_uid parameter is missing.");
            }

            // 중복 제거: 인증 토큰 가져오기
            String authToken = getAuthToken();
            String status = paymentDAO.getPaymentStatus(authToken, merchantUid);

            // 디버그 로그 추가
            System.out.println("Merchant UID: " + merchantUid + ", Status: " + status);

            request.setAttribute("merchant_uid", merchantUid);
            request.setAttribute("status", status);
            request.getRequestDispatcher("PaymentResult.jsp").forward(request, response);
        } catch (Exception e) {
            // 명확한 에러 메시지 전달
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("error", "Failed to retrieve payment status: " + e.getMessage());
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }

    private String getAuthToken() {
        try {
            return paymentDAO.getAuthToken();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve auth token", e);
        }
    }
}