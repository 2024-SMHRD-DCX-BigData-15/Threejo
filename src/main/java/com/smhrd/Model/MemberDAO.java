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
      
      
      // id중복검사를 위한 메소드
      public boolean checkId(String id) {
  		// 0. DB에 접글할 수 있는 session 발급
  		SqlSession session = factory.openSession(true);
  		// 1. session을 이용해서 쿼리문 접근
  		Member result = session.selectOne("check",id);
  		// 2. 쿼리문(MemberMapper.xml)작성
  		// 3. 그에 대한 결과값을 통해서 결과 값 처리
  		session.close();
  		if(result==null) {
  			//result 안에 값이 없다면 -> 중복된 값이 없다! 
  			// -> 쿼리문을 실행했을 때, 일치한 값이 없다.
  			return false;
  		}else {
  			// result 안에 값이 있다면 -> 중복된 값이 있다!
  			return true;

  		}
  	}
}
