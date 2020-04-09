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
	if (confirm("確定刪除此文章 ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=DEL&articleId=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}

function modify(n,article) {
	if (confirm("確定將此文章做更改?") ){
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=MOD&articleId=" + n +"article"+article+"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} 
}

</script>

<meta charset="UTF-8">
<title>發布活動啦啦啦啦啦啦啦啦啦啦啦</title>
</head>
<body>
<form action="<c:url value='Launch_activityServlet' />" method="POST">

</form>



<form action="<c:url value='ResponserServlet' />" method="POST">

</form>


<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此文章 ? ") ) {
		document.forms[0].action="<c:url value='UpdateDelResponerServlet?cmd=DEL&articleId=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}

function modify(n,article) {
	if (confirm("確定將此文章做更改?") ){
		document.forms[0].action="<c:url value='UpdateDelResponerServlet?cmd=MOD&articleId=" + n +"article"+article+"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} 
}

</script>




</body>
</html>