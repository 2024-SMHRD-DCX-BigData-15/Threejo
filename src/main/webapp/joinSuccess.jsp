<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align: center;">
 	<div id="wrapper">
		<!-- Menu -->
		<nav id="Update"> <font size="8">회원가입성공</font>
		<br>
		정보를 확인하세요<br>
		<br>
		<%
			String id = request.getParameter("id");
		
		%>
		
		<table>
			<tr>
				<td>ID</td>
				<td><%=id %></td>	
			</tr>
		</table>
		<a href="index.jsp"><input type="button" value="메인페이지로"></a> </nav>
	</div>
	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>


</body>
</html>