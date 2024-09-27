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
	<% StudentMemo studentMemo = (StudentMemo)request.getAttribute("studetMemo"); %>

	<form action ="StudentMemoServlet" method = "post">
		<label>学生番号</label><br>
			<input type ="text" name = "studentId" value ="<%= studenMemo.getStudentNumber()%>"/>
		<br>
		
		<label>学生氏名
			<input type ="text" name = "studentNumber" value ="<%= studenMemo.getStudentName()%>"/>
		</label><br>
		
		<label>メモ
			<textarea  name = "memo"<%= studenMemo.getStudentNumber()%></textarea>
		</label><br>
		<label>更新者</label><p><%= studentMemo.getStaffName %></p>		
		<input type = "submit" value = "登録"/>
	</form>

</body>
</html>