<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>发新帖</h1>
	<form action="${pageContext.servletContext.contextPath}/solr/tiezi" method="post">
		标题:<input type="text" name="tieziTitle"><br>
		作者:<input type="text" name="tieziAuthor"><br>
		内容:<textarea rows="10" cols="50" name="tieziContent"></textarea><br>
		<input type="submit">
	</form>

</body>
</html>