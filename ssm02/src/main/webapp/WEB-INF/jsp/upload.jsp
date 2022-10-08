<%--
  Created by IntelliJ IDEA.
  User: she
  Date: 2022/9/28
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="uploadfile" enctype="multipart/form-data" method="POST">
    请选择一个文件上传 :
    <input type="file" name="upload" />
    <input type="submit" value="上传" />
</form>
</body>
</html>
