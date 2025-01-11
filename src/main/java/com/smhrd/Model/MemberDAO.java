package com.smhrd.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;

public class MemberDAO {
   private static final Statement DBConnection = null;
      // 1. DB연결
      SqlSessionFactory factory = SqlSessionManager.getSqlSession();

      // 회원가입을 위한 메소드
      public int join(MemberVO vo) {
         SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋이 가능
         // 2. sql 문장을 실행
         int result = session.insert("join", vo);
         // 3. 세션 반납
         session.close();
         return result; // Servlet으로 DB의 결과값을 전달   
      }
      
      
      // 로그인을 위한 메소드
      public MemberVO login(MemberVO vo) {
    	    SqlSession session = factory.openSession(true);

    	    // 디버깅 출력
    	    System.out.println("DAO로 전달된 user_id: " + vo.getUser_id());
    	    System.out.println("DAO로 전달된 user_pw: " + vo.getUser_pw());

    	    // 쿼리 실행
    	    MemberVO result = session.selectOne("login", vo);

    	    // 쿼리 결과 확인
    	    if (result != null && "NOT_FOUND".equals(result.getUser_id())) {
    	        System.out.println("사용자를 찾을 수 없습니다.");
    	        result = null; // 더미 데이터를 null로 처리
    	    }

    	    session.close();
    	    return result;
    	}


      
      
      // id중복검사를 위한 메소드
      public boolean checkId(String user_id) {
  		// 0. DB에 접글할 수 있는 session 발급
  		SqlSession session = factory.openSession(true);
  		// 1. session을 이용해서 쿼리문 접근
  		MemberVO result = session.selectOne("check",user_id);
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
      
      public int update(MemberDTO dto) {
    	    SqlSession sqlSession = factory.openSession(true);
    	    int cnt = sqlSession.update("update", dto);
    	    System.out.println("성공여부:"+ cnt);
    	    
    	    sqlSession.close();
    	    return cnt;
    	}

      // 회원 삭제 메소드
      public boolean deleteMember(MemberVO member) {
          // 1. MyBatis의 SqlSession 객체 열기 (auto-commit 활성화)
          SqlSession session = factory.openSession(true);
          
          // 디버깅: VO 객체에 담긴 값 출력
          System.out.println("삭제 요청된 user_id: " + member.getUser_id());
          System.out.println("삭제 요청된 user_pw: " + member.getUser_pw());

          // 2. delete 쿼리 실행 및 결과 반환
          int result = session.delete("deleteMember", member);
          
          System.out.println("MyBatis 삭제 결과 (영향받은 행 수): " + result);

          // 3. 세션 닫기
          session.close();

          // 4. 삭제 성공 여부 반환
          return result > 0;
      }
  }