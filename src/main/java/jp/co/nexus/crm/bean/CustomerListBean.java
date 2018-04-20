package jp.co.nexus.crm.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 顧客一覧情報
 * @author jshioya
 *
 */
public class CustomerListBean {

	/** 社員の検索候補 */
	private Map<String, String> employees = new LinkedHashMap<String, String>();
	/** 担当先の検索候補 */
	private Map<String, String> companies = new LinkedHashMap<String, String>();
	/** 顧客一覧情報 */
	private List<CustomerInfoBean> customerInfoBeans = new ArrayList<CustomerInfoBean>();
	
	public Map<String, String> getEmployees() {
		return employees;
	}
	public void setEmployees(Map<String, String> employees) {
		this.employees = employees;
	}
	public Map<String, String> getCompanies() {
		return companies;
	}
	public void setCompanies(Map<String, String> companies) {
		this.companies = companies;
	}
	public List<CustomerInfoBean> getCustomerInfoBeans() {
		return customerInfoBeans;
	}
	public void setCustomerInfoBeans(List<CustomerInfoBean> customerInfoBeans) {
		this.customerInfoBeans = customerInfoBeans;
	}
}
