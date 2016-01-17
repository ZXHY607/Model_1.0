<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member" import="net.gslab.entity.Admin" 
    import="net.gslab.entity.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="view_all/css/login.css">
<title>注销</title>
</head>
<body class="back">
 <h1 class="t1">注销页面</h1>
       <%String type=(String)session.getAttribute("LOGIN_TYPE") ;%></br>
       <span class="t2">  <%if(type!=null&&type.equals("student")) {
          Member member=(Member) session.getAttribute("MEMBER_CONTEXT");
         out.println("登陆学生ID:"+member.getMemberId());
          out.println("登陆学生姓名:"+member.getMemberName());
       }%></span></br>
       <%if(type!=null&&type.equals("teacher")){
          Teacher teacher=(Teacher) session.getAttribute("MEMBER_CONTEXT");
          out.println("登陆老师ID:"+teacher.getTeacherId());
          out.println("登陆老师姓名:"+teacher.getTeacherName());

       }%>
       <%if(type!=null&&type.equals("admin")){
          Admin admin=(Admin) session.getAttribute("MEMBER_CONTEXT");
          out.println("登陆管理员ID:"+admin.getAdminId());
          out.println("登陆管理员姓名:"+admin.getAdminName());
       }%>
       
     <span class="t2">    <%if(type==null){
    	   out.println("您并未登陆，无法注销！");
       }%></span><br>

  <span class="t2">    <%if(type!=null&&!type.equals("")){ 
	  out.println("login_type:"+type);%></span></br>
  
   <form action="/Model/login2/doLoginOut" class="loginOut" method="POST">
         <input class="title" type="submit" name="submit" value="注销" />   
  <%} %>
  </form>
  <div class="foot">
	  <a class="forget_pn" href="/Model/view_all/a_sy.html">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返回主页>></a>
	</div>
</body>
</html>