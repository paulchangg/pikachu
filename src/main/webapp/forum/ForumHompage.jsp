<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<div style="text-align: left: ;">
		<h1>熱門看板</h1>
		<c:forEach var="fname" items="${fname}">
			<h1>
				<a href="/ForumHompage/${fname}">${fname}</a>
			</h1>
			<br>
		</c:forEach>
	</div>
</body>
</html>