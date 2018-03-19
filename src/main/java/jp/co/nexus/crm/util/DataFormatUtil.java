package jp.co.nexus.crm.util;

/**
 * 画面表示用のデータフォーマットクラス
 * @author jshioya
 *
 */
public final class DataFormatUtil implements DataFormatConstants {
	
	public static void main(String[] args) {
		
		String customerId1 
			= formatCustomerNumber(AREA_NO_HAMAMATSU, 1);
		System.out.println("customerId1 : " + customerId1);
		String customerId2 
			= formatCustomerNumber(AREA_NO_TOKYO, 10);
		System.out.println("customerId2 : " + customerId2);
		
		String rank1 = formatCustomerRank(70, 91);
		System.out.println("rank1 : " + rank1);
		String rank2 = formatCustomerRank(39, 91);
		System.out.println("rank2 : " + rank2);
	}
	
	/**
	 * 顧客番号を取得
	 * @param areaCode エリアコード
	 * @param companyCode 企業コード
	 * @return 顧客番号
	 */
	public static String formatCustomerNumber(
			int areaCode,
			int companyCode) {
		
		// エリアコードからエリア識別子を割り当て
		String areaId = AREA_COMMON;
		switch (areaCode) {
		case AREA_NO_HAMAMATSU:
			// 浜松
			areaId = AREA_HAMAMATSU;
			break;
		case AREA_NO_TOKYO:
			// 東京
			areaId = AREA_TOKYO;
			break;
		default:
			// エリア共
			areaId = AREA_COMMON;
			break;
		}
		
		// エリア識別子と顧客コードをゼロパディングして返却
		return String.format("%s%03d", areaId, companyCode);
	}
	

	/**
	 * 企業情報の合計スコア、案件の質情報の合計スコアから顧客ランクを返却
	 * @param comScore 企業情報の合計スコア
	 * @param prjScore 案件の質情報の合計スコア
	 * @return 顧客ランク
	 */
	public static String formatCustomerRank(
			int comScore,
			int prjScore) {
		
		// 企業情報をランク付け
		String comRank = COM_RANK_E;
		if (comScore >= 70) {
			comRank = COM_RANK_A;
		} else if (comScore >= 60 && comScore <= 69) {
			comRank = COM_RANK_B;
		} else if (comScore >= 50 && comScore <= 59) {
			comRank = COM_RANK_C;
		} else if (comScore >= 40 && comScore <= 49) {
			comRank = COM_RANK_D;
		} else {
			comRank = COM_RANK_E;
		}
		
		// 、案件の質情報をランク付け
		String prjRank = PRJ_RANK_4;
		if (prjScore >= 91) {
			prjRank = PRJ_RANK_1;
		} else if (prjScore >= 71 && prjScore <= 90) {
			prjRank = PRJ_RANK_2;
		} else if (prjScore >= 51 && prjScore <= 70) {
			prjRank = PRJ_RANK_3;
		} else {
			prjRank = PRJ_RANK_4;
		} 
		
		return String.format("%s-%s", comRank, prjRank);
	}
	
}
