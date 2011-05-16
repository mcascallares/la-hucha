package org.matias.lahucha.controller;

import org.matias.lahucha.model.FinanceAccount;
import org.matias.lahucha.service.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping("/account")
public class FinanceAccountController {

	@Autowired
	private FinanceAccountService financeAccountService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public @ModelAttribute("account") FinanceAccount create() {
		return new FinanceAccount();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("account") FinanceAccount account,
			BindingResult bindingResult) {
		account.setUserEmail(UserServiceFactory.getUserService().getCurrentUser().getEmail());
		financeAccountService.save(account);
		return new ModelAndView("redirect:/");
	}
}
