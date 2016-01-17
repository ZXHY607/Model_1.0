<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="view_all/css/a_sysjs.css">
<title>登陆页面</title>
</head>
<body class="back">
<form action="/Model/login/doLogin">

	用户名：<input class="in" type="text" name="loadname"/><br/>
	密    码：<input class="in" type="password" name="password"/>
	<input class="title" type="submit" value="登录"/>
	</form>
</body>
</html>