<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>

	<% String message = (String)request.getAttribute("message"); %>
	<% if(message != null){%>
	<%= message %>
	<% } %>
	
	<h1>ログイン画面</h1>
	<p>IDとパスワードを入力してください</p>
	<form action ="LoginServlet" method = "post">
		<label>ID：<input type ="text" name = "staffId"/></label><br>
		<label>Password：<input type ="password" name = "staffPassword"/></label><br>
		<input type = "submit" value = "ログイン"/>
	</form>
	<p>アカウント登録はこちら</p>
	<input type="button" value="新規登録" onclick="location.href='RegisterServlet'">
</body>
</html>