package com.smhrd.Controller;

import com.smhrd.Model.OrderDAO;
import com.smhrd.Model.OrderVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[ListController] 요청 처리 시작"); // 디버깅 로그

        // 전달된 svc_idx 파라미터 확인
        String svcIdxParam = request.getParameter("svc_idx");
        System.out.println("[DEBUG] 전달된 svc_idx (원본): " + svcIdxParam);

        if (svcIdxParam == null || svcIdxParam.trim().isEmpty()) {
            System.out.println("[ERROR] svc_idx가 전달되지 않았습니다.");
            response.sendRedirect("order_list.jsp");
            return;
        }

        try {
            int svc_idx = Integer.parseInt(svcIdxParam); // 파라미터를 정수로 변환
            System.out.println("[DEBUG] 변환된 svc_idx: " + svc_idx);

            // DAO를 통해 svc_idx로 데이터 조회
            OrderDAO dao = new OrderDAO();
            OrderVO order = dao.getOrderById(svc_idx);

            if (order != null) {
                System.out.println("[DEBUG] 조회된 OrderVO: " + order);

                // 조회된 데이터를 request에 저장하고 JSP로 전달
                request.setAttribute("order", order);
                request.getRequestDispatcher("order_view.jsp").forward(request, response);
            } else {
                System.out.println("[ERROR] svc_idx에 해당하는 데이터가 없습니다.");
                response.sendRedirect("order_list.jsp");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] svc_idx가 숫자가 아닙니다.");
            response.sendRedirect("order_list.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ERROR] 데이터 조회 중 오류 발생");
            response.sendRedirect("order_list.jsp");
        }
    }
}
