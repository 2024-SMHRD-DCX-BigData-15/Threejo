package com.smhrd.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
      
      public static boolean updateId(Member member) {
          String sql = "UPDATE members SET user_pw = ?, user_email = ?, user_tell = ? WHERE user_id = ?";
          boolean success = false;
          
          // DB 연결과 쿼리 실행 부분을 생략하고, 이미 DB 연결이 되어 있다고 가정
          try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/service_db", "ChangHwan", "1234");
               PreparedStatement ps = conn.prepareStatement(sql)) {
              
              ps.setString(1, member.getPw());   // 수정된 비밀번호
              ps.setString(2, member.getEmail()); // 수정된 이메일
              ps.setString(3, member.getTell());  // 수정된 전화번호
              ps.setString(4, member.getId());    // 수정할 회원 아이디
              
              int rows = ps.executeUpdate();  // 쿼리 실행 후, 수정된 행 수 확인
              if (rows > 0) {
                  success = true;  // 수정이 성공했으면 true 반환
              }
          } catch (SQLException e) {
              e.printStackTrace();  // 예외 발생 시 오류 메시지 출력
          }
          return success;  // 수정 성공 여부 반환
      }

}
