package org.matias.lahucha.service;

public interface MailerService {

	void sendMimeMessage(String messageBody, String destinationAddress, String destinationName);
}
