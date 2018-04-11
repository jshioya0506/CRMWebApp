package jp.co.nexus.crm.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.nexus.crm.facade.AuthFacade;
import jp.co.nexus.crm.form.LoginForm;

/**
 * 認証処理と顧客一覧を表示するコントローラ
 * @author jshioya
 *
 */
@Controller
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	/**
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth")
	public String doProcess(LoginForm loginForm, Model model) {
		
		// ユーザID（メールアドレス)
		String userId = loginForm.getUserId();
		//　パスワード
		String password = loginForm.getPassword();
		
		// ログイン認証
		AuthFacade authFacade = new AuthFacade();
		boolean doneLogin = authFacade.doLogin(userId, password);
		if (!doneLogin) {
			return "login";
		}
		
		// 顧客一覧表示
		
		
		return "customer_list";
	}
	
}
