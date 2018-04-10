package jp.co.nexus.crm.facade;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;

import jp.co.nexus.crm.db.Employee;

public class AuthFacade {

	/**
	 * 認証処理
	 * @param userId ログインID(メールアドレス)
	 * @param password パスワード
	 * @return
	 */
	public boolean doLogin (String userId, String password) {
		
		// DBの設定情報を取得
		ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
		ObjectContext context = cayenneRuntime.getContext();
		
		Expression exprUserId 
			= ExpressionFactory.matchExp(Employee.EMAIL_PROPERTY, userId);
		Expression exprPassword 
			= ExpressionFactory.matchExp(Employee.PASSWORD_PROPERTY, password);
		
		SelectQuery query = new SelectQuery(Employee.class);
		query.andQualifier(exprUserId);
		query.andQualifier(exprPassword);
		
		List<Employee> employees = (List<Employee>)context.performQuery(query);
		
		return !employees.isEmpty();
	}
	
}
