<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师信息批量上传</title>
</head>
<body>
 <h1>批量添加老师</h1><hr/>
      <form action="../teacher/uploadExcel" method="post" enctype="multipart/form-data" >
                    上传文件:<input type="file" name="file" /><br/>
            <input type="submit" name="submit" value="提交" />
      </form>
      </br> </br> </br> </br> </br><hr/>
                   注意：excel文件的格式应该是 “ id		teacherName		password   ” 
                   下图供参考： </br> </br><img alt="excel参考" src="../images/uploadExcel_Teacher_example.png">
        


</body>
</html>