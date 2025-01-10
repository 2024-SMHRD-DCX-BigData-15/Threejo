package com.smhrd.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class AskDAO {

	    // 1:1 문의사항을 DB에 저장하는 메서드
	    public void insertInquiry(AskDAO inquiry) throws SQLException {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	        	// 1. DB연결
	        	SqlSessionFactory factory = SqlSessionManager.getSqlSession();
	        	   
	            // SQL 쿼리 작성 (1:1 문의사항을 DB에 저장하는 INSERT 문)
	            String sql = "INSERT INTO inquiries (ask_title, ask_content, ask_id) VALUES (?, ?, ?)";
	            pstmt = conn.prepareStatement(sql);

	            // 쿼리 파라미터 설정
	            pstmt.setString(1, inquiry.getask_title());	
	            pstmt.setString(2, inquiry.getask_Content());
	            pstmt.setString(3, inquiry.getask_id());

	            // SQL 실행
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw e;  // SQLException을 호출한 곳으로 던짐
	        } finally {
	            // 자원 반납
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        }
	    }

		private String getask_title() {
			// TODO Auto-generated method stub
			return null;
		}

		private String getask_Content() {
			// TODO Auto-generated method stub
			return null;
		}

		private String getask_id() {
			// TODO Auto-generated method stub
			return null;
		}
}
