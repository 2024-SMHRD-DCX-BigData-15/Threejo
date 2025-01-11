package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;
import java.util.List;

public class OrderDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
    
    //OrderController DAO
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
    
    // OrderListController DAO
    public List<OrderVO> getAllOrders() {
        System.out.println("[OrderDAO] getAllOrders() 호출"); // 디버깅: 메서드 호출
        List<OrderVO> orderList = null; // 의뢰 데이터를 저장할 리스트 초기화
        try (SqlSession session = sqlSessionFactory.openSession()) {
            System.out.println("[OrderDAO] 의뢰 데이터 조회 시도"); // 디버깅: 데이터 조회 시작
            // MyBatis Mapper를 사용하여 t_service 테이블의 모든 데이터 조회
            orderList = session.selectList("com.smhrd.db.OrderMapper.getAllOrders");
            if (orderList != null && !orderList.isEmpty()) {
                System.out.println("[OrderDAO] 의뢰 데이터 조회 성공: " + orderList.size() + "건");
            } else {
                System.out.println("[OrderDAO] 의뢰 데이터가 없습니다.");
            }
        } catch (Exception e) {
            System.err.println("[OrderDAO] 의뢰 데이터 조회 중 예외 발생"); // 디버깅: 예외 메시지
            e.printStackTrace();
        }
        System.out.println("[OrderDAO] getAllOrders() 종료"); // 디버깅: 메서드 종료
        return orderList; // 조회한 의뢰 데이터 리스트 반환
    }
}
