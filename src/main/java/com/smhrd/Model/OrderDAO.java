package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;
import java.util.List;

public class OrderDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

    // 의뢰 데이터 삽입 메서드
    public int insertOrder(OrderVO order) {
        System.out.println("OrderDAO: 데이터 삽입 시작"); // 디버깅
        int result = 0;
        try (SqlSession session = sqlSessionFactory.openSession(true)) { // 자동 커밋 설정
            result = session.insert("com.smhrd.db.OrderMapper.insertOrder", order);

            // 디버깅: 삽입 성공 여부 확인
            if (result > 0) {
                System.out.println("데이터 삽입 성공: " + order);
            } else {
                System.err.println("데이터 삽입 실패");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 디버깅: 예외 출력
        }
        return result;
    }

    // 작성자 ID 존재 여부 확인 메서드
    public boolean isUserExists(String userId) {
        boolean exists = false;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            exists = session.selectOne("com.smhrd.db.OrderMapper.isUserExists", userId) != null;
        } catch (Exception e) {
            e.printStackTrace(); // 디버깅: 예외 출력
        }
        return exists;
    }
}
