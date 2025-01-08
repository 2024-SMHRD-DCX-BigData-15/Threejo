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
      

      
   // DB 연결을 위한 메소드 (MySQL 데이터베이스 연결)
      private static Connection getConnection() throws SQLException {
          try {
              // MySQL JDBC 드라이버 로딩
              Class.forName("com.mysql.cj.jdbc.Driver");
              // DB 연결 설정 (호스트, 데이터베이스, 사용자명, 비밀번호)
              return DriverManager.getConnection("jdbc:mysql://localhost:3306/service_db", "ChangHwan", "1234");
          } catch (ClassNotFoundException | SQLException e) {
              // 예외 발생 시 오류 메시지 출력
              e.printStackTrace();
              throw new SQLException("DB 연결 실패");
          }
      }
      // 사용자 정보를 아이디를 기준으로 가져오는 메소드
      public static Member getMemberByUsername(String username) {
          Member member = null;
          String sql = "SELECT * FROM members WHERE username = ?";
          
          // DB 연결 및 쿼리 실행
          try (Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(sql)) {
              ps.setString(1, username);  // 아이디를 쿼리에 설정
              ResultSet rs = ps.executeQuery();
              
              // 결과가 있으면 Member 객체로 변환
              if (rs.next()) {
                  member = new Member(
                      rs.getString("id"),
                      rs.getString("pw"),
                      rs.getString("email"),
                      rs.getString("tell")
                  );
              }
          } catch (SQLException e) {
              // 예외 발생 시 오류 메시지 출력
              e.printStackTrace();
          }
          // 조회된 회원 정보 반환
          return member;
      }
      // 사용자 정보를 수정하는 메소드
      public static boolean updateMember(Member member) {
          String sql = "UPDATE members SET password = ?, email = ?, phone = ? WHERE username = ?";
          boolean success = false;
          
          // DB 연결 및 쿼리 실행
          try (Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(sql)) {
              ps.setString(1, member.getPw());  // 수정된 비밀번호 설정
              ps.setString(2, member.getEmail());     // 수정된 이메일 설정
              ps.setString(3, member.getTell());     // 수정된 전화번호 설정
              ps.setString(4, member.getId());  // 수정할 아이디 설정
              
              // 쿼리 실행 후 수정된 행의 수 확인
              int rows = ps.executeUpdate();
              if (rows > 0) {
                  success = true;  // 성공적으로 수정된 경우
              }
          } catch (SQLException e) {
              // 예외 발생 시 오류 메시지 출력
              e.printStackTrace();
          }
          // 수정 성공 여부 반환
          return success;
      }
  }
