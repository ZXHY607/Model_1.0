<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
<%--关振宇创建，测试批量添加学生的功能 --%>

<form action="<%=request.getContextPath() %>/servlet/FileUploadServlet" method="post" enctype="multipart/form-data" >
        上传人名:<input type="text" name="name" /><br/>      
          上传文件:<input type="file" name="file" /><br/>
          <input type="submit" name="submit" value="提交" />
      </form>

</body>
</html>