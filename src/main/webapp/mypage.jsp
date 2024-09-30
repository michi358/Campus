<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List"%>
 <%@page import="model.Staff"%>  
 <%@page import="model.Todo"%> 

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
	
	<a href="/Campus/StudentServlet">学生一覧</a><br>
	<a href="StudentMemoServlet">新規登録</a><br>
	<br>
	<strong>ToDo</strong>
	<% List<Todo>todoList =(List<Todo>)request.getAttribute("todoList"); %>
		<tr>
		<% if(todoList != null){ %>
			<%for(int i = 0; i < todoList.size(); i++){ %>
				<td><%= todoList.get(i).getTask() %></td>
				<td><%= todoList.get(i).getCreatedAt() %></td>
			<% } %>
		<% } else { %>
		<p>登録されているタスクはありません</p>
		<% } %>
		</tr>
	
	
	<br>
	<a href= "ChangePasswordServlet">パスワード変更</a><br>
	<br>
	<input type="button" value="ログアウト" onclick="location.href='LogoutServlet'"/>
</body>
</html>