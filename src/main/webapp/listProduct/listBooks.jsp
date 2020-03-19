<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<c:forEach varStatus="stVar"  var="entry"  items="${products_DPP}" >	
    <table border="1">
        <tr height='100'>
            <td width="200">${entry.value.p_id}</td>
            <td width="200">${entry.value.p_name}</td>
            <td width="200">${entry.value.p_desc}</td>
            <td width="200">${entry.value.price}</td>
            <td width="200">${entry.value.stock}</td>
            <td width="200"><img height='100' width='80' 
    			 src='${pageContext.servletContext.contextPath}/init/getBookImage?id=${entry.value.p_id}'>
     		</td>
            <td width="200">${entry.value.p_bns}</td>
            <td>
             <FORM  action="<c:url value='/listProduct/BuyProduct.do' />" method="POST">
                                購買數量:
               <select name='qty'>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
               </select>
               <!-- 這些隱藏欄位都會送到後端 -->
               <Input type='hidden' name='productId' value='${entry.value.p_id}'>
               <Input type='hidden' name='pageNo' value='${pageNo}'>
               <Input type='submit' value='加入購物車'>
       			</FORM>
            
            </td>         
        </tr>
    </table>
</c:forEach>  
<div id="paging">
<!-- 以下為控制第一頁、前一頁、下一頁、最末頁 等超連結-->
<table border="1">
  <tr>
    <td width='76'>
        <c:if test="${pageNo > 1}">
           <div id="pfirst">
              <a href="<c:url value='/listProduct/DisplayPageProducts?pageNo=1' />">第一頁</a>
           </div>
        </c:if>
     </td>
     <td width='76'>
        <c:if test="${pageNo > 1}">
           <div id="pprev">
              <a href="<c:url value='/listProduct/DisplayPageProducts?pageNo=${pageNo-1}' />">上一頁</a>
           </div>
        </c:if>  
     </td>
     <td width='76'>
            <c:if test="${pageNo != totalPages}">
                <div id="pnext">
                   <a href="<c:url value='/listProduct/DisplayPageProducts?pageNo=${pageNo+1}' />">下一頁</a>
                </div>
            </c:if>
     </td>  
     <td width='76'>
            <c:if test="${pageNo != totalPages}">
                <div id="plast">
                    <a href="<c:url value='/listProduct/DisplayPageProducts?pageNo=${totalPages}' />">最末頁</a>
                </div>
            </c:if>
     </td>
     <td width='176' align="center">
                      第${pageNo}頁 / 共${totalPages}頁    
     </td>  
</tr>
</table>
</div>
<a href="<c:url value='../shoppingCart/showCartContent.jsp' />">購物車</a>

</body>
</html>