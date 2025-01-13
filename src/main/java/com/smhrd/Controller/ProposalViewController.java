package com.smhrd.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.Model.ProposalDAO;
import com.smhrd.Model.ProposalVO;

@WebServlet("/ProposalViewController")
public class ProposalViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProposalViewController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[DEBUG] ProposalViewController 호출됨");

        // 요청된 prop_idx 파라미터 확인
        String propIdxParam = request.getParameter("prop_idx");
        int prop_idx = -1;
        if (propIdxParam != null && !propIdxParam.isEmpty()) {
            try {
                prop_idx = Integer.parseInt(propIdxParam);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] prop_idx 파싱 오류: " + propIdxParam);
            }
        }
        System.out.println("[DEBUG] 요청된 prop_idx: " + prop_idx);

        // DAO를 통해 데이터베이스에서 제안서 가져오기
        ProposalDAO dao = new ProposalDAO();
        ProposalVO proposal = dao.getProposalById(prop_idx);
        System.out.println("[DEBUG] 조회된 제안서: " + proposal);

        // JSP에 데이터 전달
        request.setAttribute("proposal", proposal);

        // proposal_view.jsp로 포워딩
        request.getRequestDispatcher("proposal_view.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
