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
	<h1>图片列表</h1>
	<table border="1" cellpadding="0">
		<c:if test="${empty pictures}">
			<tr>
				<td colspan="3">无图片显示</td>
			</tr>
		</c:if>

		<c:if test="${!empty pictures}">
			<tr>
				<td>图片</td>
				<td>描述</td>
				<td>删除</td>
			</tr>
		</c:if>

		<c:forEach items="${pictures }" var="pic">
			<tr>
				<c:if test="${pic.groupName != 'no_value'}">
					<td><img width="200" height="150"
						src="http://192.168.203.132/${pic.groupName}/${pic.fileName}"></td>
				</c:if>
				<c:if test="${pic.groupName == 'no_value'}">
					<td>没图片</td>
				</c:if>
				<td>${pic.picDesc }</td>
				<td><a href="picture/${pic.id }">删除</a></td>
			</tr>

		</c:forEach>



	</table>

</body>
</html>