<%@page import="java.util.List"%>
<%@page import="com.smhrd.Model.order_list" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>의뢰목록</title>
</head>
<body>
    <h1>의뢰목록</h1>
    <ul>
        <%
            List<order_list> order_list = (List<com.smhrd.Model.order_list>) request.getAttribute("order_list");
            if (order_list != null && !order_list.isEmpty()) {
                for (order_list order : order_list) {
        %>
                    <li>
                        <strong><%= order.getNot_idx()%></strong> - <%= order.getNot_idx() %> 
                        (<%=order.getNot_idx() %>)
                    </li>
        <%
                }
            } else {
        %>
                <li>등록된 의뢰가 없습니다.</li>
        <%
            }
        %>
    </ul>
    <a href="request.jsp?action=new">새 의뢰 등록</a>
</body>
</html>