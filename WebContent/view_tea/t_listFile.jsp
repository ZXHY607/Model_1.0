<%@page import="net.gslab.tools.FileUtil"%>
<%@page import="java.io.File"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	.rootName
	{
		font-size:15px;
	}
	.rootInput
	{
		border:none;
	}

	.cBtn
	{
	 margin-left:30px;
	}
	.change
	{
		display:none;
	}
</style>

</head>
<body>
	
	
	<br><br>
	<div ><a href="/Model/file/tListCategory">返回课件目录 </a></div>
	<div ><a href="${parent }">返回上一级</a></div>
	<form action="/Model/file/delete" method="post" onsubmit="return confirm('你确定要删除选中的文件吗')">
	<input type="hidden" name="filePath" value="${filePath }">
	<div class="ul">
	<%File[] files=(File[])request.getAttribute("files");
	for(File file:files){
	%>

	<div>
	<input type="checkbox" name="files" value="<%=file.getPath()%>">
	<%if(file.isDirectory()){ %>
	<!-- <img src="../images/PowerPoint.png" class="icon"></img> -->
	<span>(文件夹)</span>
	<a class="dir" href="../file/tListFile?filePath=<%=file.getPath()%>">
	<%=file.getName() %>
	</a>
	<%} else if(file.isFile()&&!FileUtil.isVideo(file)){%>
	
	<span>(文件)</span>
	<!-- <img src="../images/file.png" class="icon"></img> -->
	<a class="file"  href="../file/download?filePath=<%=file.getPath() %>" ><%=file.getName() %></a>
	<% }else{%>
	<span>(视频)</span>
	<!-- <img src="../images/file.png" class="icon"></img> -->
	<a class="video"  href="/Model/view_login/playVideo.jsp?uri=<%=FileUtil.changeToServerURL(file.getPath(), request.getServletContext().getRealPath("/"))%>" ><%=file.getName() %></a>
	<%} %>
	</div>
	<%} %>
	
	</div>
	<br><br><br><br>
	<button >删除选中的文件</button>
	<br><br><br><br>
	<p>上传文件</p>
	</form>
	<form action="/Model/file/upload" method="post" enctype="multipart/form-data">
	<input name="filePath" value="${filePath}" type="hidden">
	<input type="file" name="file">
	<button>上传</button>
	</form>
	<br><br>
	<div class="description">
	视频播放器只支持以下几个格式的媒体文件mp3, mp4 (AAC/H.264), ogg (Vorbis/Theora), webm (Vorbis/VP8), wav
	 ，rtmp, flv
	</div>
	<form action='/Model/file/newFolder'>
		<input name="filePath" value="${filePath}" type="hidden">
		<input name="fileName" placeholder="请输入要新建文件夹名称">
		<button>新建文件夹</button>
	</form>
</body>
</html>