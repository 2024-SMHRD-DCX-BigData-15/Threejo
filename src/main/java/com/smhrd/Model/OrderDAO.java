package com.smhrd.Model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

import java.util.List;

public class OrderDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

    // 1. 의뢰 데이터 삽입
    public int insertRequest(Order request) {
        SqlSession session = sqlSessionFactory.openSession(true); // 자동 커밋 설정
        int result = 0;
        try {
            result = session.insert("RequestMapper.insertRequest", request);
        } finally {
            session.close();
        }
        return result;
    }

    // 2. 모든 의뢰 데이터 조회
    public List<Order> selectAllRequests() {
        SqlSession session = sqlSessionFactory.openSession();
        List<Order> requestList = null;
        try {
            requestList = session.selectList("RequestMapper.selectAllRequests");
        } finally {
            session.close();
        }
        return requestList;
    }
}
