package featureToTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import configuration.ActionKeyword;
import configuration.Constants;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SiteDownMail {
	public static void siteDownMail() {
		ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);

		String senderEmail = "rohit.pandey@savemax.com";
		String senderPassword = "R0hitP@ndey";
		String recipientEmail = "rohit.pandey@savemax.com";

		long refreshInterval = 5000; // milliseconds
		int maxWaitTime = 20; // seconds
		int refreshCount = 0;

		while (true) {
			ActionKeyword.openURL(Constants.ProductionURL);

			try {
				Thread.sleep(maxWaitTime * 1000); // milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (ActionKeyword.getTitle().equals("test")) {
				System.out.println("Page loaded successfully");
				refreshCount = 0;
			} else {
				System.out.println("Page has not loaded within the specified time");
				refreshCount++;

				if (refreshCount >= 1) {
					System.out.println("Inside mailing");
					sendEmail(senderEmail, senderPassword, recipientEmail, "Site is down");
					 break;
				}
			}

			try {
				Thread.sleep(refreshInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// ActionKeyword.quiteBrowser();
	}

	private static void sendEmail(String senderEmail, String senderPassword, String recipientEmail,
			String messageContent) {
		String host = "webmail.domain.com"; //"smtp.gmail.com";
		int port = 443;//587;

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("savemax.com is down");
			message.setText(messageContent);

			// Send the email
			Transport.send(message);

			System.out.println("Email sent successfully");
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}
}