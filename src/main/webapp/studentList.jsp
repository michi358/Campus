<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生一覧</title>
</head>
<body>
	<% String message = (String)request.getAttribute("message"); %>
	<% if(message != null){%>
	<%= message %>
	<% } %>
	<h1>学生一覧</h1>
	
	<% List<Student> studentList = 
		(List<Student>)request.getAttribute("studentList"); %>
	
	<a href="StudentMemoServlet">新規登録</a>
	
	<table>
		<tr>
			<th>学生番号</th>
			<th>氏名</th>
			<th>詳細</th>
		</tr>
		<tr>
			<% for(int i = 0; i < studentList.size(); i++){ %>
			<td><%=studentList.get(i).getStudentNumber() %></td>
			<td><%=studentList.get(i).getStudentName() %></td>
			<td>
				<form action ="StudentMemoServlet" method = "post">
				<a href="StudentMemoServlet?student_number=<%= studentList.get(i).getStudentNumber() %>">詳細</a>
				</form>
			</td>
		</tr>
		<% } %>
	</table>
	<a href="/Campus/mypage.jsp">マイページへ戻る</a>
</body>
</html>