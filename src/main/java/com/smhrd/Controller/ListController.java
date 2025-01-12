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
    	 System.out.println("[OrderListController] 요청 처리 시작"); // 디버깅: 요청 시작 로그

         try {
             // DAO 객체 생성
             OrderDAO orderDAO = new OrderDAO();
             System.out.println("[OrderListController] OrderDAO 객체 생성 완료"); // 디버깅: DAO 생성 로그

             // 데이터베이스에서 의뢰 목록 조회
             List<OrderVO> orderList = orderDAO.getAllOrders();
             System.out.println("[OrderListController] 데이터베이스에서 의뢰 데이터 조회 성공"); // 디버깅: 데이터 조회 성공 로그
             System.out.println("[OrderListController] 조회된 데이터 수: " + orderList.size()); // 디버깅: 데이터 수 출력

             // 조회한 데이터를 JSP로 전달
             request.setAttribute("orderList", orderList);
             System.out.println("[OrderListController] 조회 데이터를 JSP로 전달 완료"); // 디버깅: 데이터 전달 로그

             // order_list.jsp로 포워딩
             RequestDispatcher dispatcher = request.getRequestDispatcher("order_list.jsp");
             dispatcher.forward(request, response);
             System.out.println("[OrderListController] JSP로 포워딩 완료"); // 디버깅: 포워딩 성공 로그

         } catch (Exception e) {
             // 예외 발생 시 로그 출력
             System.err.println("[OrderListController] 에러 발생: " + e.getMessage());
             e.printStackTrace();
             throw new ServletException(e);
         }

         System.out.println("[OrderListController] 요청 처리 완료"); // 디버깅: 요청 완료 로그
     }
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
