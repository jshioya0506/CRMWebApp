package jp.co.nexus.crm.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.nexus.crm.form.LoginForm;

/**
 * 
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
		
		// 
		
		return "customer_list";
	}
	
}
