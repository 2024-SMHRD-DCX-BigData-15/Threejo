package com.smhrd.Controller;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.smhrd.Model.ProposalDAO;
import com.smhrd.Model.ProposalVO;

@WebServlet("/ProposalWriteController")
public class ProposalWriteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 System.out.println("[DEBUG] ProposalWriteController - 요청 시작");
    	    try {
    	        // 요청 파라미터 확인
    	        String prop_title = request.getParameter("prop_title");
    	        String prop_content = request.getParameter("prop_content");
    	        String prop_accountStr = request.getParameter("prop_account");
    	        String prop_ed_tdStr = request.getParameter("prop_ed_td");
    	        String prop_tell = request.getParameter("prop_tell");
    	        String svc_idxStr = request.getParameter("svc_idx");
    	        String prop_id = request.getParameter("prop_id");

    	        System.out.println("[DEBUG] 요청된 파라미터 확인:");
    	        System.out.println("prop_title: " + prop_title);
    	        System.out.println("prop_content: " + prop_content);
    	        System.out.println("prop_account: " + prop_accountStr);
    	        System.out.println("prop_ed_td: " + prop_ed_tdStr);
    	        System.out.println("prop_tell: " + prop_tell);
    	        System.out.println("svc_idx: " + svc_idxStr);
    	        System.out.println("prop_id: " + prop_id);

    	        // 유효성 검사 (필요시 추가)
    	        if (prop_title == null || prop_content == null || prop_accountStr == null || prop_ed_tdStr == null || prop_tell == null || svc_idxStr == null || prop_id == null) {
    	            throw new IllegalArgumentException("모든 입력값이 필요합니다.");
    	        }

    	        // 파라미터 변환
    	        long prop_account = Long.parseLong(prop_accountStr);
    	        Timestamp prop_ed_td = Timestamp.valueOf(prop_ed_tdStr + " 00:00:00");
    	        int svc_idx = Integer.parseInt(svc_idxStr);

    	        // ProposalVO 생성
    	        ProposalVO proposal = new ProposalVO(prop_title, prop_content, prop_account, prop_ed_td, prop_tell, svc_idx, prop_id);
    	        System.out.println("[DEBUG] ProposalVO 생성: " + proposal);

    	        // DAO 호출
    	        ProposalDAO dao = new ProposalDAO();
    	        int result = dao.insertProposal(proposal);
    	        System.out.println("[DEBUG] DAO 결과: " + (result > 0 ? "성공" : "실패"));

    	        // 결과에 따라 리다이렉트
    	        if (result > 0) {
    	            response.sendRedirect("proposal_box.jsp");
    	        } else {
    	            response.sendRedirect("proposal_write.jsp?error=1");
    	        }
    	    } catch (Exception e) {
    	        System.err.println("[ERROR] ProposalWriteController - 예외 발생");
    	        e.printStackTrace();
    	        response.sendRedirect("proposal_write.jsp?error=2");
    	    }
    	}
}
