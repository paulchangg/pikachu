<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=DEL&articleId=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
</script>

<meta charset="UTF-8">
<title>發布活動啦啦啦啦啦啦啦啦啦啦啦</title>
</head>
<body>
<form action="<c:url value='Launch_activityServlet' />" method="POST">
</form>
</body>
</html>