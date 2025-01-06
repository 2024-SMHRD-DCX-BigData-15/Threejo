package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;

public class MemberDAO {
	// 1. DB연결
	   SqlSessionFactory factory = SqlSessionManager.getSqlSession();

	   // 회원가입을 위한 메소드
	   public int join(Member vo) {
	      SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋이 가능
	      // 2. sql 문장을 실행
	      int result = session.insert("join", vo);
	      // 3. 세션 반납
	      session.close();
	      return result; // Servlet으로 DB의 결과값을 전달!
	   }
}
