package com.smhrd.Controller;
//1
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.smhrd.Model.OrderDAO;

@WebServlet("/OrderDeleteController")
public class OrderDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 의뢰 삭제 요청 처리
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[OrderDeleteController] 요청 처리 시작");

        try {
            // 삭제할 게시글 번호 가져오기
            int svc_idx = Integer.parseInt(request.getParameter("svc_idx"));
            System.out.println("[OrderDeleteController] 삭제할 게시글 번호: " + svc_idx);

            // OrderDAO를 통해 데이터 삭제
            OrderDAO dao = new OrderDAO();
            int result = dao.deleteOrder(svc_idx);

            if (result > 0) {
                System.out.println("[OrderDeleteController] 게시글 삭제 성공");
            } else {
                System.err.println("[OrderDeleteController] 게시글 삭제 실패");
            }

        } catch (Exception e) {
            System.err.println("[OrderDeleteController] 요청 처리 중 예외 발생");
            e.printStackTrace();
        }

        // 의뢰 관리 페이지로 리다이렉트
        response.sendRedirect("OrderManageController");
    }
}
