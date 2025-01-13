package com.smhrd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.PaymentDAO;
import com.smhrd.Model.PaymentVO;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[DEBUG] PaymentController 호출됨");

        // 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        // 클라이언트 요청 데이터 처리
        String prop_title = request.getParameter("prop_title");
        long prop_account = Long.parseLong(request.getParameter("prop_account"));
        int prop_idx = Integer.parseInt(request.getParameter("prop_idx"));

        // DAO를 통해 사용자 이메일 및 전화번호 조회
        PaymentDAO dao = new PaymentDAO();
        String user_email = dao.getUserEmailById(user_id);
        String user_tell = dao.getUserTellById(user_id);

        // 기본값 설정
        if (user_email == null) user_email = "default_email@example.com";
        if (user_tell == null) user_tell = "000-0000-0000";

        // PaymentVO 객체 생성
        PaymentVO payment = new PaymentVO(0, prop_title, prop_account, user_email, user_id, user_tell, prop_idx);
        System.out.println("[DEBUG] PaymentVO 생성: " + payment);

        // 결제 정보 삽입
        int pay_idx = dao.insertPayment(payment);

        if (pay_idx > 0) {
            System.out.println("[DEBUG] 결제 정보 삽입 성공, pay_idx: " + pay_idx);
            response.sendRedirect("payment.jsp?pay_idx=" + pay_idx);
        } else {
            System.out.println("[ERROR] 결제 정보 삽입 실패");
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
