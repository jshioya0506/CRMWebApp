<html>
<head>
<title>N-CRM</title>
<bgsound loop="infinite" src="sound/bgm.wma">
<link rel="stylesheet" href="css/text.css" type="text/css">
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
    <td align="right"><input type="submit" name="logout" value="ログアウト" /></td>
  </tr>
</table>
</form>
<form method="GET" action="customer_list_filter.html">
<table width=1000 class=search>
  <tr>
    <td width=100 align="center">担当営業</td>
    <td width=700>
      <select name="GROUP" >
        <option value="0">＜指定なし＞
        <option value="1">山本正伸
        <option value="2">島田菜々子
        <option value="3">江渕太郎
	<option value="4">山本祐加
      </select>
    </td>
  </tr>
  <tr>
    <td width=100 align="center">社名</td>
    <td width=700>
      <select name="SYAMEI">
        <option value="0">＜指定なし＞
        <option value="1">ヤマハ発動株式会社 IM事業部
        <option value="2">ABC株式会社 AAA事業部
      </select>
    </td>
    <td width=100><input type=submit value=" 検索 "></td>
  </tr>
<br>
</table>
</form>
<br>
<form method="GET" action="customer_detail.html">
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
    <td align="center">前回<br/>訪問日</td>
    <td align="center">関係性</td>
    <td align="center"></td>
  </tr>
  <tr class=hiro3>
    <td align="center">H0001</td>
    <td align="center">山本正伸</td>
    <td align="center">A-1</td>
    <td align="center">ヤマハ発動株式会社 IM事業部</td>
    <td align="center">静岡県浜松市中区早出町882</td>
    <td align="center">○○太郎</td>
    <td align="center">○○事業部 ○○グループ</td>
    <td align="center">グループリーダー</td>
    <td align="center">2017/10/01</td>
    <td align="center">良好</td>
    <td align="center"><input type=submit value="詳細"></td>
  </tr>
  <tr class=hiro2>
    <td align="center">T0001</td>
    <td align="center">島田菜々子</td>
    <td align="center">B-1</td>
    <td align="center">ABC株式会社 AAA事業部</td>
    <td align="center">東京都千代田区丸の内1丁目</td>
    <td align="center">△△次郎</td>
    <td align="center">△△△△事業部<br/>グループリーダー</td>
    <td align="center">△△△△事業部<br/>△△グループ</td>
    <td align="center">2017/11/01</td>
    <td align="center">普通</td>
    <td align="center"><input type=submit value="詳細"></td>
  </tr>

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
