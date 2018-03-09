<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>N-CRM</title>
<link rel="stylesheet" href="<c:url value='/resources/css/text.css'/>" type="text/css">
</head>
<body class=body>
	<center>
		<table border=1 width=1200>
			<tr>
				<td align="center" class=title>顧客情報管理システム</td>
			</tr>
		</table>
		<br>
		<form action="auth"　method="post">
			<table width=1200 class=search>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right">メールアドレス</td>
					<td width=600><input type=text name="LOGIN_ID" size="30"
						maxlength="40"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right">パスワード</td>
					<td width=600><input type=password name="LOGIN_PASSWORD"
						size="30" maxlength="40"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right"></td>
					<td width=600 align="right"></td>
				</tr>
				<tr>
					<td width=600 align="right"><input type=submit value="ログイン"></td>
					<td width=600><input type=reset value="リセット"></td>
				</tr>
				<br>
			</table>
	</center>
</body>
</html>
