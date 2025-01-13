package com.smhrd.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 응답 인코딩 설정
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");	
    	
    	System.out.println("[DEBUG] ProposalBoxController 호출됨");
        response.getWriter().println("Controller 호출 테스트");

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String svc_idxStr = request.getParameter("svc_idx");

            System.out.println("[DEBUG] 세션 user_id: " + user_id);
            System.out.println("[DEBUG] 요청된 svc_idx: " + svc_idxStr);

            int svc_idx = (svc_idxStr != null) ? Integer.parseInt(svc_idxStr) : -1;

            ProposalDAO dao = new ProposalDAO();

            // 보낸 제안서 조회
            List<ProposalVO> sentProposals = dao.getSentProposals(user_id);
            System.out.println("[DEBUG] 보낸 제안서 수: " + sentProposals.size());

            // 받은 제안서 조회
            List<ProposalVO> receivedProposals = (svc_idx != -1) ? dao.getReceivedProposals(svc_idx, user_id) : new ArrayList<>();
            System.out.println("[DEBUG] 받은 제안서 조회 결과: " + receivedProposals.size() + "건");
            for (ProposalVO proposal : receivedProposals) {
                System.out.println("[DEBUG] 받은 제안서 내용: " + proposal);
            }


            // 데이터 설정
            request.setAttribute("sentProposals", sentProposals);
            request.setAttribute("receivedProposals", receivedProposals);

            RequestDispatcher dispatcher = request.getRequestDispatcher("proposal_box.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.err.println("[ERROR] ProposalBoxController 예외 발생");
            e.printStackTrace();
        }
    }
}
