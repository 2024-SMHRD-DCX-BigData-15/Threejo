package com.smhrd.Controller;

import com.smhrd.Model.OrderVO;
import com.smhrd.Model.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 요청 데이터의 한글 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        try {
            // 2. 세션에서 사용자 ID 가져오기
            HttpSession session = request.getSession();
            String svcId = (String) session.getAttribute("user_id");

            if (svcId == null || svcId.trim().isEmpty()) {
                System.err.println("로그인 상태가 아니거나 사용자 ID가 세션에 없습니다.");
                response.sendRedirect("login.jsp");
                return;
            }

            // 디버깅: 세션에서 가져온 사용자 ID 출력
            System.out.println("세션에서 가져온 svc_id: " + svcId);

            // 3. 요청 데이터 가져오기
            String svcTitle = request.getParameter("svc_title");         // 의뢰 제목
            String svcContent = request.getParameter("svc_content");     // 의뢰 내용
            String svcCategori = request.getParameter("svc_categori");   // 서비스 카테고리
            long svcAccount = Long.parseLong(request.getParameter("svc_account"));  // 의뢰 예산
            String svcEdTd = request.getParameter("svc_ed_td");          // 의뢰 완료일

            // 디버깅: 입력 값 확인
            System.out.println("입력된 값 -> 제목: " + svcTitle + ", 내용: " + svcContent + ", 카테고리: " + svcCategori + ", 예산: " + svcAccount + ", 완료일: " + svcEdTd);

            // 4. VO 객체 생성 및 값 설정
            OrderVO order = new OrderVO();
            order.setSvc_title(svcTitle);
            order.setSvc_content(svcContent);
            order.setSvc_categori(svcCategori);
            order.setSvc_account(svcAccount);
            order.setSvc_ed_td(svcEdTd);
            order.setSvc_id(svcId);

            // 5. DAO를 사용하여 데이터 삽입
            OrderDAO dao = new OrderDAO();
            int result = dao.insertOrder(order);

            // 디버깅: 데이터 삽입 결과 확인
            System.out.println("데이터 삽입 결과: " + result);

            // 6. 결과에 따라 페이지 이동
            if (result > 0) {
                response.sendRedirect("OrderListController");
            } else {
                response.sendRedirect("order_fail.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 디버깅: 예외 출력
            response.sendRedirect("order_fail.jsp");
        }
    }
}
