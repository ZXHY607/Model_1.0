<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Model/course/add" method="post">
	 <input name="name" placeholder="课程名称"><br><br>

	 <input name="tId" placeholder="负责人ID" ><br><br>
	 课程简介<br><br>
	 <textarea name="intro" cols="30" rows="5">
	 </textarea><br><br>
	 <button >确定</button>
	</form>
</body>
</html>