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
	<h1>查询到的相关帖子</h1>
	<table border="1" cellpadding="0">
		<tr>
			<th>题目</th>
			<th>作者</th>
			<th>详情</th>
		</tr>
		<c:if test="${empty resultMap }">
			<tr>
				<td colspan="3">没有查询到相关数据</td>
			</tr>
		</c:if>
		
		<c:if test="${!empty resultMap }">
			<c:forEach items="${resultMap }" var="entry">
				<tr>
					<td>${entry.value['tiezi_title']}</td>
					<td>${entry.value['tiezi_author']}</td>
					<td><a href="${pageContext.servletContext.contextPath}/solr/tiezi/${entry.key}">详情</a></td>
				</tr>
				
			</c:forEach>

		</c:if>


	</table>

</body>
</html>