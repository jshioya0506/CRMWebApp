package jp.co.nexus.crm.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.nexus.crm.facade.AuthFacade;
import jp.co.nexus.crm.facade.CustomersFacade;
import jp.co.nexus.crm.form.LoginForm;

/**
 * 認証処理を行うコントローラ
 * @author jshioya
 *
 */
@Controller
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	/**
	 * 認証処理を行い、顧客情報を抽出したデータを顧客一覧画面へ表示
	 * @param loginForm ログインフォームの入力データ
	 * @param model SpringFrameworkのモデルクラス
	 * @return 正常終了時にcustomer_list.jspへ遷移
	 */
	@RequestMapping(value = "/auth", method = { RequestMethod.GET, RequestMethod.POST })
	public String doProcess(LoginForm loginForm, Model model) {
		
		// ユーザID（メールアドレス)
		String userId = loginForm.getUserId();
		//　パスワード
		String password = loginForm.getPassword();
		
		// ログイン認証(認証に失敗した場合はfalseが返る)
		AuthFacade authFacade = new AuthFacade();
		boolean doneLogin = authFacade.doLogin(userId, password);
		if (!doneLogin) {
			//FIXME エラー処理(ログイン画面にエラーメッセージを通知)
			return "login";
		}
		
		// 顧客テーブルからデータを抽出し、画面に表示
		CustomersFacade customerFacade = new CustomersFacade();
		customerFacade.doAction(model);
		
		return "customer_list";
	}
	
}
