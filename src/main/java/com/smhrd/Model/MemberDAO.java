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
      
      public static boolean updateId(MemberVO member) {
    	    String sql = "UPDATE t_user SET user_email = ?, user_tell = ? WHERE user_id = ?";
    	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/service_db", "Changwan", "1234");
    	         PreparedStatement ps = conn.prepareStatement(sql)) {
    	        ps.setString(1, member.getUser_email());
    	        ps.setString(2, member.getUser_tell());
    	        ps.setString(3, member.getUser_id());
    	        return ps.executeUpdate() > 0;
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return false;
    	}


  
	public static boolean deleteMember(String user_id, String user_pw) {
		// 사용자가 입력한 비밀번호와 일치하는지 확인하는 SQL 쿼리
        String sql = "SELECT * FROM t_user WHERE user_id = ? AND user_pw = ?";
        
        try (Connection conn = DBConnection.getConnection(); // DB 연결
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            // 사용자 비밀번호를 쿼리로 전달
            ps.setString(2, user_pw);
            // 실행 결과 조회
            ResultSet rs = ps.executeQuery();
            // 비밀번호가 일치하면 삭제 진행
            if (rs.next()) {
                // 비밀번호가 맞다면, 사용자를 삭제하는 SQL 쿼리
                String deleteSql = "DELETE FROM t_user WHERE user_id = ?";
                
                try (PreparedStatement deletePs = conn.prepareStatement(deleteSql)) {
                    deletePs.setString(1, user_id);  // 삭제할 회원의 user_id 설정
                    int rows = deletePs.executeUpdate(); // 사용자 삭제 실행
                    return rows > 0;  // 성공적으로 삭제되었으면 true 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
  }