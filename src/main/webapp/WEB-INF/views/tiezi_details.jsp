<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>帖子详情</h1>
	<table border="1" cellpadding="0">
		<tr>
			<td>题目:</td>
			<td>${resultMap.tieziTitle }</td>
		</tr>
		<tr>
			<td>作者:</td>
			<td>${resultMap.tieziAuthor }</td>
		</tr>
		<tr>
			<td>内容:</td>
			<td>${resultMap.tieziContent }</td>
		</tr>
	</table>

</body>
</html>