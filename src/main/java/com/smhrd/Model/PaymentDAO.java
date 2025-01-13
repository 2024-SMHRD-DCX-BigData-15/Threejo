package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class PaymentDAO {

    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

    public int insertPayment(PaymentVO payment) {
        int pay_idx = -1; // 기본값 설정
        try (SqlSession session = sqlSessionFactory.openSession()) {
            System.out.println("[DEBUG] insertPayment 호출");
            int result = session.insert("com.smhrd.db.PaymentMapper.insertPayment", payment);
            session.commit();
            if (result > 0) {
                pay_idx = payment.getPay_idx(); // 자동 생성된 키 값 가져오기
                System.out.println("[DEBUG] 결제 데이터 삽입 성공, pay_idx: " + pay_idx);
            } else {
                System.out.println("[ERROR] 결제 데이터 삽입 실패");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] insertPayment 예외 발생: " + e.getMessage());
        }
        return pay_idx;
    }


    public PaymentVO getPaymentById(int pay_idx) {
        PaymentVO payment = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            System.out.println("[DEBUG] getPaymentById 호출 - pay_idx: " + pay_idx);
            payment = session.selectOne("com.smhrd.db.PaymentMapper.getPaymentById", pay_idx);
            System.out.println("[DEBUG] 조회된 PaymentVO: " + payment);
        } catch (Exception e) {
            System.out.println("[ERROR] getPaymentById 예외 발생: " + e.getMessage());
        }
        return payment;
    }
    
    // 사용자 이메일 조회
    public String getUserEmailById(String user_id) {
        String user_email = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            user_email = session.selectOne("com.smhrd.db.PaymentMapper.getUserEmailById", user_id);
            System.out.println("[DEBUG] 조회된 user_email: " + user_email);
        } catch (Exception e) {
            System.out.println("[ERROR] getUserEmailById 예외 발생: " + e.getMessage());
        }
        return user_email;
    }

    // 사용자 전화번호 조회
    public String getUserTellById(String user_id) {
        String user_tell = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            user_tell = session.selectOne("com.smhrd.db.PaymentMapper.getUserTellById", user_id);
            System.out.println("[DEBUG] 조회된 user_tell: " + user_tell);
        } catch (Exception e) {
            System.out.println("[ERROR] getUserTellById 예외 발생: " + e.getMessage());
        }
        return user_tell;
    }
}
