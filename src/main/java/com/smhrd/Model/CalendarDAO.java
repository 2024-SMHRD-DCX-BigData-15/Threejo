package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

import java.util.List;

public class CalendarDAO {
    private SqlSessionFactory factory = SqlSessionManager.getSqlSession();

    // 특정 사용자의 모든 일정 가져오기
    public List<CalendarVO> getUserEvents(String user_id) {
        SqlSession session = factory.openSession();
        List<CalendarVO> events = null;

        try {
            System.out.println("[DEBUG] 일정 조회 중 user_id: " + user_id);
            events = session.selectList("getUserEvents", user_id);
            System.out.println("[DEBUG] 조회된 일정 수: " + (events != null ? events.size() : 0));
        } catch (Exception e) {
            System.out.println("[ERROR] 일정 조회 중 오류 발생");
            e.printStackTrace();
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

    // 일정 추가
    public boolean addEvent(CalendarVO vo) {
        SqlSession session = factory.openSession(true);
        boolean result = false;

        try {
            System.out.println("[DEBUG] 추가할 일정 데이터: " + vo);
            int count = session.insert("addEvent", vo);
            System.out.println("[DEBUG] 일정 추가 결과 (영향받은 행 수): " + count);
            result = count > 0;
        } catch (Exception e) {
            System.out.println("[ERROR] 일정 추가 중 오류 발생");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    // 일정 삭제
    public boolean deleteEvent(int sche_idx) {
        SqlSession session = factory.openSession();
        int result = 0;

        try {
            System.out.println("[DEBUG] 실행할 SQL: DELETE FROM t_schedule WHERE sche_idx = " + sche_idx);
            result = session.delete("com.smhrd.db.CalenderMapper.deleteEvent", sche_idx);
            System.out.println("[DEBUG] 삭제 결과 (영향받은 행 수): " + result);
            session.commit();
        } catch (Exception e) {
            System.out.println("[ERROR] 일정 삭제 중 오류 발생");
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

        return result > 0;
    }
}