<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.StudentMemo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細情報</title>
</head>
<body>
	<% String message = (String)request.getAttribute("message"); %>
	<% if(message != null){%>
	<%= message %>
	<% } %>
	<% StudentMemo studentMemo = (StudentMemo)request.getAttribute("studentMemo"); %>

	<form action ="StudentMemoServlet" method = "post">
		<label>学生番号</label><br>
			<input type ="text" name = "studentNumber" value ="<%= studentMemo.getStudentNumber()%>"/>
		<br>
		
		<label>学生氏名</label><br>
			<input type ="text" name = "studentName" value ="<%= studentMemo.getStudentName()%>"/><br>
		
		
		<label>メモ</label><br>
			<textarea  name = "memo" ><%= studentMemo.getMemo() %></textarea><br>
		<label>更新者</label><p><%= studentMemo.getStaffName() %></p><br>
		<input type = "submit" value = "登録"/>
	</form>
	<a href="/Campus/StudentServlet">学生一覧へ戻る</a><br>
	<a href="/Campus/mypage.jsp">マイページへ戻る</a>
</body>
</html>