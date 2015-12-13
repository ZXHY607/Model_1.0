<%@page import="net.gslab.entity.Course"%>
<%@page import="net.gslab.setting.PageBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <form action="/Model/course/delete" method="post">
	<table><tr><th></th><th>课程名称</th><th>课程负责人ID</th></tr>
	<%
	List<Course> list=(List<Course>)request.getAttribute("list");
	if(list!=null)
		for(Course t:list)
		{
			%>
	
	<tr>
	<td><input name="id" value="<%=t.getFileDir()%>" type="checkbox"> </td>
	<td><a href="/Model/course/getOne?id=<%=t.getFileDir()%>&view=/view_admin/m_courseUpdate.jsp"><%= t.getName()%></a></td>
	
	<td><%=t.gettId() %></td>
	
	</tr>
	
	<%}%>
	</table>
	<button>删除选中的课程</button>
	</form>
	
</body>
</html>