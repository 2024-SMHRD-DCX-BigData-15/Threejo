package com.smhrd.Model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class BoardDAO {
   // 1. DB연결
   SqlSessionFactory factory = SqlSessionManager.getSqlSession();
   
   // 2. getList메소드 생성
   public List<BoardVO> getList() {
      // 전체 게시글 조회하는 메소드
      SqlSession session = factory.openSession(true);
      
      // BoardMapper에 정의된 getList id를 가진 SQL쿼리문 실행
      List<BoardVO> result = session.selectList("getList");
      
      // 해당 id를 가진 select문을 실행한 후, 결과값을 List로 반환
      session.close();
      return result;
   }

   public int writeBoard(BoardVO uploadBoard) {
      // 게시글 작성하는 매소드
      // 1. DB에 접근할 수 있는 session받기
      SqlSession session = factory.openSession(true);
      // 2. BoardMapper에 정의된 writeBoard id를 가진 SQL쿼리문 실행
      // insert메소드를 실행하면 DB에서 insert문을 실행함으로써 영향받는 행의 개수가 반환된다.
      int result = session.insert("writeBoard", uploadBoard);
      // 3. 해당 SQL쿼리문을 실행한 후, 결과값을 반환
      
      // 4. 세션 반납
      session.close();
      return result;
   }
   
   public BoardVO getBoard(int idx) {
      // 인덱스 번호만으로 DB에 접근해서 해당 인덱스 번호의 게시글 정보를 가져오는 메소드
      // 여러개의 게시글을 가지고 올 때는 리턴값이 list<MyBoard>
      // 한개의 게시글의 상세 정보(MyBoard의 필드값, title, content..)를 가지고 올 때는 리턴값이 MyBoard
      // 1. DB에 접근할 수 있는 session받기
      SqlSession session = factory.openSession(true);
      // 2. BoardMapper에 정의된 getBoard id를 가진 SQL쿼리문 실행
      BoardVO result = session.selectOne("getBoard", idx);
      // 3. 세션반납
      session.close();
      // 4. 해당 SQL쿼리문을 실행한 후 결과값 반납
      return result;
   }
   
   public int delete(int idx) {
      SqlSession session = factory.openSession(true);
      int result = session.delete("delete", idx);
      session.close();
      return result;
   }
   
   }
