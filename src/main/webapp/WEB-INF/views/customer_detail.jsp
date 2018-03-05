<html>
<head>
<title>N-CRM</title>
<bgsound loop="infinite" src="sound/bgm.wma">
<link rel="stylesheet" href="css/text.css" type="text/css">
</head>
<body class=body>
<script type="text/javascript">
<!--
// 削除
function delete(){
   if( confirm('上記内容で削除してもよろしいですか？') ) {
        document.main.action="customer_complete_delete.html";
        document.main.submit();
    }
    return;
}
// -->
</script>
<center>

<table border=1 width=1000 >
  <tr>
    <td align="center" class=title>顧客情報管理システム</td>
  </tr>
</table>
<form name="main">
<table border=1 width=1000>
  <tr class=sub_title>
    <td width=1000 align="center" colspan=5>顧客情報</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">社名</td>
    <td>ヤマハ発動株式会社 IM事業部</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">担当者(提案先)</td>
    <td>○○太郎</td>
  </tr>
  <tr class=hiro3>
   <td width=200 align="center">部署</td>
   <td>○○事業部 ○○グループ</td>
  </tr>
　<tr class=hiro3>
   <td width=200 align="center">役職</td>
   <td>グループリーダー</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">電話番号</td>
    <td>053-460-6109</td>
  </tr>
  
<tr class=hiro3>
    <td width=200 align="center">メールアドレス</td>
    <td>marumaru.tarou@yamaha.co.jp</td>
  </tr>
　<tr class=hiro3>
    <td width=200 align="center">顧客との関係性</td>
    <td>良好</td>
  </tr>
</table>
<table border=1 width=1000>
  <tr class=sub_title>
    <td width=1000 align="center" colspan=5>社内情報</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">担当者</td>
    <td>山本正伸</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">部署</td>
    <td>CCG</td>
  </tr>
　<tr class=hiro3>
    <td width=200 align="center">役職</td>
    <td>グループ長</td>
  </tr>
　<tr class=hiro3>
    <td width=200 align="center">電話番号</td>
    <td>053-451-0830</td>
  </tr>
　<tr class=hiro3>
    <td width=200 align="center">メールアドレス</td>
    <td>yamamoto@nexus-nt.co.jp</td>
  </tr>
</table>
<br>
<table border=1 width=1000>
  <tr class=sub_title>
    <td width=1000 align="center" colspan=5>企業情報</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">売上</td>
    <td width=100><u>8</u>点/10点</td>
    <td align="left"></td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">人数</td>
    <td width=100><u>9</u>点/10点</td>
    <td align="left"></td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">規模</td>
    <td width=100><u>15</u>点/20点</td>
    <td align="left">
    15点(大手メーカーで一部上場企業),
    10点(自社若しくは親会社が一部上場企業),
    5点(上記以外に応じて)
    </td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">事業内容</td>
    <td width=100><u>25</u>点/30点</td>
    <td align="left">
    30点(知名度も高く、シェアが広い自社パッケージ、自社製品の開発),
    20点(上記ではないが自社パッケージ、自社製品の開発、大手メーカーから一括請負),
    15点(大手メーカーから一括請負している),
    10点(上記以外に応じて)
    </td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">商流</td>
    <td width=100><u>20</u>点/20点</td>
    <td align="left">
    20点(エンド),
    15点(上記ではないが、商流が浅い(1回面談等)),
    10点(商流がある(2回面談まで))
    </td>
  </tr>
</table>
<br>
<table border=1 width=1000>
  <tr class=sub_title>
    <td width=1000 align="center" colspan=5>案件の質情報</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">要員提案</td>
    <td width=800><u>8</u>点/10点</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">単価</td>
    <td width=800><u>9</u>点/10点</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">案件内容</td>
    <td width=800><u>15</u>点/20点</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">若手受入れ</td>
    <td width=800><u>25</u>点/30点</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">決裁権</td>
    <td width=800><u>20</u>点/20点</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">担当窓口</td>
    <td width=800><u>20</u>点/20点</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">担当者との関係性</td>
    <td width=800><u>20</u>点/20点</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">スキル(開発言語)</td>
    <td width=800>
    	<input type="checkbox" name="programing_skill" value="1">Java
    	<input type="checkbox" name="programing_skill" value="2">Java若手
    	<input type="checkbox" name="programing_skill" value="4">VB.net
    	<input type="checkbox" name="programing_skill" value="8" checked="checked">VB6.0
    	<input type="checkbox" name="programing_skill" value="16" checked="checked">C#.net
    	<input type="checkbox" name="programing_skill" value="32">C#
    	<input type="checkbox" name="programing_skill" value="64">PHP
    	<input type="checkbox" name="programing_skill" value="128">PHP若手
    	<input type="checkbox" name="programing_skill" value="256">COBOL
    	<input type="checkbox" name="programing_skill" value="512">COBOL若手
    	<input type="checkbox" name="programing_skill" value="1024">VBA
    	<input type="checkbox" name="programing_skill" value="2048" checked="checked">C
    	<input type="checkbox" name="programing_skill" value="4096" checked="checked">C(制御)
    	<input type="checkbox" name="programing_skill" value="8192" checked="checked">C++
    	<input type="checkbox" name="programing_skill" value="16384">SQL
    	<input type="checkbox" name="programing_skill" value="32768">PL/SQL
    	<input type="checkbox" name="programing_skill" value="65536">ACCESS
    </td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">スキル(周辺業務)</td>
    <td width=800>
    	<input type="checkbox" name="tester_skill" value="1">テスター
    	<input type="checkbox" name="tester_skill" value="2">検証
    	<input type="checkbox" name="tester_skill" value="4">ヘルプデスク
    	<input type="checkbox" name="tester_skill" value="8" checked="checked">運用監視(24日365h)
    	<input type="checkbox" name="tester_skill" value="16" checked="checked">システム運用
    	<input type="checkbox" name="tester_skill" value="32">基盤構築
    	<input type="checkbox" name="tester_skill" value="64">サーバー構築
    	<input type="checkbox" name="tester_skill" value="128">サーバー運用
    	<input type="checkbox" name="tester_skill" value="256">ネットワーク構築
    	<input type="checkbox" name="tester_skill" value="512">ネットワーク運用
    	<input type="checkbox" name="tester_skill" value="1024">PMO
    	<input type="checkbox" name="tester_skill" value="2048">ドキュメント作成
    </td>
  </tr>
</table>
<br>
<table border=1 width=1000>
  <tr class=sub_title>
    <td width=1000 align="center" colspan=5>外注利用</td>
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">利用可否</td>
    <td width=800>不可</td>
    
  </tr>
  <tr class=hiro3>
    <td width=200 align="center">特記事項</td>
    <td width=800></td>
  </tr>
</table>
<br>
<table width=1000>
  <tr>
    <td width=1000 align="center">
      <input type=button value="削除" onclick="delete()">
    </td>
  </tr>
</form>
  <tr>
  <form method="GET" action="customer_list.html">
    <td with=800 align="right"><input type=submit value="戻る"></td>
  </form>
  </tr>
</table>
</center>
</body>
</html>
