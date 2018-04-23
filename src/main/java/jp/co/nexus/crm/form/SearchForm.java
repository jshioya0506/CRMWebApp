package jp.co.nexus.crm.form;

/**
 * 検索条件の入力フォーム
 * @author jshioya
 *
 */
public class SearchForm {
	
	/** 職員コード */
	private String staffCode;
	/** 顧客コード */
	private String customerCode;
	
	/**
	 * 職員コードを取得
	 * @return 顧客コード
	 */
	public String getStaffCode() {
		return staffCode;
	}
	
	/**
	 * 職員コードを設定
	 * @param staffCode 職員コード
	 */
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	
	/**
	 * 
	 * @param customerCode
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	
}
