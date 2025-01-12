package com.smhrd.Model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class CalendarDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

    // 일정 추가 메소드
    public boolean addEvent(CalendarVO vo) {
        SqlSession session = sqlSessionFactory.openSession(true); // Auto Commit
        try {
            int result = session.insert("addEvent", vo);
            System.out.println("[DEBUG] 일정 추가 결과 (영향받은 행 수): " + result);
            return result > 0;
        } finally {
            session.close();
        }
    }

    // 일정 조회 메소드
    public List<CalendarVO> getUserEvents(String user_id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            List<CalendarVO> events = session.selectList("getUserEvents", user_id);
            System.out.println("[DEBUG] 조회된 일정 수: " + (events != null ? events.size() : 0));
            return events;
        } finally {
            session.close();
        }
    }
}
