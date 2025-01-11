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

@WebServlet("/OrderListController")
public class OrderListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 의뢰 목록을 조회하여 JSP로 전달하는 메서드
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
