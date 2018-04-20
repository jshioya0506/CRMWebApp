<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>N-CRM</title>
<link rel="stylesheet" href="<c:url value='/resources/css/text.css'/>"
	type="text/css">
</head>
<body class=body>
	<center>
		<table border=1 width=1000>
			<tr>
				<td align="center" class=title>顧客情報管理システム</td>
			</tr>
		</table>
		<br>
		<form action="login.html">
			<table width=1000>
				<tr>
					<td align="right"><input type="submit" name="logout"
						value="ログアウト" /></td>
				</tr>
			</table>
		</form>
		<form method="GET" action="searchCustomer">
			<table width=1000 class=search>
				<tr>
					<td width=100 align="center">担当営業</td>
					<td width=700>
					   <!-- 検索候補を設定 -->
					   <select name="staff">
							<option value="0">＜指定なし＞</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td width=100 align="center">社名</td>
					<td width=700>
					   <!-- 検索候補を設定 -->
					   <select name="syamei">
							<option value="0">＜指定なし＞</option>
					   </select>
					</td>
					<td width=100><input type=submit value="検索 "></td>
				</tr>
				<br>
			</table>
		</form>
		<br>
		<form method="GET" action="/detailCustomer">
			<table border=1 width=1000>
				<tr class=sub_title>
					<td width=1000 align="center" colspan=11>顧客一覧</td>
				</tr>
				<tr class=sub_title>
					<td align="center">顧客<br>番号</td>
					<td align="center">担当<br>営業</td>
					<td align="center">ランク</td>
					<td align="center">社名</td>
					<td align="center">住所</td>
					<td align="center">担当者</td>
					<td align="center">部署名</td>
					<td align="center">役職</td>
					<td align="center">前回<br />訪問日
					</td>
					<td align="center">関係性</td>
					<td align="center"></td>
				</tr>
				<!-- レコード表示  -->
			</table>
		</form>
		<br>
		<form method="GET" action="customer_register.html">
			<table width=1000>
				<tr>
					<td with=800 align="right"><input type=submit value="新規登録">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
