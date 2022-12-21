<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style>
			a {
				text-decoration: none;
			}
			.buttonImage {
				display: inline-block;
				font-size: 0.8em;
				background: #eeeeee;
				border: solid 1px #333333;
				border-radius: 3px;
				color: #000000;
				width: fit-content;
				padding: 2px 5px;
				text-align: center;
				text-decoration: none;
				cursor: arrow;
			}
			.buttonImage:hover {
				background-color: #dddddd;
			}
		</style>
		<title>修正内容を確定</title>
	</head>
	<body>
				<%
				List<KadaiDataBean> detailData = (ArrayList)request.getAttribute("detailData");
				for(KadaiDataBean bean : detailData) {
				%>
				<table>
						<tr><th>学籍番号</th>
							<td><input type="text" name="changed_id" value="<%= bean.getId() %>" pattern=".*\S+.*" maxlength="5" pattern="\d{5}" required></td></tr>
						<tr><th>在籍状態</th>
							<td><input type="text" name="changed_status" value="<%= bean.getStatus() %>" pattern="^[0-2]" required></td></tr>
						<tr><th>在籍状態確定日</th>
							<td><input type="date" name="changed_statusdate" value="<%= bean.getStatusEnterDate() %>" max="" required></td></tr>
						<tr><th>学生氏名</th>
							<td><input type="text" name="changed_name" value="<%= bean.getName() %>" pattern=".*\S+.*" required></td></tr>
						<tr><th>ふりがな</th>
							<td><input type="text" name="changed_furigana" value="<%= bean.getFurigana() %>" pattern="[\u3041-\u3096\s]*" required></td></tr>
						<tr><th>生年月日</th>
							<td><input type="date" name="changed_birth" value="<%= bean.getBirth() %>" max="" required></td></tr>
						<tr><th>本人郵便番号</th>
							<td><input type="text" name="changed_post" value="<%= bean.getPostNumber() %>" pattern=".*\S+.*" maxlength="7" required></td></tr>
						<tr><th>本人住所</th>
							<td><input type="text" name="changed_address" value="<%= bean.getAddress() %>" pattern=".*\S+.*" required></td></tr>
						<tr><th>本人電話番号</th>
							<td><input type="tel" name="changed_tel" value="<%= bean.getTellNumber() %>" pattern=".*\S+.*" required></td></tr>
						<tr><th>本人メールアドレス</th>
						<%
						if(bean.getMail() != null) {
						 %>
							<td><input type="email" name="changed_email" value="<%= bean.getMail() %>"></td></tr>
						 <%} else { %>
						 	<td><input type="text" name="changed_email" value="<%="" %></td>"></tr>
						 <%} %>
						<tr><th>保護者氏名</th>
							<td><input type="text" name="changed_pname" value="<%= bean.getParentName() %>" pattern=".*\S+.*" required></td></tr>
						<tr><th>保護者ふりがな</th>
							<td><input type="text" name="changed_pfurigana" value="<%= bean.getParentFurigana() %>" pattern="[\u3041-\u3096\s]*" required></td></tr>
						<tr><th>保護者郵便番号</th>
							<td><input type="text" name="changed_ppost" value="<%= bean.getParentPostNumber() %>" pattern=".*\S+.*" maxlength="7" required></td></tr>
						<tr><th>保護者住所</th>
							<td><input type="tel" name="changed_paddress" value="<%= bean.getParentAddress() %>" pattern=".*\S+.*" required></td></tr>
						<tr><th>保護者電話番号</th>
							<td><input type="email" name="changed_ptel" value="<%= bean.getParentTellNumber() %>" pattern=".*\S+.*" required></td></tr>
						<tr><th>保護者メールアドレス</th>
						<%
						if(bean.getParentMail() != null) {
						 %>
						 	<td><input type="text" name="changed_pemail" value="<%= bean.getParentMail() %>"></td></tr>
						 <%} else { %>
						 	<td><input type="text" name="changed_pemail" value="<%="" %>"></td></tr>
					<%
						}
					}
					%>
		<b>修正を確定しますか？</b>
		
		<button type="submit">はい</button>
		<button type="button" onclick="history.back()">戻る</button>
	</body>
</html>