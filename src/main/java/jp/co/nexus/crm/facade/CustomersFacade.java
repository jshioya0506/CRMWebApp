package jp.co.nexus.crm.facade;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
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

/**
 * 顧客情報のデータ処理を行うクラス
 * @author jshioya
 *
 */
public class CustomersFacade {

	/**
	 * 顧客の一覧情報を取得してモデルに設定
	 * @param model SprinｇFrameworkのモデルクラス
	 * @return 正常に処理できた場合はtrue
	 */
	public boolean doAction (Model model) {

		// DBの設定情報を取得
		ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
		ObjectContext context = cayenneRuntime.getContext();

		// 顧客情報Beanのインスタンスを生成
		CustomerListBean bean = new CustomerListBean();

		//************************************
		// １．職員テーブルの検索条件を設定
		//************************************

		// 検索条件1：失効日=0
		SelectQuery empQuery = new SelectQuery(Employee.class);
		Expression empExpr
			= ExpressionFactory.matchExp(
					Employee.LOST_YMD_PROPERTY, Integer.valueOf(0));
		empQuery.setQualifier(empExpr);

		//************************************
		// 2．職員テーブルを検索
		//************************************

		// 検索結果を担当者のコンボボックスに設定
		List<Employee> employees = (List<Employee>)context.performQuery(empQuery);
		for (Employee employee : employees) {
			// 社員番号＝社員名
			bean.getEmployees().put(
				String.valueOf(employee.getEmpNo()), employee.getName());
		}

		//************************************
		// 3.顧客管理テーブルの検索条件を設定
		//************************************

		// 検索条件1：失効日=0
		SelectQuery customerQuery = new SelectQuery(NCCustomer.class);
		Expression exprExpireDate
			= ExpressionFactory.matchExp(
					NCCustomer.LOST_YMD_PROPERTY, Integer.valueOf(0));
		customerQuery.setQualifier(exprExpireDate);

		//************************************
		// 4.顧客管理テーブルを検索
		//************************************
		List<NCCustomer> customers = (List<NCCustomer>)context.performQuery(customerQuery);

		// 検索結果を社名のコンボボックスに設定
		if (customers != null && !customers.isEmpty()) {
			for (NCCustomer customer : customers) {
				// 顧客コード
				Integer customerCode = getCustomerCode(customer);
				// 会社名
				String companyName = customer.getName();
				// 検索結果を社名のコンボボックスに設定
				bean.getCompanies().put(String.valueOf(customerCode), companyName);
			}
		}

		// 検索結果を顧客情報のモデルに設定
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
				String rank = getRank(customer);
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
				bean.getCustomers().add(infoBean);
			}
		}

		// 顧客の一覧情報をモデルへ設定
		model.addAttribute("bean", bean);

		return !customers.isEmpty();
	}

	/**
	 * 顧客情報の絞り込み検索結果をモデルに設定
	 * @param model
	 * @param staffCode
	 * @param companyCode
	 * @return
	 */
	public boolean doSearch (
			Model model,
			String staffCode,
			String companyCode) {


		// DBの設定情報を取得
		ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
		ObjectContext context = cayenneRuntime.getContext();

		// 顧客情報Beanのインスタンスを生成
		CustomerListBean bean = new CustomerListBean();

		//************************************
		// １．職員テーブルの検索条件を設定
		//************************************

		// 検索条件1：失効日=0
		SelectQuery empQuery = new SelectQuery(Employee.class);
		Expression empExpr
			= ExpressionFactory.matchExp(
					Employee.LOST_YMD_PROPERTY, Integer.valueOf(0));
		empQuery.setQualifier(empExpr);

		//************************************
		// 2．職員テーブルを検索
		//************************************

		// 検索結果を担当者のコンボボックスに設定
		List<Employee> employees = (List<Employee>)context.performQuery(empQuery);
		for (Employee employee : employees) {
			// 社員番号＝社員名
			bean.getEmployees().put(
				String.valueOf(employee.getEmpNo()), employee.getName());
		}

		//************************************
		// 3.顧客管理テーブルの検索条件を設定
		//************************************

		// 検索条件1：失効日=0
		SelectQuery customerQuery = new SelectQuery(NCCustomer.class);
		Expression exprExpireDate
			= ExpressionFactory.matchExp(
					NCCustomer.LOST_YMD_PROPERTY, Integer.valueOf(0));
		customerQuery.setQualifier(exprExpireDate);

		//************************************
		// 4.顧客管理テーブルを検索
		//************************************
		List<NCCustomer> customers = (List<NCCustomer>)context.performQuery(customerQuery);

		// 検索結果を社名のコンボボックスに設定
		if (customers != null && !customers.isEmpty()) {
			for (NCCustomer customer : customers) {
				// 顧客コード
				Integer customerCode = getCustomerCode(customer);
				// 会社名
				String companyName = customer.getName();
				// 検索結果を社名のコンボボックスに設定
				bean.getCompanies().put(String.valueOf(customerCode), companyName);
			}
		}
		//************************************
		// 5.顧客管理テーブルの検索条件を設定
		//************************************
		SelectQuery customerRefQuery = new SelectQuery(NCCustomer.class);

		// 検索条件1：失効日=0
		Expression empExprDate	= ExpressionFactory.matchExp(
				NCCustomer.LOST_YMD_PROPERTY, Integer.valueOf(0));
		customerRefQuery.setQualifier(empExprDate);

		// 検索条件2：職員コード=%入力値%　※入力値が"*"だったら条件として指定しない
		if(!("*".equals(staffCode))) {
			Expression empExprStaffCode
				= ExpressionFactory.matchExp(
						NCCustomer.EMPLOYEE_PROPERTY, Integer.parseInt(staffCode));
			customerRefQuery.andQualifier(empExprStaffCode);
		}

		// 検索条件3：顧客コード=%入力値% ※入力値が"*"だったら条件として指定しない
		if(!("*".equals(companyCode))) {

			Expression empExprCompanyCode
				= ExpressionFactory.matchDbExp(
						NCCustomer.CUSTOMERCD_PK_COLUMN, Integer.parseInt(companyCode));
			customerRefQuery.andQualifier(empExprCompanyCode);
		}
		//************************************
		// 6.顧客管理テーブルを検索
		//************************************
		System.out.println(customerRefQuery);
		List<NCCustomer> serchlistcustomers = (List<NCCustomer>)context.performQuery(customerRefQuery);

		// 検索結果を顧客情報のモデルに設定

		for (NCCustomer customer : serchlistcustomers) {
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
				String rank = getRank(customer);
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
				bean.getCustomers().add(infoBean);
			}
		}

		// 顧客の一覧情報をモデルへ設定
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
//		for (NCDivision division : divisions) {
//			ObjectId objectId = division.getObjectId();
//			Integer divisionCd
//				= (Integer)objectId
//					.getIdSnapshot()
//					.get(NCDivision.DIVISIONCD_PK_COLUMN);
//			if (divisionCd == divisionCode) {
//				return division;
//			}
//		}
		return divisions.get(0);
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

	private String getRank(NCCustomer customer) {
		// 企業ランク合計スコア
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

		return DataFormatUtil.formatCustomerRank(companyScoreSum, annkenScoreSum);
	}
}
