<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member" import="net.gslab.entity.Admin" 
    import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="view_all/css/login.css">
<title>注销结果页面</title>
</head>
<body class="back">
       
         <h1 class="t1">处理结果</h1>    <h2 class="t2"><%=request.getParameter("ERROR_MSG_KEY")%></h2>
<div class="foot">
	  <a class="forget_pn" href="/Model/view_all/a_sy.html">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返回主页>></a>
	</div>
 
</body>
</html>