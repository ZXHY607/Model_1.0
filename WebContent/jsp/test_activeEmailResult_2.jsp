<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="net.gslab.entity.Member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活邮箱  结果页面2</title>
</head>
<body>
        <%Member member=(Member)session.getAttribute("MEMBER_CONTEXT"); 
        out.println("已登陆学生的姓名:"+member.getMemberName());
        out.print("</br>");
        out.println("已登陆学生的ID:"+member.getMemberId());out.print("</br>");
        %></br>
                          邮箱: <%=member.getEmail() %></br>
                          激活结果：<%=request.getParameter("ERROR_MSG_KEY") %></br>
                    
                          邮箱 激活状态：    <%=member.getEmail_active() %>     </br> "1"代表激活，"0"代表未激活      
 
</body>
</html>