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

<%-- <c:forEach varStatus="stVar"  var="entry"  items="${products_DPP}" >	 --%>
<!--     <table border="1"> -->
<!--         <tr height='100'> -->
<%--             <td width="200">${entry.value.p_id}</td> --%>
<%--             <td width="200">${entry.value.p_name}</td> --%>
<%--             <td width="200">${entry.value.p_desc}</td> --%>
<%--             <td width="200">${entry.value.price}</td> --%>
<%--             <td width="200">${entry.value.stock}</td> --%>
<!--             <td width="200"><img height='100' width='80'  -->
<%--     			 src='${pageContext.servletContext.contextPath}/init/getBookImage?id=${entry.value.p_id}'> --%>
<!--      		</td> -->
<%--             <td width="200">${entry.value.p_bns}</td> --%>
<!--             <td> -->
	<form action="<c:url value='/forum/Launch_activityServlet' />"
		method="POST" enctype="multipart/form-data">



		<h1 style="text-align: center;">我要發文啦</h1>

		<div>
			<span size="10"> <img src="../images/common/memberphoto_2.jpg"
				style="width: 128px; height: 128px;" />
			</span>
			
			 版名:
               <Input type='hidden' name='f_id' value='${f_id}'>
			<h1 ><a href="/forum/ForumHompage?f_id=1">${fname}</a></h1>		 
			<h1 ><a href="/forum/ForumHompage?f_id=2">${fname}</a></h1>		 
			<h1 ><a href="/forum/ForumHompage?f_id=3">${fname}</a></h1>		 
			 
               <Input type='hidden' name='fname value='${fname}'>
			 
			 
<!--                <select name='fname'> -->
               
               
               
               
<%--                     <option value="<a href="/forum/ForumHompage?f_id=1">">旅遊</option> --%>
<%--                     <option value="<a href="/forum/ForumHompage?f_id=2">">魯蛇卡</option> --%>
<!--                     <option value="3">加油回饋</option> -->
<!--                     <option value="4">無限卡</option> -->
<!--                     <option value="5">電影</option> -->
<!--                     <option value="6">購物</option> -->
<!--                     <option value="7">現金回饋</option> -->
<!--                     <option value="8">宗教</option> -->
<!--                     <option value="9">公益</option> -->
<!--                     <option value="10">鈦金or御璽卡</option> -->
<!--                     <option value="11">運動</option> -->
<!--                </select> -->
               <!-- 這些隱藏欄位都會送到後端 -->
			
			
			
			
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
<!--             </td>          -->
<!--         </tr> -->
<!--     </table> -->
<%-- </c:forEach>  --%>


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