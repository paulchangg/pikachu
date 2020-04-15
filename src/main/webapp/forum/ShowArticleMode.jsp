<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	function confirmDelete(n) {
		if (confirm("確定刪除此文章 ? ")) {
			document.forms[0].action = "<c:url value='/forum/UpdateItem.do?cmd=DEL&articleId="
					+ n + "' />";
			document.forms[0].method = "POST";
			document.forms[0].submit();
		} else {

		}
	}

	function modify(n, article) {
		if (confirm("確定將此文章做更改?")) {
			document.forms[0].action = "<c:url value='/forum/UpdateItem.do?cmd=MOD&articleId="
					+ n + "article" + article + "' />";
			document.forms[0].method = "POST";
			document.forms[0].submit();
		}
	}
</script>

<meta charset="UTF-8">
<title>發布活動啦啦啦啦啦啦啦啦啦啦啦</title>
</head>
<style>
textarea {
	width: 100%;
	height: 150px;
	padding: 12px 20px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	background-color: #f8f8f8;
	font-size: 16px;
	resize: none;
}
</style>
<body>



	<h1 style="text-align: center;">我要發文啦</h1>

	<div>
		<span> <img src="../images/common/memberphoto_2.jpg"
			style="width: 128px; height: 128px;" />
		</span>
	</div>

	<h1>版名:${sessionfname}</h1>
	
	<form action="<c:url value='/forum/Launch_activityServlet' />"
		method="POST" enctype="multipart/form-data">
		<div>
			<label for="article_title">標題:</label> 
			<input type="text"
				id="article_title" name="article_title" size="100"
				placeholder="標題不能少於10個字" value="${param.article_title}" /> 
				<font color="red" size="-1">${MsgMap.TitleError}</font> <br>
		</div>
		<div>
			<label for="article_content"   name="article_content" size="100" >內容:</label><br>
			

			
			<textarea cols="50" rows="5" placeholder="內容不能少於100個字"  name="article_content" value="${param.article_content}"></textarea>
			<br> <font color="red" size="-1">${MsgMap.ContentError}</font> <br> 
		</div>
		<div>
			<label class="fontSize">活動圖片：</label><br />&nbsp; <input
				name="articleimage" type="file" value="${param.articleimage}" /><br />&nbsp;

		</div>

		<div>
			<label for="subject">主題:</label> <input type="text" id="subject"
				name="subject" size="100" value="${param.subject}" /><br> <font
				color="red" size="-1">${MsgMap.subjectError}</font><br>


		</div>
		<br>


		<div>
			活動的地點: <select name="location" id="location" value ="${param.location}">
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
			</select> <font color="red" size="-1">${MsgMap.subjectError}</font><br>
		</div>
		<div>
			<label for="starteTime">活動開始時間:</label> <input type="date"
				name="starteTime" tabindex="7" value="${param.starteTime}" /><br>

			<font color="red" size="-1">${MsgMap.starte_TimeError}</font> <br>
		</div>
		<div>

			<label for="endTime">活動結束時間:</label> <input type="date"
				name="endTime" tabindex="7" value="${param.endTime}" /><br> <font
				color="red" size="-1">${MsgMap.endTimeError}</font> <br> <input
				type="submit" name="提交活動" size="100" /><br>

		</div>
	</form>





	<form action="<c:url value='/forum/ResponserServlet' />" method="POST">

	</form>




	<script type="text/javascript">
		function confirmDelete(n) {
			if (confirm("確定刪除此文章 ? ")) {
				document.forms[0].action = "<c:url value='/forum/UpdateDelResponerServlet?cmd=DEL&articleId="
						+ n + "' />";
				document.forms[0].method = "POST";
				document.forms[0].submit();
			} else {

			}
		}

		function modify(n, article) {
			if (confirm("確定將此文章做更改?")) {
				document.forms[0].action = "<c:url value='/forum/UpdateDelResponerServlet?cmd=MOD&articleId="
						+ n + "article" + article + "' />";
				document.forms[0].method = "POST";
				document.forms[0].submit();
			}
		}
	</script>




</body>
</html>