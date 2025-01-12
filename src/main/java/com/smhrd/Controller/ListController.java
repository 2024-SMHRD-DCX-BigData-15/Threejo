package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.smhrd.Model.OrderDAO;
import com.smhrd.Model.OrderVO;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 전달된 svc_idx 가져오기
        String svc_idx = request.getParameter("svc_idx");

        // 디버깅: 전달된 svc_idx 확인
        System.out.println("[DEBUG] 전달된 svc_idx: " + svc_idx);

        // DAO를 통해 게시글 데이터 조회
        OrderDAO dao = new OrderDAO();
        OrderVO order = dao.getOrderById(Integer.parseInt(svc_idx));

        // 디버깅: 조회된 데이터 확인
        System.out.println("[DEBUG] 조회된 게시글: " + order);

        // 데이터 전달 및 페이지 이동
        request.setAttribute("order", order);
        request.getRequestDispatcher("order_view.jsp").forward(request, response);
    }
}
