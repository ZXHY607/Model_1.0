<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>邮箱重置密码  </title>
</head>
<body class="back">

    <%-- ERROR_MSG_KEY:<%=request.getParameter("ERROR_MSG_KEY") %> --%>
   <div class="TITLE0"><!--标题-->
      <hr class="line" />
      <img src="images/logo.png" class="logo">
      <img src="images/title-DLUT.png" class="DLUT">
      <div style="clear:both;"></div>
    </div>

    <div class="line_div">
      <hr class="line1" />
    </div>
    
    <h1 class="t1">重置密码</h1>
   
   <form action="../email/resetPasseord_sent" class="email" method="POST">
   <div class="selection1">
             <span class="t2">  账户类型</span><br/><br/>
      <span class="select">学生：<input type="radio" checked="checked" name="logintype" value="student" /></span>
      <span class="select">老师：<input type="radio" name="logintype" value="teacher" /></span>  
      <span class="select"> 管理员：<input type="radio" name="logintype" value="admin" /> </span>
   
   </div>
    <br />
      <input class="in" type="text" onFocus="this.value=''" value="请输入账户ID" name="loadname"/><br/>    
      <input class="in" type="text" onFocus="this.value=''" value="请输入绑定的邮箱" name="email"/><br/>        
      <input class="title" type="submit" name="submit" value="发送重置密码的邮件 " />   
 
   </form>
   <div class="foot">
	  <a class="forget_pn1" href="/Model/view_all/a_sy.html">返回主页>></a>
	</div>
</body>
</html>