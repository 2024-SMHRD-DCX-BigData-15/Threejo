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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[DEBUG] ProposalViewController 호출됨");

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

        ProposalDAO dao = new ProposalDAO();
        ProposalVO proposal = dao.getProposalById(prop_idx);
        System.out.println("[DEBUG] 조회된 제안서: " + proposal);

        request.setAttribute("proposal", proposal);
        request.getRequestDispatcher("proposal_view.jsp").forward(request, response);
    }
}
