/*function cancelOrder() {
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
*/

function reconfirmOrder() {
	if (confirm("訂單成立後無法修改,確定是否送出? ") ) {
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