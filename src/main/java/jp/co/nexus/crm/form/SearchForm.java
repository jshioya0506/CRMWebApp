package jp.co.nexus.crm.form;

/**
 * 検索条件の入力フォーム
 * @author jshioya
 *
 */
public class SearchForm {

	/** 職員コード */
	private String staffCode;
	/**  */
	//private String customerCode;
	/** 顧客コード */
	private String companies;

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
	//public String getCustomerCode() {
	//	return customerCode;
	//}

	/**
	 *
	 * @param customerCode
	 */
	//public void setCustomerCode(String customerCode) {
	//	this.customerCode = customerCode;
	//}

	/**
	 * 顧客コードを取得
	 * @return 顧客コード
	 */
	public String getcompanies() {
		return companies;
	}

	/**
	 * 顧客コードを設定
	 * @param companies 顧客コード
	 */
	public void setcompanies(String companies) {
		this.companies = companies;
	}


}
