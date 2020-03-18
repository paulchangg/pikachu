<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/common/member_data.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>填寫個人資料</title>
</head>
<body>
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <div class="header">導覽列</div>
    <div class="Memberdata"><h3>填寫個人資料</h3></div>

                                       <!--這裡有1個id 顯示資料庫帳號-->
    <div class="memberID"><h3>會員帳號ID:<span id="memberdata_id">資料庫帳號</span></h3></div>

    
                      <!--這裡有1個id 顯示目前會員的頭像-->
    <div class="photo" id="memberdata_img" >
        <img src="images/common/membephoto_0.png" width="160" height="160" > 
        <div>
        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#imagemodal" >選擇圖片</button>
        </div>
    </div> 

    <!--這區塊是跳出視窗-->    
    <div class="modal fade" id="imagemodal">
      <div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
            <h3>選擇您喜愛的圖像</h3>
              <form action="" method="">                                    
              <label class="radio-inline"><input type="radio" name="m_img" value="memberphoto_1"><img src="images/common/memberphoto_1.jpg" width="100" height="100"></label>
              <label class="radio-inline"><input type="radio" name="m_img" value="memberphoto_2"><img src="images/common/memberphoto_2.jpg" width="100" height="100"></label>
              <label class="radio-inline"><input type="radio" name="m_img" value="memberphoto_3"><img src="images/common/memberphoto_3.jpg" width="100" height="100"></label>
              <label class="radio-inline"><input type="radio" name="m_img" value="memberphoto_4"><img src="images/common/memberphoto_4.jpg" width="100" height="100"></label>
              <label class="radio-inline"><input type="radio" name="m_img" value="memberphoto_5"><img src="images/common/memberphoto_5.jpg" width="100" height="100"></label>
              <label class="radio-inline"><input type="radio" name="m_img" value="memberphoto_6"><img src="images/common/memberphoto_6.jpg" width="100" height="100"></label>
              <input type="submit" value="確定">
            </form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					</div>
				</div>
			</div>
    </div>
   <!--這區塊是跳出視窗-->


    <div class="personaldata"><h3>個人資料</h3>
    <hr>
     <form action="" method="">
        姓名:<input type="text" name="name" placeholder="請輸入中文姓名" required autofocus><font color="red">*填寫後不可更改</font>
     <p>暱稱:<input type="text" name="nickname"></p>
     <p>生日:<input type="date" name="birthday" required ><font color="red">*填寫後不可更改</font></p>
     <p>手機:<input type="text" name="phone_num"pattern="[0-9]{10}" placeholder="輸入10位數字" required ><font color="red">*填寫後不可更改</font></p>
     <p>信箱:<input type="text" name="m_mail"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" ></p>
        性別:<input type="radio" name="gender" value="male" required>男
             <input type="radio" name="gender" value="female" required>女       
     <p>年收入<select name="income">
              <option value="30under">36萬以下</option>
              <option value="36-50">36萬-50萬</option>
              <option value="30-50">51萬-70萬</option>
              <option value="71-99">71萬-99萬</option>
              <option value="71-99">100萬以上</option>
              </select>  
     </p>
     <p>最高學歷:<select name="education">
            <option value="Elementary">國小</option>
            <option value="Juniorhigh">國中</option>
            <option value="Highschool">高中</option>
            <option value="University">大學</option>
            <option value="Graduate-Doctor">碩博</option>
            </select>  
    </p>
     <p>居住縣市:
        <select name="city">
            <option value="Keelung">基隆</option>
            <option value="Taipei">台北/新北</option>
            <option value="Taoyuan">桃園</option>
            <option value="Hsinchu">新竹</option>
            <option value="Miaoli">苗栗</option> 
            <option value="Taichung">台中</option> 
            <option value="Changhua">彰化</option> 
            <option value="Nantou">南投</option> 
            <option value="Yunlin">雲林</option>
            <option value="Chiayi">嘉義</option>
            <option value="Tainan">台南</option>
            <option value="Kaohsiung">高雄</option>
            <option value="Pingtung">屏東</option> 
            <option value="Taitung">台東</option> 
            <option value="Hualien">花蓮</option> 
            <option value="Yilan">宜蘭</option> 
            <option value="insland">外島</option> 
        </select>  
    </p>
     <p><input type="submit" value="儲存">
        <a href="MemberCenter.html"><input type="button" value="返回"></a>
     </p>
     </form>
    </div>
    
    <div class="footer">尾巴</div>
</body>
</html>