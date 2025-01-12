package com.smhrd.Controller;

import com.smhrd.Model.OrderDAO;
import com.smhrd.Model.OrderVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 전달된 svc_idx 파라미터 확인
        String svcIdxParam = request.getParameter("svc_idx");
        System.out.println("[DEBUG] 전달된 svc_idx: " + svcIdxParam);

        if (svcIdxParam == null || svcIdxParam.trim().isEmpty()) {
            System.out.println("[ERROR] svc_idx가 전달되지 않았습니다.");
            response.sendRedirect("order_list.jsp");
            return;
        }

        try {
            int svc_idx = Integer.parseInt(svcIdxParam);

            // DAO를 통해 svc_idx로 데이터 조회
            OrderDAO dao = new OrderDAO();
            OrderVO order = dao.getOrderById(svc_idx);

            if (order != null) {
                System.out.println("[DEBUG] 조회된 게시글 데이터: " + order);

                // 조회된 데이터를 request에 저장하고 order_view.jsp로 전달
                request.setAttribute("order", order);
                request.getRequestDispatcher("order_view.jsp").forward(request, response);
            } else {
                System.out.println("[ERROR] svc_idx에 해당하는 데이터가 없습니다.");
                response.sendRedirect("order_list.jsp");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] svc_idx 파라미터가 숫자가 아닙니다.");
            response.sendRedirect("order_list.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ERROR] 게시글 조회 중 오류 발생");
            response.sendRedirect("order_list.jsp");
        }
    }
}
