package com.smhrd.Controller;

import com.smhrd.Model.IamportAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentServletController extends HttpServlet {
    private final IamportAPI iamportAPI = new IamportAPI();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authToken = iamportAPI.getAuthToken();
        if (authToken == null) {
            response.getWriter().write("Failed to get auth token");
            return;
        }

        // 고유한 merchant_uid 생성
        String merchantUid = "ORDER_" + System.currentTimeMillis();
        int amount = Integer.parseInt(request.getParameter("amount"));

        // 결제 준비 요청
        String prepareResult = iamportAPI.preparePayment(authToken, merchantUid, amount);

        // 기존에 중복된 merchant_uid인지 확인
        if (prepareResult.contains("\"code\":1")) {
            System.out.println("중복된 merchant_uid로 인해 요청 실패");
            response.getWriter().write("결제 실패: 중복된 merchant_uid");
            return;
        }

        // 결과를 JSP로 전달
        request.setAttribute("result", prepareResult);
        request.getRequestDispatcher("PaymentResult.jsp").forward(request, response);
    }
}
