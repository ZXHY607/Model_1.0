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
    <%Course c=(Course)request.getAttribute("one");{%>
	<form action="/Model/course/update" method="post">
		<input type="hidden" name="fileDir" value="<%=c.getFileDir() %>">
		课程名称：<input name="name" value="<%=c.getName()%>"><br><br>
		
		负责人姓名：<input name="tId" value="<%=c.gettId()%>"><br><br>
	
		课程说明：<br>
		<textarea rows="5" cols="30" name="intro"><%=c.getIntro() %></textarea><br><br>
		<button>确定修改</button>
	</form>
	<%} %>
</body>
</html>