package jp.co.nexus.crm.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author jshioya
 *
 */
@Controller
public class CustomerNewEntryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerNewEntryController.class);
	
	/**
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/customerNewEntry", method = RequestMethod.GET)
	public String doProcess(Locale locale, Model model) {
		return "customer_register";
	}
	
}
