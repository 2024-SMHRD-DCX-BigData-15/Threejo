<%@page import="com.smhrd.model.MyBoard"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.smhrd.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
	BoardDAO dao = new BoardDAO();
	List<MyBoard> boardList = dao.select();
	System.out.println(boardList.size());
%>
<%
	String boardCnt = dao.boardCnt();
	System.out.print(boardCnt);
%>
<div class="container">
  <h2>빅데이터반 게시판😎😎</h2>
  <div class="panel panel-default">
    <div class="panel-heading">게시판</div>
    <div class="panel-body">
    	  <table class="table table-bordered">
		    <thead>
		      <tr>
		        <th>번호</th>
		        <th>제목</th>
		        <th>작성자</th>
		        <th>작성일</th>
		      </tr>
		    </thead>
		    <tbody>
		    <%
		    for(MyBoard m : boardList) {
		    %>
		      <tr>
		        <td><%=m.getIdx() %></td>
		        <td><a href="boardcontent.jsp?idx=<%=m.getIdx()%>"><%=m.getTitle() %></a></td>
		        <td><%=m.getWriter() %></td>
		        <td><%=m.getIndate() %></td>
		      </tr>
		      <tr>
    			<td>게시글 수</td>
    			<td><%=boardCnt %></td>
    		</tr>
		      <% } %>
		      <tr>
		      	<td colspan="4">
		      		<button class="btn btn-sm btn-success" onclick="location.href='boardform.jsp'">글작성</button>
		      		<button class="btn btn-sm btn-warning" onclick="window.open('chat.jsp', '채팅방', 'width=450, height=500 top=50, left=500')">채팅하기</button>
		      	</td>
		      </tr>
		    </tbody>
		  </table>
    
    </div>
    <div class="panel-body">
    	  <table class="table table-bordered">
		    <thead>
		      <tr>
		        <th>번호</th>
		        <th>제목</th>
		        <th>작성자</th>
		        <th>작성일</th>
		      </tr>
		    </thead>
		    <tbody id="list">
				
		    </tbody>
		  </table>
    
    </div>
    <div class="panel-footer">빅데이터 분석서비스 개발자과정 (담임 : 서대희)</div>
  </div>
</div>
<script type="text/javascript">
	// 게시물 전체 정보 불러오기 기능을 만들자(비동기통신 -ajax)
	// http://localhost:8081/mavenboard/boardlist.jsp
	// http://localhost:8081/mavenboard/board
	
	//현재 html 문서가 브라우저 상에서 로드가 완료되고 나면 getList() 호출
	$(document).ready(function(){
		getList()
	})
	// 비동기 통신 시 사용하는 데이터 형식 : json {key:value, key:value} / xml
	function getList() {
		$.ajax({
			url : "board", // 요청경로
			type : "get", // 요청방식(http 요청 메서드)
			success : printList,
			error : function(){
				alert("통신실패!")
			}
		})
	}
	function printList(data) {
		var data = JSON.parse(data)
		
		var html = "" //id=list 곳에 추가가 될 html 코드
		
	    for(var board of data) {
	      html += "<tr>"
	      html += "<td>" + board.idx + "</td>"
	      html += "<td><a href='boardcontent.jsp?idx=" + board.idx + "'>" + board.title + "</a></td>"
	      html += "<td>" + board.writer + "</td>"
	      html += "<td>" + board.indate + "</td>"
	      html += "<td><button class='btn btn-sm btn-info' onclick='deleteBoard(" + board.idx + ")'>삭제</button></td>"
	      html += "</tr>"
	    }
		// html() : 특정 태그 사이에 html 코드를 바꾸고 싶을 때 사용
		// text() : text 바꾸고 싶을 때
		// append() : 특정 태그 사이에 값을 추가 (뒤쪽에 추가)
		// prepend() : 앞쪽에 추가 => 누적
		$("#list").html(html)
	}
	// http://localhost:8081/mavenboard/boardlist.jsp
	// http://localhost:8081/mavenboard/board/delete
	function deleteBoard(idx) {
		$.ajax( {
			url : "board/delete",
			data : {"idx" : idx}, // 서버로 보낼 데이터(json)
			type : "get",
			success : getList,
			error : function() {
				alert("통신실패!")
			}
		})
	}
</script>
</body>
</html>
