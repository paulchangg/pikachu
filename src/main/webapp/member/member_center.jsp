<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="/css/common/member_center.css" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員中心</title>
    <script src="jquery-3.4.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/d3js/5.15.0/d3.min.js"></script>
</head>
<body>
    <div class="header">導覽列</div>
    <div class="membercenter">會員中心</div>

    <div class="personal"><h3>個人訊息</h3>
        <div class="human" id="MemberCenter-img"><!--顯示會員的圖像id-->
            <img src="/images/common/membephoto_0.png" width="150" height="150" >
    </div> 

        <div class="personaltext"><!--共有4個id-->
             最新發表文章:<a href="" target="blank"><span id="membercenter_article">信用卡好像辦不過</span></a>
          <p>累積發表文章數:<a href="" target="blank"><span id="membercenter_number">33</span></a>篇</P>
          <p>文章發表新回應:<a href="" target="blank"><span id="membercenter_answer">無</span></a></p>
             累積紅利點數:<span id="membercenter_points">55</span>
        </div>
    </div> 

    <div class="friend"><h3>交友配對</h3> <!--共有1個id-->
       <p>
          <a href="" target="blank">好友清單</a> 
      </p>
         <a href="" target="blank"><span id="membercenter_message">未讀悄悄話</span></a>
    </div>

    <!--共有6個id-->
    <div class="cridecart"><h3>信用卡收藏</h3>
          <div class="newCrideCard-1">
            <form action="" method="">
            <input type="button" value="刪除" id="membercenter_delete1"> 
            <input type="button" value="新增信用卡" id="membercenter_add1"> 
            </form>
          </div>
         <div class="newCrideCard-2">
            <form action="" method="">
            <input type="button" value="刪除" id="membercenter_delete2" > 
            <input type="button" value="新增信用卡"id="membercenter_add2"> 
           </form>
         </div>
        <div  class="newCrideCard-3">
            <form action="" method="">
                <input type="button" value="刪除" id="membercenter_delete3"> 
                <input type="button" value="新增信用卡" id="membercenter_add3"> 
               </form>
        </div>
    </div>

    <div class=container>
       <div class="order"><h3>訂單資料</h3>
       <a href="" target="blank">訂單查詢</a>
       <p><a href="" target="blank"><p>追蹤清單</a></p>
       <p><a href="" target="blank"><p>聯絡客服</a></p>
       </div>

       <div class="memberdata"><h3>會員資料</h3>
       <a href="member/member_data.jsp" target="blank">個人資料填寫</a>
       <p><a href="member/member_edit.jsp" target="blank">修改</a></p>
       <p></p><a href="member/member_inquary.jsp" target="blank">查看</a></p>
       </div>
    </div>

    <div class="footer">尾巴</div>
</body>
</html>