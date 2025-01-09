package com.smhrd.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.smhrd.db.SqlSessionManager;

public class BoardDAO {

	SqlSessionFactory factory = SqlSessionManager.getSqlSession();
	
	public List<MyBoard> select() {
		SqlSession sqlSession = SqlSessionFactory.openSession();
		List<MyBoard> res = sqlSession.selectList("BoardMapper.select");
		sqlSession.close();
		return res;
	}
	
	public int writeBoard(MyBoard m) {
		SqlSession sqlSession = SqlSessionFactory.openSession(true);
		int res = sqlSession.insert("BoardMapper.write", m);
		sqlSession.close();
		return res;
	}
	
	public MyBoard getBoard(int idx) {
		SqlSession sqlSession = SqlSessionFactory.openSession();
		MyBoard res = sqlSession.selectOne("BoardMapper.getBoard", idx);
		sqlSession.close();
		return res;
	}
	
	public int deleteBoard(int idx) {
		SqlSession sqlSession = SqlSessionFactory.openSession(true);
		int res = sqlSession.delete("BoardMapper.delete", idx);
		sqlSession.close();
		return res;
	}
	
	public String boardCnt() {
		SqlSession sqlSession = SqlSessionFactory.openSession(true);
		String res = sqlSession.selectOne("BoardMapper.board_cnt");
		sqlSession.close();
		return res;
	}
}
