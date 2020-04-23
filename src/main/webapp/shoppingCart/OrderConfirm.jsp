<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/OrderConfirm.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />
    <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">
   <link rel="stylesheet" href="../css/common/wickedcss.min.csss"/>
    <link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css' rel='stylesheet'>
	<script src="https://ajax.googleapis.com/ajax/libs/d3js/5.15.0/d3.min.js"></script>
	<title>訂單確認</title>
	<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
</head>
<body>
    <div class="flex-container">
        <!----------------------head區塊------------------------------>
        <div class="header">
			${LoginOK.m_id}皮卡丘商標位置<!--這裡開始為上方,呈現商標log位置-->
		</div>
		<!--訂單確認區塊-->
	    <div class="headertwo">
			訂單確認
		</div>
		<!--參考圖區塊-->
		<div class="stepimg"><img src="image/參考圖.png" height="230px"></div>
  
		<!--中間整體區塊-->
		<div class="row centertotal">
			<div class="col-5 centerone">
				<form action="<c:url value='ProcessOrder.do' />" method="POST" >
			     <table class="orderarea"><!--訂購人資訊-->
					<tr>
						<th colspan="2"><i class='bx bx-pencil'></i>填寫購買人資訊</th>
					</tr>
				    <tr>
					   <td>姓名</td>
					   <td><input type="text" required name=""></td>
				   </tr>
				    <tr>
					   <td>Email</td>
					   <td><input type="email" required name=""></td>
				   </tr>
				    <tr>
					   <td>手機</td>
					   <td><input type="tel" required name=""></td>
					</tr>
				 </table>
				 <table class="orderarea"><!--付款方式當作未來展望不須理會-->
					 <tr>
						 <th><i class='bx bx-coin'></i>付款方式</th>
					 </tr>
					 <tr>
					    <td><input type="radio"  name="paycredit">信用卡</td>
					 </tr>
					 <tr>
						 <td><input type="radio" name="paycredit">Web ATM</td>
					 </tr>
					 <tr>
						<td><input type="radio" name="paycredit">ATM 轉帳</td>
					</tr>
					<tr>
						<td><input type="radio" name="paycredit">全家 FamiPort 付款(免手續費)</td>
					</tr>
				 </table>
				 <table class="orderarea"><!--發票當作未來展望不須理會-->
					 <tr><td>發票</td>
						  <td><select name="invoce">
							  <option>捐贈愛台灣流浪貓咪協會</option>
							  <option>索取個人戶電子發票</option>
							  <option>索取紙本公司戶電子發票</option>
							</select>
						</td>
					</tr>
				 </table>
				 <input type="button" name="OrderBtn"  value="確定送出" onclick="reconfirmOrder()" id="orderBtn">
			   </form>
			</div>
			 <div class="col-4 centertwo"><!--購買內容-->
				<div>
				     <table class="tabletwo">
						<tr>
							<td><span id="confirm_proudce">商品名稱</span></td>
							<td><span id="confirm_quenty">數量</span></td>
							<td><span id="confirm_price">優惠價</span></td>
						</tr>
						<tr><td>付款總計</td>
							<td colspan="2"><span id="confirm_totalprice"></span></td>
						</tr>
				    </table>
		       </div>
			</div>
		</div>


        <!--這裡返回訂單查詢頁面按鈕區塊-->
        <div class="footerbutton">
                <a href="showShoppingCart.jsp"><input type="button" value="上一步" class="fobutton"></a>
        </div>
    
           <!--這裡為底部區塊-->
           <div class="site_footer"> <!--尾巴區塊-->
              <div class="gotop">
                 <h4> © Java & Android 程式設計人才養成班 第13期第2組. All Rights Reserved</h4>
             </div>
          </div>

  </div>      
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
     integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
     crossorigin="anonymous"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
     <script type="text/javascript" src="/js/jquery-barcode.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
     integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
     crossorigin="anonymous"></script>  
     <script src="js/orderconfirm.js"></script>   
</body>
</html>