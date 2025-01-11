package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;  
import java.util.List;      

public class OrderDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

    // 의뢰 데이터 삽입
    public int insertRequest(OrderVO request) {
        SqlSession session = sqlSessionFactory.openSession(true); // 자동 커밋 설정
        int result = 0;
        try {
            result = session.insert("RequestMapper.insertRequest", request);
        } finally {
            session.close();
        }
        return result;
    }

    // 모든 의뢰 데이터 조회
    public List<OrderVO> selectAllRequests() {
        SqlSession session = sqlSessionFactory.openSession();
        List<OrderVO> requestList = null;
        try {
            requestList = session.selectList("RequestMapper.selectAllRequests");
        } finally {
            session.close();
        }
        return requestList;
    }
    
    // OrderDetailController DAO
    // 의뢰 상세 정보 조회 
    public OrderVO getOrderById(String orderId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.smhrd.db.OrderMapper.getOrderById", orderId);
        }
    }
    
    // OrderListController DAO
    // DB 연결 메소드 (MySQL 사용)
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/service_db";  // MySQL DB 연결 URL
        String username = "ChangHwan";  // MySQL 사용자명
        String password = "1234";  // MySQL 비밀번호
        return DriverManager.getConnection(url, username, password);
    }
    public List<OrderVO> getAllOrder() {
        List<OrderVO> orderList = new ArrayList<>();  // List 인터페이스를 사용하여 ArrayList 초기화
        String query = "SELECT writer, create_date, status, description FROM orders"; // 'orders' 테이블에서 모든 의뢰 데이터를 가져옴
        
        // 데이터베이스 연결 및 쿼리 실행
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query); 
             ResultSet rs = stmt.executeQuery()) {
            
            // 결과셋에서 데이터를 추출하여 OrderVO 객체에 담기
            while (rs.next()) {
                OrderVO order = new OrderVO();
                order.setWriter(rs.getString("writer"));
                order.setCreateDate(rs.getString("create_date"));
                order.setStatus(rs.getString("status"));
                order.setDescription(rs.getString("description"));
                orderList.add(order); // 의뢰 객체를 리스트에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 의뢰 목록 반환
        return orderList;
    }
    
    // OrderManageController DAO
    // 모든 의뢰 목록을 가져오는 메소드
    public List<OrderVO> getAllOrders() {
        String query = "SELECT * FROM orders";  // 의뢰 목록을 가져오는 SQL 쿼리
        List<OrderVO> orders = new ArrayList<>();  // 의뢰 목록을 저장할 리스트
        
        try (Connection conn = getConnection(); // 데이터베이스 연결
             PreparedStatement stmt = conn.prepareStatement(query); // 쿼리 실행을 위한 PreparedStatement
             ResultSet rs = stmt.executeQuery()) { // SQL 쿼리 실행

            // 쿼리 결과에서 데이터를 추출하여 OrderVO 객체로 변환
            while (rs.next()) {
                OrderVO order = new OrderVO();  // 새 의뢰 객체 생성
                order.setOrderId(rs.getInt("order_id"));  // 의뢰 ID 설정
                order.setTitle(rs.getString("title"));  // 의뢰 제목 설정
                order.setDeadline(rs.getDate("deadline"));  // 의뢰 마감일 설정
                order.setStatus(rs.getString("status"));  // 의뢰 상태 설정
                orders.add(order);  // 의뢰 객체를 목록에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 예외 발생 시 에러 메시지 출력
        }

        return orders;  // 의뢰 목록 반환
    }
}

