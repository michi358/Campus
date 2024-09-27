<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>新規登録</h1>
	<p>ID・名前・パスワードを入力してください</p>
	<form action ="RegisterServlet" method = "post">
		<label>ID：<input type ="number" name = "id"/></label><br>
		<label>名前：<input type ="text" name = "name"/></label><br>
		<label>Password：<input type ="password" name = "password"/></label><br>
		<input type = "submit" value = "新規登録"/>
	</form>
</body>
</html>