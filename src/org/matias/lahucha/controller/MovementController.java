package org.matias.lahucha.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.matias.lahucha.form.MovementForm;
import org.matias.lahucha.model.Movement;
import org.matias.lahucha.service.FinanceAccountService;
import org.matias.lahucha.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping("/movement")
public class MovementController {

	@Autowired
	private FinanceAccountService financeAccountService;

	@Autowired
	private MovementService movementService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView create() {
		MovementForm movementForm = new MovementForm();
		ModelAndView mav = new ModelAndView();
		mav.addObject("movementForm", movementForm);
		mav.addObject("accounts", financeAccountService.loadByUserEmail(UserServiceFactory.getUserService()
						.getCurrentUser().getEmail()));
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute MovementForm movementForm) {
		Movement movement = new Movement();
		movement.setFinanceAccountId(movementForm.getFinnanceAccountId());
		movement.setAmount(movementForm.getAmount());
		movement.setNotes(movementForm.getNotes());
		Calendar calendar = new GregorianCalendar(movementForm.getCreationYear(), movementForm.getCreationMonth(), 
				movementForm.getCreationDate());
		movement.setCreation(calendar.getTime());
		movementService.save(movement);
		return new ModelAndView("redirect:/");
	}
}
