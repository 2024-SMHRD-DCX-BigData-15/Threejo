package com.smhrd.Model;
//1
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class MessageDAO {
	
	 private SqlSessionFactory sqlSessionFactory;

	    public MessageDAO() {
	        // MyBatis 설정을 통해 SqlSessionFactory 객체 가져오기
	        sqlSessionFactory = SqlSessionManager.getSqlSession();
	    }

	    // 메시지를 조회하는 메소드
	    public List<MessageVO> getMessages(String user_id) {
	        try (SqlSession session = sqlSessionFactory.openSession()) {
	            // 매퍼에 정의된 쿼리 ID 호출
	            return session.selectList("com.smhrd.db.MessageMapper.getMessages", user_id);
	        }
	    }

}
