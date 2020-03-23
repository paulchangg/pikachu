<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='/_04_ShoppingCart/UpdateItem.do?cmd=DEL&bookId=" + n +"' />" ;
// 		<a href="<c:url value='/_04_ShoppingCart/UpdateItem.do?cmd=DEL&bookId=' />" >
// action="<c:url value='/listProduct/BuyProduct.do' />"
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
function modify(key, qty, index) {
	var x = "newQty" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='/_04_ShoppingCart/UpdateItem.do?cmd=MOD&bookId=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
      return false;
   }
   return true;
}
function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}
function Abort() {
	if (confirm("確定放棄購物 ? ") ) {
		return true;
	} else {
		return false;
	}
}
</script>

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
 		<td width="200" style="text-align:right;">
                <Input id="newQty${vs.index}" style="width:28px;text-align:right" name="newQty" type="text" value="<fmt:formatNumber value="${anEntry.value.qty}" />" name="qty" onkeypress="return isNumberKey(event)"  />
         </td>
 		<td ><Input type="button" name="update" value="修改" onclick="modify(${anEntry.key}, ${anEntry.value.qty}, ${vs.index})">
             <Input type="button" name="delete" value="刪除" onclick="confirmDelete(${anEntry.key})"></td>
 		
 	</tr>
</table>
</c:forEach>
	<p>總金額:${subtotal}</p>
<tr height='80'>
   <td > 
     <table border='1'>
        <tr >
          <td width="265" align='center'>
              <a href="<c:url value='../listProduct/DisplayPageProducts' />">繼續購物</a>
          </td>
          <td width="265" align='center'>
              <a href="<c:url value='/_04_ShoppingCart/checkout.do' />" onClick="return Checkout(${subtotal});">再次確認</a>
          </td>
          <td width="265" align='center'>
              <a href="<c:url value='/_04_ShoppingCart/abort.do' />" onClick="return Abort();">放棄購物</a>
          </td>
        </tr>
     </table>
   </td>
</tr>

<form>
   <input type="hidden" name="a"/>
</form>

</body>
</html>