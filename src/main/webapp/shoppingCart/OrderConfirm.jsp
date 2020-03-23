<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "CANCEL";
		document.forms[0].action="<c:url value='ProcessOrder.do' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
function reconfirmOrder() {
	var sa = document.getElementById('ShippingAddress').value;
	if  (sa === "") {
		window.alert ('出貨地址不能是空白');
		return ; 
	}
	if (confirm("確定送出此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "ORDER";
		document.forms[0].action="<c:url value='ProcessOrder.do' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
</script>
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
</head>
<body>
<form action="<c:url value='ProcessOrder.do' />" method="POST" >
	<table border='1'>
		<tr >
			<td>
				會員姓名:${LoginOK.m_id}
			</td>
			<TD >
         		訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>
         	</TD>
		</tr>
		
	</table>
	<table>
		<tr>
			<td width='200'>商品名稱</td>
			<td width='200'>價格</td>
			<td width='200'>數量</td>
		</tr>
		<TR>
         <TD colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
                             出貨地址：<Input id='ShippingAddress' 
                   name="ShippingAddress" value="">
                
         </TD>
      	</TR>
		<c:forEach varStatus="vs" var="entry" items="${ShoppingCart.content}">
		<tr>
			<td width='200'>${entry.value.p_name}</td>
			<td width='200'>${entry.value.price}</td>
			<td width='200'>${entry.value.qty}</td>
		</tr>
		</c:forEach>
		<input type="hidden" name="finalDecision"  value="">   
   		<input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()">
   		<input type="button" name="CancelBtn" value="取消訂單" onclick="cancelOrder()">
	</table>
</form>
</body>
</html>