package org.matias.lahucha.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.matias.lahucha.dto.Report;
import org.matias.lahucha.form.ReportForm;
import org.matias.lahucha.service.MailerService;
import org.matias.lahucha.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private MailerService mailerService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ReportForm create() {
		return new ReportForm();
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public ModelAndView show(@ModelAttribute ReportForm reportForm) {
		// always from the first day
		Calendar calendarFrom = new GregorianCalendar(reportForm.getFromYear(),
				reportForm.getFromMonth(), 1);
		Calendar calendarUntil = new GregorianCalendar(reportForm.getUntilYear(),
				reportForm.getUntilMonth(), 1);

		// input trasverse are also supported
		Date from, until;
		if (calendarFrom.after(calendarUntil)) {
			from = calendarUntil.getTime();
			until = calendarFrom.getTime();
		} else {
			from = calendarFrom.getTime();
			until = calendarUntil.getTime();
		}

		Report report = reportService.generate(UserServiceFactory.getUserService().getCurrentUser()
				.getEmail(), UserServiceFactory.getUserService().getCurrentUser().getNickname(),
				from, until);
		return new ModelAndView().addObject("report", report).addObject("mailerService",
				mailerService);
	}

	public MailerService getMailerService() {
		return mailerService;
	}
}
