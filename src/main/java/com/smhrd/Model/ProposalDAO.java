package com.smhrd.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;

public class ProposalDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

    // ProposalWriteController
    // 제안 데이터 삽입 메서드
    public int insertProposal(ProposalVO proposal) {
        System.out.println("[DEBUG] insertProposal() 호출 - proposal: " + proposal);
        int result = 0;
        try (SqlSession session = sqlSessionFactory.openSession(true)) { // 자동 커밋
            result = session.insert("com.smhrd.db.ProposalMapper.insertProposal", proposal);
            System.out.println("[DEBUG] SQL 실행 결과: " + result);
        } catch (Exception e) {
            System.err.println("[ERROR] insertProposal() 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
    
    // ProposalBoxController
    // 보낸 제안서 조회
    public List<ProposalVO> getSentProposals(String user_id) {
        List<ProposalVO> proposals = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            proposals = session.selectList("com.smhrd.db.ProposalMapper.getSentProposals", user_id);
            System.out.println("[DEBUG] 보낸 제안서 조회 결과: " + proposals);
        } catch (Exception e) {
            System.err.println("[ERROR] getSentProposals() 중 예외 발생");
            e.printStackTrace();
        }
        return proposals;
    }


    // 받은 제안서 조회
    public List<ProposalVO> getReceivedProposals(int svc_idx, String user_id) {
        List<ProposalVO> proposals = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("svc_idx", svc_idx);
            params.put("user_id", user_id);
            proposals = session.selectList("com.smhrd.db.ProposalMapper.getReceivedProposals", params);
            System.out.println("[DEBUG] 받은 제안서 조회 결과: " + proposals.size() + "건");
        } catch (Exception e) {
            System.err.println("[ERROR] getReceivedProposals() 중 예외 발생");
            e.printStackTrace();
        }
        return proposals;
    }
    
    // ProposalViewController
    public ProposalVO getProposalById(int prop_idx) {
        ProposalVO proposal = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            System.out.println("[DEBUG] getProposalById 호출 - prop_idx: " + prop_idx);
            proposal = session.selectOne("com.smhrd.db.ProposalMapper.getProposalById", prop_idx);
            System.out.println("[DEBUG] 조회된 ProposalVO: " + proposal);
        } catch (Exception e) {
            System.out.println("[ERROR] getProposalById 예외 발생: " + e.getMessage());
        }
        return proposal;
    }
    
}
