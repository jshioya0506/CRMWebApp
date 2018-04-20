package jp.co.nexus.crm.bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 顧客情報
 * @author jshioya
 *
 */
public class CustomerInfoBean {

	/** 顧客番号(エリア識別子＋顧客コード) */
	private String customerNo = "";
	/** 担当営業 */
	private String staffName = "";
	/** ランク */
	private String rank = "";
	/** 社名 */
	private String companyName = "";
	/** 住所 */
	private String postAddress = "";
	/** 担当者 */
	private String personnelName = "";
	/** 部署名 */
	private String departmentName = "";
	/** 役職 */
	private String positionName = "";
	/** 前回訪問日 */
	private String lastVisitDate = "";
	/** 関係性 */
	private String relationship = "";
	
	
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getLastVisitDate() {
		return lastVisitDate;
	}
	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
}
