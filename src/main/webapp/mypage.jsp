<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="model.Staff"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<body>
	<% String message = (String)request.getAttribute("message"); %>
	<% if(message != null){%>
	<%= message %>
	<% } %>
	
	<h1>マイページ</h1>
	
	<% Staff staff = (Staff)session.getAttribute("staff"); %>
	<p>ログインスタッフID:<%= staff.getStaffId() %></p>
	<p>ログインスタッフ名: <%= staff.getStaffName() %></p><br>
	
	<h3><a href="/Campus/StudentServlet">学生一覧</a></h3>
	
	<input type="button" value="パスワード変更" onclick="location.href='ChangePasswordServlet'"/>
	<input type="button" value="ログアウト" onclick="location.href='LogoutServlet'"/>
</body>
</html>