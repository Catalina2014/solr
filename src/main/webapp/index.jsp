<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/">
<title>Insert title here</title>
</head>
<body>
	<a href="solr/create">创建新帖</a>
	<br>
	<form action="solr/tiezi" method="post">
		<input type="text" name="keywords"/> 
		<input type="submit" value="找帖子"/>
	</form>
	<br>
	<br>
	
	<form action="picture" method="post" enctype="multipart/form-data">
		图片描述:<input type="text" name="picDesc"/><br>
		<input type="file" name="pic"/><br>
		<input type="submit" value="上传图片"/>
	</form>
	
	<a href="pictures">显示所有图片</a>
</body>
</html>