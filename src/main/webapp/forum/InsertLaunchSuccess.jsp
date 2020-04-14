<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lab05_03</title>
</head>
<body>
<h1>文章編號${ Launch_activityBean.article_Id } 的資料新增成功</h1>
會員: ${ Launch_activityBean.article_m_id }<br>
活動標題: ${ Launch_activityBean.article_title }<br>
活動內容: ${ Launch_activityBean.article_content }<br>
活動圖片: ${ Launch_activityBean.articleimage }<br>
活動主題: ${ Launch_activityBean.subject }<br>
活動地點: ${ Launch_activityBean.location }<br>
PO文時間: ${ Launch_activityBean.post_Time }<br>
活動開始時間: ${ Launch_activityBean.startTime }<br>
活動結束時間: ${ Launch_activityBean.endTime }<br>
</body>
</html>