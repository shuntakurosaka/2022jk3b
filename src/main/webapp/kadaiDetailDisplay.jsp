<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.KadaiDataBean"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style type="text/css">
			table {
				border-collapse: collapse;
			}
			table, th, td {
				border: solid 1px #000000;
			}
			th, td {
				padding: 5px;
			}
			.formarea {
				margin-left: 30px;
			}
			.buttonarea {
				margin-top: 20px;
			}
			.linkStyle {
				display: inline-block;
				padding: 10px;
				color: #0000ff;
			}
			.noLinkStyle {
				display: line-block;
				padding: 10px;
				color: #99999;
			}
		</style>
		<title>一覧</title>
	</head>
	<body>
		<header>
			<h2>データの編集</h2>
		</header>
		<main>
			
			<form class="formarea" method="get" action="select">
				<table>
					<tr>
						<th>選択</th>
						<th>学籍番号</th>
						<th>学生氏名</th>
						<th>ふりがな</th>
					</tr>
					<%
						List<KadaiDataBean> data = (ArrayList)request.getAttribute("data");
						for(KadaiDataBean bean : data) {
					%>
					<tr>
						<td><input type="radio" name="id" value="<%= bean.getId() %>" id="radio"></td>
						<td><label for="radio"><%= bean.getId() %></label></td>
						<td><label for="radio"><%= bean.getName() %></label></td>
						<td><label for="radio"><%= bean.getFurigana() %></label></td>
					</tr>
					<%
						}
					%>
				</table>
				<div class="buttonarea">
					<button type="button" onclick="history.back()">戻る</button>
					<button type="submit" name="submit" value="insert">編集</button>
					
				</div>
			</form>
		</main>
	</body>
</html>