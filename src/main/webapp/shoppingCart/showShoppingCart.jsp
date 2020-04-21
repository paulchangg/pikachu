<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/shopping_cart.css" rel="stylesheet" type="text/css">
    <title>購物車清單</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
    crossorigin="anonymous" />
    <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/common/wickedcss.min.csss"/>
    <link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://ajax.googleapis.com/ajax/libs/d3js/5.15.0/d3.min.js"></script>
</head>
<body>

    <form>
       <input type="hidden" name="a"/>
    </form>
    <c:choose>
       <c:when test="${ShoppingCart.subtotal > 0}">
          <c:set var="subtotalMessage" value="金額小計:${ShoppingCart.subtotal} 元"/>
          <c:set var="subtotal" value="${ShoppingCart.subtotal}" scope="application"/>  
       </c:when>
       <c:otherwise>
          <c:set var="subtotalMessage" value="金額小計:  0 元"/>
          <c:set var="subtotal" value="0" scope="application"/>                
       </c:otherwise>
    </c:choose>

    <div class="flex-container">
        <!----------------------head區塊------------------------------>
        <div class="header">
			${LoginOK.m_id}皮卡丘商標位置<!--這裡開始為上方,呈現商標log位置-->
		   <div class="row justify-content-end"> 
				<div class="col-4">    <!---------logo區塊----------->
				   <img class="index_logo" src="/homepage/images/index_logo.jpg" alt="logo" />
			   </div>
			   <div class="col-4">     <!---------左上角超連結區塊----------->
					<div class="row justify-content-end">
						<div class="col-3">
							<a href="../member/member_login.jsp">
							  <i class="fa fa-user-circle" id="memberlogin">會員登入</i>
							</a>
					   </div>
						 <div class="col-3">
							 <a href="">
							  <i class="fa fa-credit-card" id="cardcompare">信用卡比較</i>
							</a>
						</div>
						<div class="col-3"> 
							 <a href="#">
							  <i class="fa fa-question-circle" id="qanda">Q & A</i>
							</a>
					   </div>
				   </div>
			   </div>
		   </div>
	   </div>
		<!-- 導覽列--------------------------->
	   <section class="top_navi">
		   <nav>
			   <a href="#">關於我們</a>
			   <a href="../member/member_center.jsp">會員中心</a>
			   <a href="#">論壇交友</a>
			   <a href="#">信用卡搜尋</a>
			   <a href="../listProduct/shopping.jsp">購物商城</a>
			   <a href="#">資訊網</a>
			</nav>
		</section>
            
        <!--這裡為步驟購物圖檔-->
        <div class="stepimg"><img src="image/參考圖.png" height="300px"></div>
    
        <!--這裡為購物清單-->
        <div class="tablearea">
            <h4>購物清單</h4>
                <table class="table table-hover ">
                   <thead><!--購物清單表格標題-->
                      <tr>
                         <th>商品名稱</th>
                         <th>優惠價</th>
                         <th>購買數量</th>
                         <th>目前庫存</th>
                         <th>商品小計</th>
                         <th>變更明細</th>
                     </tr>
                    </thead>
                    <tbody><!--表格內呈現資料-->
                    <c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}">
                      <tr>
                          <td><a href="<c:url value='/listProduct/DisplayPageProducts?mode=show&productId=${entry.value.p_id}' />" id="shoppingcart_page">
                              <img src="${pageContext.servletContext.contextPath}/init/getBookImage?id=${anEntry.value.p_id}" width="100px" id="shoppingcart_img">
                             ${anEntry.value.p_name} </a>
                          </td>
                          <td>NT<sapn id="shoppingcart_orp">${anEntry.value.price}</sapn>元
                          </td>
                          <td>
                          <!--<input id="shoppingcart_min" name="qty" type="button" value="-" />  按鈕的方法是用id寫得所以如果有第二筆物件就必須把方法裡面的id更換掉 -->
                          <%--<input id="shoppingcart_quantity" name="newQty${vs.index}" type="text" value="${anEntry.value.qty}" /> --%>
                              <Input id="newQty${vs.index}" style="width:70px;text-align:right" name="newQty" type="text" value="<fmt:formatNumber value="${anEntry.value.qty}" />" name="qty" onkeypress="return isNumberKey(event)"  />
                          <!--<input id="shoppingcart_add" name="qty" type="button" value="+" /> -->
                         </td>
                          <td><span id="shoppingcart_stock">30</span></td>
                          <td>小計:<span id="shoppingcart_total">${anEntry.value.qty * anEntry.value.price}</span></td>
                          <td>
                                <Input type="button" name="delete" value="刪除" onclick="confirmDelete(${anEntry.key})"><!--麻煩你寫了...智商過低我已經要陣亡Q_Q-->
                                <Input type="button" name="delete" value="修改" onclick="modify(${anEntry.key}, ${anEntry.value.qty}, ${vs.index})"><!--麻煩你寫了...智商過低我已經要陣亡Q_Q-->
                         </td>
                      </tr>
                      </c:forEach>
                        <tr><td colspan="5" align="right">訂單小計</td>
                            <td><span id="shoppingcart_money">${subtotal}</span>元</td>
                        </tr>
                    </tbody>
             </table>
        </div>

        <div class="col-12 return"> <!--繼續購物-->
            <form action="<c:url value='../_04_ShoppingCart/ProcessOrder.do' />" method="POST" >
               <Input type='hidden' name='finalDecision' value='Order'>
                <a href="<c:url value='../listProduct/DisplayPageProducts' />"><button type="button" class="btn btn-warning">繼續購物</button></a>
                <Input type='submit' class="btn btn-warning" data-toggle="modal" data-target="#shoopingmodal" id="shopping_addButton" value='結帳'>
            </form>
      </div>
         
	   <div class="site_footer"> <!--尾巴區塊-->
          <div class="gotop">
              <h4> © Java & Android 程式設計人才養成班 第13期第2組. All Rights Reserved</h4>
          </div>
     </div>
 </div>
    
        <!--這區塊是點選結算跳出視窗-->    
        <div class="modal fade" id="shoppingcart_paymoney">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <p>確認送出訂單?</p>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <a href="shopping_cart2.html"><button type="button" class="btn btn-default">確認</button></a>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
         <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>     
        <script src="js/shopping_cart.js"></script>
</body>
</html>