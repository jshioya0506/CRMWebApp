package jp.co.nexus.crm.facade;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.springframework.ui.Model;

import jp.co.nexus.crm.db.Employee;
import jp.co.nexus.crm.db.NCCustomer;

public class StoreCustomersFacade {

	/**
	 * 顧客
	 * @param model 
	 * @param password パスワード
	 * @return
	 */
	public boolean doAction (Model model) {
		
		// DBの設定情報を取得
		ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
		ObjectContext context = cayenneRuntime.getContext();
		
		// 顧客管理テーブルから失効日が0のレコードのみを抽出
		SelectQuery query = new SelectQuery(NCCustomer.class);
		Expression exprExpireDate 
			= ExpressionFactory.matchExp(NCCustomer.LOST_YMD_PROPERTY, Integer.valueOf(0));
		query.setQualifier(exprExpireDate);
		List<NCCustomer> customers = (List<NCCustomer>)context.performQuery(query);
		if (customers == null || customers.isEmpty()) {
			return false;
		}
		
		// 顧客情報をモデルに設定
		
		
		return true;
	}
	
}
