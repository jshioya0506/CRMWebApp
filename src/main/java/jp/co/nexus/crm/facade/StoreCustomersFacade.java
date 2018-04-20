package jp.co.nexus.crm.facade;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.Ordering;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.query.SortOrder;
import org.springframework.ui.Model;

import jp.co.nexus.crm.bean.CustomerInfoBean;
import jp.co.nexus.crm.bean.CustomerListBean;
import jp.co.nexus.crm.db.Area;
import jp.co.nexus.crm.db.Employee;
import jp.co.nexus.crm.db.NCCalldoc;
import jp.co.nexus.crm.db.NCCustomer;
import jp.co.nexus.crm.db.NCDivision;
import jp.co.nexus.crm.db.NCPerson;
import jp.co.nexus.crm.util.DataFormatUtil;

public class StoreCustomersFacade {

	/**
	 * 顧客
	 * @param model
	 * @return
	 */
	public boolean doAction (Model model) {
		
		// DBの設定情報を取得
		ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
		ObjectContext context = cayenneRuntime.getContext();
		
		CustomerListBean bean = new CustomerListBean();
		
		// 営業担当者の全レコードを抽出
		SelectQuery empQuery = new SelectQuery(Employee.class);
		Expression empExpr 
			= ExpressionFactory.matchExp(
					Employee.LOST_YMD_PROPERTY, Integer.valueOf(0));
		empQuery.setQualifier(empExpr);
		List<Employee> employees = (List<Employee>)context.performQuery(empQuery);
		for (Employee employee : employees) {
			// 従業員データをモデルに設定
			bean.getEmployees().put(
				String.valueOf(employee.getEmpNo()), employee.getName());
		}
		
		// 顧客管理テーブルから全レコードを抽出
		SelectQuery customerQuery = new SelectQuery(NCCustomer.class);
		Expression exprExpireDate 
			= ExpressionFactory.matchExp(
					NCCustomer.LOST_YMD_PROPERTY, Integer.valueOf(0));
		customerQuery.setQualifier(exprExpireDate);
		List<NCCustomer> customers = (List<NCCustomer>)context.performQuery(customerQuery);
		if (customers == null || customers.isEmpty()) {
			return false;
		}
		
		// 顧客情報をモデルに設定
		for (NCCustomer customer : customers) {
			CustomerInfoBean infoBean = new CustomerInfoBean();
			
			// エリア情報
			Area area = customer.getArea();
			
			// 営業担当情報
			Employee employee = customer.getEmployee();
			
			// 顧客コード
			Integer customerCode = getCustomerCode(customer);
			
			//　担当者情報から顧客情報を作成して、一覧情報として追加する
			List<NCPerson> persons = getPersons(context, customerCode);
			for (NCPerson person : persons) {
				// 顧客番号[エリアコード＋顧客コード]
				String customerNo 
					= DataFormatUtil.formatCustomerNumber(
						area.getAreaCd(), customerCode);
				infoBean.setCustomerNo(customerNo);
				// 担当営業 
				infoBean.setStaffName(employee.getName());
				// ランク
				String rank = DataFormatUtil.formatCustomerRank(comScore, prjScore);
				infoBean.setRank(rank);
				// 社名
				infoBean.setCompanyName(customer.getName());
				// 住所
				infoBean.setPostAddress(customer.getAddress());
				// 担当者
				infoBean.setPersonnelName(person.getName());
				
				// 部署名
				NCDivision division = getDivision(
					context,
					customerCode, 
					person.getDivisioncd());
				infoBean.setDepartmentName(division.getName());
				
				//TODO 役職(テーブルのカラムがないので表示保留)
				infoBean.setPositionName("");
				
				// 前回訪問日
				NCCalldoc calldoc = getLastVisitInfo(
					context,
					customerCode, 
					person.getDivisioncd());
				String lastVisitDate 
					= DataFormatUtil.formatDate(
						String.valueOf(calldoc.getCallYmd()));
				infoBean.setLastVisitDate(lastVisitDate);
				
				// 
				//TODO 関係性(テーブルのカラムがないので表示保留)
				infoBean.setRelationship("");
				
				// 顧客の一覧情報として追加
				bean.getCustomerInfoBeans().add(infoBean);
			}
		}
		
		model.addAttribute("bean", bean);
		
		return true;
	}
	
