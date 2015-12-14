<%@page import="java.util.List"%>
<%@page import="net.gslab.entity.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table><tr><th>课程名称</th><th>课程负责人ID</th></tr>
	<%
	List<Course> list=(List<Course>)request.getAttribute("list");
	if(list!=null)
		for(Course t:list)
		{
			%>
	
	<tr>
	<td><a href="/Model/course/getOne?id=<%=t.getFileDir()%>&view=/view_tea/manageFile.jsp"><%= t.getName()%></a></td>
	
	<td><%=t.gettId() %></td>
	
	</tr>
	
	<%}%>
	</table>
</body>
</html>