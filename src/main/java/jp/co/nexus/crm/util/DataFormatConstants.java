package jp.co.nexus.crm.util;

public interface DataFormatConstants {
	
	//*****************************************
	// エリア識別子
	//*****************************************
	// 東京・浜松 共通
	int AREA_NO_COMMON = 0;
	String AREA_COMMON = "C";
	// 浜松エリア
	int AREA_NO_HAMAMATSU = 1;
	String AREA_HAMAMATSU = "H";
	// 東京エリア
	int AREA_NO_TOKYO = 2;
	String AREA_TOKYO = "T";
	
	//*****************************************
	// 企業ランク
	//*****************************************
	// 70点以上
	String COM_RANK_A = "A";
	// 60~69点
	String COM_RANK_B = "B";
	// 50~59点
	String COM_RANK_C = "C";
	// 40~49点
	String COM_RANK_D = "D";
	// 30~39点
	String COM_RANK_E = "E";
	
	//*****************************************
	// 案件の質ランク
	//*****************************************
	// 91点以上
	String PRJ_RANK_1 = "1";
	// 71~90点
	String PRJ_RANK_2 = "2";
	// 51~70点
	String PRJ_RANK_3 = "3";
	// 31~50点
	String PRJ_RANK_4 = "4";
	
}