	private Integer getCustomerCode(NCCustomer customer) {
		// 顧客番号
		ObjectId objectId = customer.getObjectId();
		if (objectId == null || objectId.isTemporary()) {
			return null;
		}
		
		Integer customerCd 
			= (Integer)objectId
				.getIdSnapshot()
					.get(NCCustomer.CUSTOMERCD_PK_COLUMN); 
		return customerCd;
	}
	
	private List<NCPerson> getPersons(
			ObjectContext context,
			int customerCd) {
		SelectQuery personQuery = new SelectQuery(NCPerson.class);
		
		// 検索条件[]
		Expression conditionExpr 
			= ExpressionFactory.matchExp(
					NCPerson.LOST_YMD_PROPERTY, Integer.valueOf(0));
		conditionExpr = conditionExpr.andExp(ExpressionFactory.matchExp(
				NCPerson.CUSTOMERCD_PROPERTY, Integer.valueOf(customerCd)));
		
		personQuery.setQualifier(conditionExpr);
		List<NCPerson> persons = (List<NCPerson>)context.performQuery(personQuery);
		return persons;
	}
	
	private NCDivision getDivision(
			ObjectContext context,
			int customerCd,
			int divisionCode) {
		SelectQuery divisionQuery = new SelectQuery(NCDivision.class);
		Expression conditionExpr 
			= ExpressionFactory.matchExp(
				NCPerson.LOST_YMD_PROPERTY, Integer.valueOf(0));
		conditionExpr = conditionExpr.andExp(
			ExpressionFactory.matchExp(
					NCDivision.CUSTOMERCD_PROPERTY, 
					Integer.valueOf(customerCd)));
		divisionQuery.setQualifier(conditionExpr);
		List<NCDivision> divisions 
			= (List<NCDivision>)context.performQuery(divisionQuery);
		for (NCDivision division : divisions) {
			ObjectId objectId = division.getObjectId();
			Integer divisionCd 
				= (Integer)objectId
					.getIdSnapshot()
					.get(NCDivision.DIVISIONCD_PK_COLUMN); 
			if (divisionCd == divisionCode) {
				return division;
			}
		}
		return null;
	}
	
	private NCCalldoc getLastVisitInfo(
			ObjectContext context,
			int customerCd,
			int divisionCode) {
		SelectQuery calldocQuery = new SelectQuery(NCCalldoc.class);
		
		// 検索条件[失効日が0 かつ 顧客コード かつ 部署コード]
		Expression conditionExpr 
			= ExpressionFactory.matchExp(
					NCCalldoc.LOST_YMD_PROPERTY, Integer.valueOf(0));
		conditionExpr = conditionExpr.andExp(
			ExpressionFactory.matchExp(
				NCCalldoc.CUSTOMER_CD_PROPERTY, 
				Integer.valueOf(customerCd)));
		conditionExpr = conditionExpr.andExp(
			ExpressionFactory.matchExp(
				NCCalldoc.DIVISION_CD_PROPERTY, 
				Integer.valueOf(divisionCode)));
		calldocQuery.setQualifier(conditionExpr);
		
		// ソートオーダー(訪問日の降順)
		calldocQuery.addOrdering(
			NCCalldoc.CALL_YMD_PROPERTY, SortOrder.DESCENDING);
		
		List<NCCalldoc> calldocs 
			= (List<NCCalldoc>)context.performQuery(calldocQuery);
		if (!calldocs.isEmpty()) {
			return calldocs.get(0);
		} else {
			return null;
		}
	}
}
