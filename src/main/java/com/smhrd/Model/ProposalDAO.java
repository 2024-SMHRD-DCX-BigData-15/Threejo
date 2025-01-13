package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;

public class ProposalDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

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

    
}
