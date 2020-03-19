<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
   <c:when test="${ShoppingCart.subtotal > 0}">
      <c:set var="subtotalMessage" value="金額小計:${ShoppingCart.subtotal} 元"/>
      <c:set var="subtotal" value="${ShoppingCart.subtotal}"/>  
   </c:when>
   <c:otherwise>
      <c:set var="subtotalMessage" value="金額小計:  0 元"/>
      <c:set var="subtotal" value="0"/>                
   </c:otherwise>
</c:choose>

<table border="1">
 	<tr height='100'>
 		<td width="200">產品id</td>
 		<td width="200">產品名稱</td>
 		<td width="200">產品圖</td>
 		<td width="200">產品價格</td>
 		<td width="200">下單數量</td>
 	</tr>
</table>


 <c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}">
<table border="1">
 	<tr height='100'>
 		<td width="200">${anEntry.value.p_id}</td>
 		<td width="200">${anEntry.value.p_name}</td>
 		<td width="200"><img height='100' width='80' 
    			 src='${pageContext.servletContext.contextPath}/init/getBookImage?id=${anEntry.value.p_id}'>
     	</td>
 		<td width="200">${anEntry.value.price}</td>
 		<td width="200">${anEntry.value.qty}</td>
 	</tr>
</table>
</c:forEach>
	<p>總金額:${subtotal}</p>
</body>
</html>