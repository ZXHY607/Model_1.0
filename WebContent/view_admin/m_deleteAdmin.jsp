<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除admin</title>
</head>
<body>
<h1>删除一个管理员</h1>
  </br><hr/>
<form action="../admin/deleteAdmin" class="Admin" method="POST" >
       管理员id：<input type="text" name="adminId"  ><br/> 
       管理员标记：<input type="text" name="addAdminMark"  ><br/>  <hr/>
  <input type="submit" name="submit" value="删除" />   
</form>
              注意：由于管理员的特殊性，所以添加管理员和删除管理员都需要一个特殊的标记。
      
</body>
</html>