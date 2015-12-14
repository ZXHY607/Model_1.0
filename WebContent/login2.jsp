<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/Model/view_all/css/login.css">
<title>登陆页面</title>
</head>
<body class="back">
 <div class="TITLE0"><!--标题-->
      <hr class="line" />
     <%String path=application.getRealPath("logo.png"); %> 
       
      <img src="/Model/view_all/images/logo.png" class="logo">
      <img src="/Model/view_all/images/title-DLUT.png" class="DLUT">
      <div style="clear:both;"></div>
    </div>

    <div class="line_div">
      <hr class="line1" />
    </div>
<form action="/Model/login2/doLogin2" method="POST">
     <input style="display:none" type="text"/>
     <input style="display:none" type="password"/>
	<input class="in" onFocus="this.value=''" autocomplete="off" value="请输入用户名" type="text" name="loadname" autocomplete="off" /><br/>
	<input class="in" onFocus="this.value=''" value="请输入用密码"   type="password" name="password"/><br />
	<div class="selection">
	<span class="select">学生：<input type="radio" checked="checked" name="logintype" value="student" /></span>
         <span class="select">老师：<input type="radio" name="logintype" value="teacher" /></span>  
          <span class="select"> 管理员：<input type="radio" name="logintype" value="admin" /> </span> <br />
	
	</div>
	<input class="title" type="submit" value="登录"/>
	</form>
	<div class="foot">
	  <a class="forget_pn" href="/Model/view_all/emailResetPassword.jsp">忘记密码?</a>
	  <a class="forget_pn" href="/Model/view_all/a_sy.html">返回主页>></a>
	 <br/><br/> <div class="error" style="color:red;text-align:center;"><%=(request.getAttribute("ERROR_MSG_KEY")!=null)?request.getAttribute("ERROR_MSG_KEY"):"" %></div>
	</div>
	
	
</body>
</html>