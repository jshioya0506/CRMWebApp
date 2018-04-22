package jp.co.nexus.crm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.nexus.crm.db.NCCustomer;

/**
 * 画面表示用のデータフォーマットクラス
 * 
 * @author jshioya
 *
 */
public final class DataFormatUtil implements DataFormatConstants {

	public static void main(String[] args) {

		String customerId1 = formatCustomerNumber(AREA_NO_HAMAMATSU, 1);
		System.out.println("customerId1 : " + customerId1);
		String customerId2 = formatCustomerNumber(AREA_NO_TOKYO, 10);
		System.out.println("customerId2 : " + customerId2);

		String rank1 = formatCustomerRank(70, 91);
		System.out.println("rank1 : " + rank1);
		String rank2 = formatCustomerRank(39, 91);
		System.out.println("rank2 : " + rank2);
	}

	/**
	 * 顧客番号を取得
	 * 
	 * @param areaCode
	 *            エリアコード
	 * @param companyCode
	 *            企業コード
	 * @return 顧客番号
	 */
	public static String formatCustomerNumber(int areaCode, int companyCode) {

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
	public static String formatCustomerRank(NCCustomer customer) {
		
		int companyScoreSum = 0;
		
		// 売上
		companyScoreSum += customer.getSales();
		// 人数
		companyScoreSum += customer.getNumberOfPeople();
		// 規模(バックボーン)
		companyScoreSum += customer.getBackbone();
		// 事業内容
		companyScoreSum += customer.getWorkInfo();
		// 商流
		companyScoreSum += customer.getSalesChannels();
		
		String companyRank = COM_RANK_E;
		if (companyScoreSum >= 70) {
			companyRank = COM_RANK_A;
		} else if (companyScoreSum >= 60 && companyScoreSum <= 69) {
			companyRank = COM_RANK_B;
		} else if (companyScoreSum >= 50 && companyScoreSum <= 59) {
			companyRank = COM_RANK_C;
		} else if (companyScoreSum >= 40 && companyScoreSum <= 49) {
			companyRank = COM_RANK_D;
		} else {
			companyRank = COM_RANK_E;
		}
		
		// 案件の質をランク付け
		int annkenScoreSum = 0;
		
		// 要員提案(personnelProposed)
		annkenScoreSum += customer.getPersonnelProposed();
		// 単価(unitPrice)
		annkenScoreSum += customer.getUnitPrice();
		// 案件内容(ankenInfo)
		annkenScoreSum += customer.getAnkenInfo();
		// 若手受入れ(receiving)
		annkenScoreSum += customer.getReceiving();
		// 決裁権(approvalRights)
		annkenScoreSum += customer.getApprovalRights();
		// 担当窓口(staff)
		annkenScoreSum += customer.getStaff();
		// 担当者との関係性(relationship)
		annkenScoreSum += customer.getRelationship();
		
		String annkenRank = PRJ_RANK_4;
		if (annkenScoreSum >= 91) {
			annkenRank = PRJ_RANK_1;
		} else if (annkenScoreSum >= 71 && annkenScoreSum <= 90) {
			annkenRank = PRJ_RANK_2;
		} else if (annkenScoreSum >= 51 && annkenScoreSum <= 70) {
			annkenRank = PRJ_RANK_3;
		} else {
			annkenRank = PRJ_RANK_4;
		} 
		
		return String.format("%s-%s", companyRank, annkenRank);
	}
	
	/**
	 * 企業情報の合計スコア、案件の質情報の合計スコアから顧客ランクを返却
	 * @param comScore 企業情報の合計スコア
	 * @param prjScore 案件の質情報の合計スコア
	 * @return 顧客ランク
	 */
	public static String formatCustomerRank(int companyScoreSum, int annkenScoreSum) {
		
		// 企業情報をランク付け
		String companyRank = COM_RANK_E;
		if (companyScoreSum >= 70) {
			companyRank = COM_RANK_A;
		} else if (companyScoreSum >= 60 && companyScoreSum <= 69) {
			companyRank = COM_RANK_B;
		} else if (companyScoreSum >= 50 && companyScoreSum <= 59) {
			companyRank = COM_RANK_C;
		} else if (companyScoreSum >= 40 && companyScoreSum <= 49) {
			companyRank = COM_RANK_D;
		} else {
			companyRank = COM_RANK_E;
		}
		
		// 案件の質をランク付け
		String annkenRank = PRJ_RANK_4;
		if (annkenScoreSum >= 91) {
			annkenRank = PRJ_RANK_1;
		} else if (annkenScoreSum >= 71 && annkenScoreSum <= 90) {
			annkenRank = PRJ_RANK_2;
		} else if (annkenScoreSum >= 51 && annkenScoreSum <= 70) {
			annkenRank = PRJ_RANK_3;
		} else {
			annkenRank = PRJ_RANK_4;
		} 
		
		return String.format("%s-%s", companyRank, annkenRank);
	}

	public static String formatDate(String ymd) {
		String formatYMD = null;

		SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateInstance();
		formatter.applyPattern("yyyyMMdd");
		try {
			Date date = formatter.parse(ymd);
			formatter.applyPattern("yyyy/MM/dd");
			formatYMD = formatter.format(date);
		} catch (ParseException ex) {

		}
		return formatYMD;
	}

}
