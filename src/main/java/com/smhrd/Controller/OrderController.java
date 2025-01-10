package com.smhrd.Controller;

import com.smhrd.Model.Order;
import com.smhrd.Model.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {

    // 의뢰 작성 데이터를 처리하는 POST 메서드
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 의뢰 데이터를 폼에서 가져오기
        int userId = Integer.parseInt(request.getParameter("user_Id")); // 사용자 ID
        String svcTitle = request.getParameter("svc_Title"); // 의뢰 제목
        String svcContent = request.getParameter("svc_content"); // 의뢰 내용
        String svcFile = request.getParameter("svc_file"); // 첨부 파일 경로

        // Request 객체 생성
        Order newRequest = new Order();
        newRequest.setUser_id(userId);
        newRequest.setSvc_title(svcTitle);
        newRequest.setSvc_content(svcContent);
        newRequest.setSvc_file(svcFile);

        // DAO를 통해 데이터베이스에 삽입
        OrderDAO dao = new OrderDAO();
        int result = dao.insertRequest(newRequest);

        // 처리 결과에 따라 이동
        if (result > 0) {
            response.sendRedirect("OrderListController");
        } else {
            response.sendRedirect("order_fail.jsp");
        }
    }

    // 의뢰 목록을 처리하는 GET 메서드
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // DAO 호출하여 모든 의뢰 데이터 가져오기
        OrderDAO dao = new OrderDAO();
        request.setAttribute("requests", dao.selectAllRequests());

        // JSP로 데이터 전달
        request.getRequestDispatcher("order_list.jsp").forward(request, response);
    }
}