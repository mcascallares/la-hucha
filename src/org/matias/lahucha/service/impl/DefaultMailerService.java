package org.matias.lahucha.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.matias.lahucha.service.MailerService;
import org.springframework.stereotype.Service;

@Service
public class DefaultMailerService implements MailerService {

	private Properties props = new Properties();

	@Override
	public void sendMimeMessage(String messageBody, String destinationAddress, String destinationName) {

		Session session = Session.getDefaultInstance(props, null);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@la-hucha.appspotmail.com", "La Hucha"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationAddress,
					destinationName));
			message.setSubject("Balance report");

			Multipart multiPart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(messageBody, "text/html");
			multiPart.addBodyPart(htmlPart);
			message.setContent(multiPart);

			Transport.send(message);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
