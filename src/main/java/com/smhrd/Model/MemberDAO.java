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
         return result; // Servlet으로 DB의 결과값을 전달   
      }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
   // 로그인을 위한 메소드
      public Member login(Member vo) {
         SqlSession session = factory.openSession(true);
         // sql문장을 실행
         // 회원이 입력한 id와 pw가 DB에 있는 데이터 중에서 일치하는 것이 있는지 확인!->select
         // session.selectOne("login", "memberMapper.xml과 연결고리 = id값", 같이 보내는 데이터);
         Member result = session.selectOne("login", vo);
         session.close();
         return result;
      }
      
}
