<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/sys/demo/uploadFile" enctype="multipart/form-data" method="post">
		<input type="text" name="fileType" id="fileType">
		<input type="file" name="file" id="file">
		<input type="submit" value="提交"> 
	</form>

</body>
</html>