<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>N-CRM</title>
<link rel="stylesheet" href="<c:url value='/resources/css/text.css'/>" type="text/css">
</head>
<body class=body>
	<center>

		<table border=1 width=800>
			<tr>
				<td align="center" class=title>顧客情報管理システム</td>
			</tr>
		</table>
		<br>

		<table border=1 width=800>
			<tr class=sub_title>
				<td width=800 align="center" colspan=5>登録完了</td>
			</tr>
			<tr>
				<td width=250 align="center" class=hiro3>顧客情報を登録しました。</td>
			</tr>
		</table>
		<br>

		<form method="GET" action="/searchCustomer">

			<table width=800>
				<tr>
					<td width=800 align="center"><input type=submit
						value="顧客情報一覧へ戻る "></td>
				</tr>

				</form>

			</table>
	</center>
</body>

</html>
