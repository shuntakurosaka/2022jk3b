<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>エラー</title>
	</head>
	<body>
		<h2>エラーが発生しました</h2>
		<b>エラー原因</b>
		<%
			List<String> errList = (List<String>)request.getAttribute("errList");
			for(String error : errList) {
		%>
		<b><%= error %></b>
		<%
			}
		%>
	</body>
</html>