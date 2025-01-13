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
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 디버깅: 시작 로그
        System.out.println("[DEBUG] ListController - 요청 시작");

        // 요청 파라미터에서 svc_idx 가져오기
        String svcIdx = request.getParameter("svc_idx");
        System.out.println("[DEBUG] svc_idx: " + svcIdx); // 디버깅: 전달된 svc_idx 출력

        // svc_idx가 존재하는 경우 데이터 조회
        if (svcIdx != null) {
            try {
                // DAO를 통해 데이터 조회
                OrderDAO orderDAO = new OrderDAO();
                OrderVO order = orderDAO.getOrderById(Integer.parseInt(svcIdx));

                // 디버깅: 조회된 데이터 출력
                if (order != null) {
                    System.out.println("[DEBUG] 조회된 데이터: " + order.toString());
                } else {
                    System.out.println("[DEBUG] 해당 svc_idx에 대한 데이터가 없습니다.");
                }

                // 조회한 데이터를 request에 담기
                request.setAttribute("order", order);
            } catch (NumberFormatException e) {
                System.err.println("[ERROR] svc_idx 변환 중 오류 발생: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("[ERROR] 데이터 조회 중 오류 발생: " + e.getMessage());
            }
        } else {
            System.out.println("[DEBUG] svc_idx가 null입니다.");
        }

        // order_view.jsp로 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher("order_view.jsp");
        dispatcher.forward(request, response);

        // 디버깅: 종료 로그
        System.out.println("[DEBUG] ListController - 요청 종료");
    }
}
