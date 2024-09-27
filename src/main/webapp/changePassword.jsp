<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
</head>
<body>
	<h1>パスワード変更</h1>
	
	<% String message = (String)request.getAttribute("message"); %>
	<% if(message != null){%>
	<font color = "red"><%= message %></font>
	<% } %>
	
	<form action ="ChangePasswordServlet" method = "post">
		<label>現在のパスワード：<input type ="password" name = "oldpassword"/></label><br>
		<label>新しいパスワード：<input type ="password" name = "newpassword"/></label><br>
		<input type = "submit" value = "変更する"/>
	</form>

</body>
</html>