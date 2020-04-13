<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="../css/common/member_inquary.css" rel="stylesheet"
	type="text/css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>查看個人資料</title>
<script src="../js/jquery-3.4.1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/d3js/5.15.0/d3.min.js"></script>
</head>
<body>
	<div class="header">導覽列</div>
	<!--區塊1-->
	<div class="Memberinquary">
		<h3>查看個人資料</h3>
	</div>
	<!--區塊2-->

	<!--這裡有1個id 顯示資料庫帳號-->
	<div class="memberID">
		<h3>
			會員帳號ID:<span id="memberinquary_id">顯示註冊的會員帳號</span>
		</h3>
	</div>
	<!--區塊3-->


	<div class="photo" id="memberinquary_img">
		<c:choose>
			<c:when test="${empty LoginOK.m_img}">
				<img src="../images/common/membephoto_0.png" width="160" height="160">
				<!--區塊4-->
			</c:when>
			<c:otherwise>
				<img
					src='${pageContext.request.contextPath}/init/getMemberImage?id=${LoginOK.m_id}' width="160" height="160">
				<!--區塊4-->
			</c:otherwise>
		</c:choose>
	</div>

	<!--這裡有9個id 圖檔要顯示-->
	<div class="personaldata">
		<!--區塊5-->
		姓名:<span id="memberinquary_name">${LoginOK.name}</span><br> 暱稱:<span
			id="memberinquary_nickname">${LoginOK.nickname}</span><br> 生日<span
			id="memberinquary_birthday">${LoginOK.birthday}</span><br> 手機:<span
			id="memberinquary_phone_num">${LoginOK.phone_num}</span><br> 信箱:<span
			id="memberinquary_m_mail">${LoginOK.m_mail}</span><br> 性別:<span
			id="memberinquary_gender">${LoginOK.gender}</span><br> 年收入:<span
			id="memberinquary_income">${LoginOK.income}</span><br> 最高學歷:<span
			id="memberinquary_education">${LoginOK.education}</span><br>
		居住縣市:<span id="memberinquary_city">${LoginOK.city}</span><br>
	</div>

	<div class="footerbutton">
		<!--區塊6-->
		<a href="member_center.jsp"><input type="button" value="返回"></a>
	</div>

	<div class="footer">尾巴</div>
	<!--區塊7-->
</body>
</html>