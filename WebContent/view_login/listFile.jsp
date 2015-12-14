<%@page import="net.gslab.tools.FileUtil"%>
<%@page import="java.io.File"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Model/view_all/js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Model/view_all/css/login.css">
<title>课件</title>
<style>
	
	a
	{
	padding-left:10px;
	height:20px;
	/* background:url(../images/PowerPoint.png); */
	}
	.icon
	{
		width:20px;
		height:20px;
	}
	.bg
	{
		background:#ececec;
	}
</style>

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
	<div class="ul">
	<%File[] files=(File[])request.getAttribute("files");
	for(File file:files){
	%>

	<div>
	<%if(file.isDirectory()){ %>
	<!-- <img src="../images/PowerPoint.png" class="icon"></img> -->
	<span>(文件夹)</span>
	<a class="dir" href="../file/listFile?filePath=<%=file.getPath()%>">
	<%=file.getName() %>
	</a>
	<%} else if(file.isFile()&&!FileUtil.isVideo(file)){%>
	
	
	<!-- <img src="../images/file.png" class="icon"></img> -->
	<a class="courseN"  href="../file/download?filePath=<%=file.getPath() %>" ><%=file.getName() %></a>
	<% }else{%>
	<span>(视频)</span>
	<!-- <img src="../images/file.png" class="icon"></img> -->
	<a class="video"  href="/Model/view_login/playVideo.jsp?uri=<%=FileUtil.changeToServerURL(file.getPath(), request.getServletContext().getRealPath("/"))%>" ><%=file.getName() %></a>
	<%} %>
	</div>
	<%} %>
	</div>
	<div class="foot">
	  <a class="forget_pn" href="../file/listCategory">返回课件目录</a>
	  <a class="forget_pn"  href="${parent }">返回上一级</a>
	</div>
	
	<script type="text/javascript">
	</script>
</body>
</html>