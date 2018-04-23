package jp.co.nexus.crm.facade;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;

import jp.co.nexus.crm.db.Employee;
import jp.co.nexus.crm.db.NCPerson;

/**
 * ログイン認証を行うクラス
 * @author jshioya
 *
 */
public class AuthFacade {

	/**
	 * 認証処理
	 * @param userId ログインID(＝メールアドレス)
	 * @param password パスワード
	 * @return 正常に処理された場合はtrue、そうでない場合はfalse
	 */
	public boolean doLogin (String userId, String password) {
		
		// DBの設定情報を取得
		ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
		ObjectContext context = cayenneRuntime.getContext();
		
		// 職員テーブルの検索条件を作成[検索条件：メールアドレスとパスワードが一致すること]
		Expression qureyExpr 
			= ExpressionFactory.matchExp(Employee.EMAIL_PROPERTY, userId);
		qureyExpr = qureyExpr.andExp(
			ExpressionFactory.matchExp(Employee.PASSWORD_PROPERTY, password));
		SelectQuery query = new SelectQuery(Employee.class);
		query.setQualifier(qureyExpr);
		
		// 従業員検索
		List<Employee> employees = (List<Employee>)context.performQuery(query);
		
		return !employees.isEmpty();
	}
	
}
