package emf_workflow_project;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailSenderService {
	
	private String smtpHost;
	private int smtpPort;
	private String username;
	private String password;
	private String senderEmail;
	
	
	public MailSenderService(String username, String password, String senderEmail) {
		super();
		this.smtpHost = "smtp.example.com";
		this.smtpPort = 587;
		this.username = username;
		this.password = password;
		this.senderEmail = senderEmail;
	}
	
	public void sendEmail(String recipientEmail, String subject, String body) {
        // SMTP configuration
        java.util.Properties properties = new java.util.Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // create an Authentication Session
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create the new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Email successfully sent.");
        } catch (MessagingException ex) {
            System.out.println("Error during the mail send: " + ex.getMessage());
        }
	}
	
 
}

