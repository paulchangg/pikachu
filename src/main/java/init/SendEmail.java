package init;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends Thread {
	
	private String email;
	private String newPW;
	
	public SendEmail(String email, String newPW) {
		super();
		this.email = email;
		this.newPW = newPW;
	}

	@Override
	public void run() {
		String host = "smtp.gmail.com";
		int port = 587;
		String from = "ntutjava013.2@gmail.com";
		String to = email;
		final String username = "ntutjava013.2@gmail.com";
		final String password = "Do!ng123";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("PIKACHU : update passowrd successfully");
			message.setText("This is your new password : [ " + newPW + " ] !!!");

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(message);

			System.out.println("寄送email結束.");
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	

	
	
	

}
