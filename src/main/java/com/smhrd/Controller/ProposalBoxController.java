package com.smhrd.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.Model.ProposalDAO;
import com.smhrd.Model.ProposalVO;

@WebServlet("/ProposalBoxController")
public class ProposalBoxController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[DEBUG] ProposalBoxController 호출됨");

        // 세션에서 로그인 사용자 정보 가져오기
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        System.out.println("[DEBUG] 세션 user_id: " + user_id);

        // 요청된 svc_idx 파라미터 확인
        String svcIdxParam = request.getParameter("svc_idx");
        Integer svc_idx = -1;
        if (svcIdxParam != null && !svcIdxParam.isEmpty()) {
            try {
                svc_idx = Integer.parseInt(svcIdxParam);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] svc_idx 파싱 오류: " + svcIdxParam);
            }
        }
        System.out.println("[DEBUG] 요청된 svc_idx: " + svc_idx);

        // DAO 객체 생성
        ProposalDAO dao = new ProposalDAO();

        // 보낸 제안서 조회
        List<ProposalVO> sentProposals = dao.getSentProposals(user_id);
        System.out.println("[DEBUG] 보낸 제안서 조회 결과: " + sentProposals);

        // 받은 제안서 조회
        List<ProposalVO> receivedProposals = new ArrayList<>();
        if (svc_idx != -1) {
            System.out.println("[DEBUG] DAO로 받은 제안서 조회 호출 - svc_idx: " + svc_idx + ", user_id: " + user_id);
            receivedProposals = dao.getReceivedProposals(svc_idx, user_id);
            System.out.println("[DEBUG] 받은 제안서 조회 결과: " + receivedProposals.size() + "건");
            for (ProposalVO proposal : receivedProposals) {
                System.out.println("[DEBUG] 받은 제안서 내용: " + proposal);
            }
        } else {
            System.out.println("[DEBUG] 유효하지 않은 svc_idx: " + svc_idx);
        }

        // JSP에 데이터 전달
        request.setAttribute("sentProposals", sentProposals);
        request.setAttribute("receivedProposals", receivedProposals);

        // proposal_box.jsp로 포워딩
        request.getRequestDispatcher("proposal_box.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
