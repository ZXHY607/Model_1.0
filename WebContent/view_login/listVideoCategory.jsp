<%@page import="java.util.ArrayList"%>
<%@page import="net.gslab.entity.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../view_all/css/login.css">
<style>
	a
	{
	display:block;
	}
</style>
<title>Insert title here</title>
</head>
<body class="back">
<div class="TITLE0"><!--标题-->
      <hr class="line" />
      <img src="../view_all/images/logo.png" class="logo">
      <img src="../view_all/images/title-DLUT.png" class="DLUT">
      <div style="clear:both;"></div>
    </div>

    <div class="line_div">
      <hr class="line1" />
    </div>
	<h1 class="t1">课件目录</h1>
	<%
	List<String> fileList =(ArrayList<String>)request.getAttribute("fileList");
	List<String> pathList =(ArrayList<String>)request.getAttribute("pathList");
	for(int i=0;i<fileList.size();i++)
	{%>
	<a href="<%=pathList.get(i)%>">
	<%=fileList.get(i) %>
	</a>
	<%} %>
	<div class="foot">
	  <a class="forget_pn1" href="/Model/view_all/a_sy.html">返回主页>></a>
	</div>
</body>
</html>