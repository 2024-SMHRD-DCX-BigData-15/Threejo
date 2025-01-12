package com.smhrd.Model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;

public class CalendarDAO {
   SqlSessionFactory factory = SqlSessionManager.getSqlSession();
    
    // 일정 추가 메소드
   public boolean addEvent(CalendarVO vo) {
	    SqlSession session = factory.openSession(true); // Auto Commit
	    try {
	    	// 디버깅 코드 추가
	        System.out.println("[DEBUG] 추가할 일정 데이터: " + vo);
	        int result = session.insert("addEvent", vo);
	        System.out.println("[DEBUG] 일정 추가 결과 (영향받은 행 수): " + result);
	        return result > 0;
	    } catch (Exception e) {
	        System.out.println("[ERROR] 일정 추가 중 오류 발생");
	        e.printStackTrace();
	        return false;
	    } finally {
	        session.close();
	    }
	}


    // 일정 조회 메서드
    public List<CalendarVO> getUserEvents(String user_id) {
        SqlSession session = factory.openSession(true);
        List<CalendarVO> events = null;

        try {
            events = session.selectList("getUserEvents", user_id);
            System.out.println("[DEBUG] 조회된 일정 수: " + (events != null ? events.size() : 0));
        } finally {
            session.close();
        }

        return events;
    }

    // 일정 수정 메서드
    public int updateEvent(CalendarVO vo) {
        SqlSession session = factory.openSession(true); // Auto-commit 활성화
        int result = 0;

        try {
            // MyBatis 쿼리 실행
            result = session.update("updateEvent", vo);
            System.out.println("[DEBUG] 일정 수정 결과 (영향받은 행 수): " + result);
        } catch (Exception e) {
            // 오류 발생 시 로그 출력
            System.out.println("[ERROR] 일정 수정 중 오류 발생");
            e.printStackTrace();
        } finally {
            session.close(); // 세션 닫기
        }

        return result;
    }


}
