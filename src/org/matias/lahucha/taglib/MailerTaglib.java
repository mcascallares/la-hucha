package org.matias.lahucha.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.matias.lahucha.service.MailerService;

public class MailerTaglib extends BodyTagSupport {

	private static final long serialVersionUID = 9192730225472322124L;
	private MailerService mailerService;
	private String destinationAddress;
	private String destinationName;

	public int doAfterBody() throws JspException {
		String body = getBodyContent().getString();
		if (body != null) {
			sendMail(body);
		}
		try {
			((HttpServletResponse) pageContext.getResponse()).sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private void sendMail(String body) {
		mailerService.sendMimeMessage(body, destinationAddress, destinationName);
	}

	public void setMailerService(MailerService mailerService) {
		this.mailerService = mailerService;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

}
