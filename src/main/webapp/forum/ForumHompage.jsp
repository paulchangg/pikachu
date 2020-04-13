<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="member_login.jsp">登入</a>




	<div style="text-align: left: ;">
		<h1>熱門看板</h1>
		<c:forEach var="fname" items="${fname}">
			<h1>
				<a href="/ForumHompage/${fname}">${fname}</a>
			</h1>
			<br>
		</c:forEach>
	</div>


<form action="<c:url value='/forum/ConnectionForum_launch' />"
		method="POST" enctype="multipart/form-data">
		</div>

		<div>
			<label for="article_title">標題:</label> <input type="text"
				id="article_title" name="article_title" size="100"
				placeholder="標題不能少於10個字" /> <font color="red" size="-1">${errorMsg.TitleError}</font>
			<br>
		</div>
		<div>
			<label for="article_content" style="">內容:</label><br />
			<textarea cols="50" rows="5" in placeholder="內容不能少於100個字"></textarea>
			<font color="red" size="-1">${errorMsg.ContentError}</font> <br>
		</div>
		<div>
			<td><label class="fontSize">活動圖片：</label><br />&nbsp;</td>
			<td><input name="memberMultipartFile" type="file" /><br />&nbsp;</td>
		</div>

		<div>
			<label for="subject">主題:</label> <input type="text" id="subject"
				name="subject" size="100" />
		</div>
		<br>


		<div>
			活動的地點: <select name="location" id="location">
				<option value="Taipei">台北</option>
				<option value="Keelung">基隆</option>
				<option value="NewTaipei">新北</option>
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
		</div>
		<div>
			<label for="starte_Time">活動開始時間:</label> <input type="datetime-local"
				name="birthday" tabindex="7" value="${param.birthday}" /><br>
		</div>
		<div>
			<label for="endTime">活動結束時間:</label> <input type="datetime-local"
				name="birthday" tabindex="7" value="${param.birthday}" /><br>
			<input type="submit" value="提交活動" /><br />
		</div>
	</form>



<%--   <h1 ><a href="<c:url value='/forum/Launch_activityServlet?f_id=2' />">魯蛇卡</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=3' />">加油回饋</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=4' />">無限卡</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=5' />">電影</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=6' />">購物</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=7' />">現金回饋</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=8' />">宗教</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=9' />">公益</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=10' />">鈦金or御璽卡</a></h1> --%>
<%--   <h1 ><a href="<c:url value='/forum/ForumHompage?f_id=11' />">運動</a></h1> --%>
</body>
</html>