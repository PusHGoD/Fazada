package com.fazada.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author HuanPM Custom mail handler for local email
 */
public class CustomMailHandler {

	/**
	 * @param from
	 * @param to
	 * @param username
	 * @param password
	 */
	public void sendAddStaffMail(String from, String to, String username, String password) {
		if (from != null && to != null && password != null) {

			// Assuming you are sending email from localhost
			String host = "localhost";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);

			// Get the default Session object.

			javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);
			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Registeration");

				// Create the message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();

				// Fill the message
				messageBodyPart.setText(
						"Hi user,<br/><br/>You has been assigned new account on Fazada by the administrator.<br/><br/>Your username is: "
								+ username + "<br/>" + "Your password is: " + password
								+ "<br/><br/>Please click this link to login to Fazada and change password:<br/> <a href='http://localhost:8080/fazada/main'>Fazada home page</a><br/><br/>Best regards,<br/><br/>Fazada team",
						"UTF-8", "html");

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Send the complete message parts
				message.setContent(multipart);

				// Send message
				Transport.send(message);
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
	}

	/**
	 * @param from
	 * @param to
	 * @param username
	 * @param password
	 */
	public void sendSignupMail(String from, String to, String username) {
		if (from != null && to != null) {

			// Assuming you are sending email from localhost
			String host = "localhost";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);

			// Get the default Session object.

			javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);
			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Registeration");

				// Create the message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();

				// Fill the message
				messageBodyPart.setText(
						"Hi " + username
								+ ",<br/><br/>Your account has been registered on Fazada.<br/>Please click this link to activate your account:<br/> <a href='http://localhost:8080/fazada/activate?userName="
								+ username + "'>Activate your account</a><br/><br/>Best regards,<br/><br/>Fazada team",
						"UTF-8", "html");

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Send the complete message parts
				message.setContent(multipart);

				// Send message
				Transport.send(message);
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
	}

	/**
	 * @param from
	 * @param to
	 * @param password
	 */
	public void sendResetMail(String from, String to, String password) {
		if (from != null && to != null && password != null) {

			// Assuming you are sending email from localhost
			String host = "localhost";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);

			// Get the default Session object.

			javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);
			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Reset Password");

				// Create the message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();

				// Fill the message
				messageBodyPart.setText(
						"Your password has been resetted.<br/>The new password is: " + password
								+ ".<br/>Please click <a href='http://localhost:8080/fazada/main'>this</a> to login to our site and change password.<br/><br/>Best regards,<br/><br/>Fazada team",
						"UTF-8", "html");

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Send the complete message parts
				message.setContent(multipart);

				// Send message
				Transport.send(message);
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
	}
}
