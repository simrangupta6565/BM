//package Project_Bank;
//
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import java.io.File;
//import java.util.Properties;
//import java.util.Scanner;
//
//import javax.mail.Authenticator;
//import javax.mail.Message.RecipientType;
//
//class BabyOfAuthenticator extends Authenticator
//{
//	public PasswordAuthentication getPasswordAuthentication()
//	{
//	// create object of PA
//	PasswordAuthentication pa = new PasswordAuthentication(""
//			+ "simrangupta6565@gmail.com","");
//			
//	
//	// return object of PA
//	return pa;
//	}
//}
//
//public class EmailSender 
//{
//	public static void main(String[] args)
//	throws Exception
//	{
//	// create object of properties in order to
//	// provide configuration info... about
//	// mail server
//	Properties p = new Properties();
//	p.put("mail.smtp.host","smtp.gmail.com");
//	p.put("mail.smtp.port","587");
//	p.put("mail.smtp.starttls.enable","true");
//	p.put("mail.smtp.auth","true");
////	p.put("mail.smtp.ssl.trust","smtp.gmail.com");
//	p.put("mail.debug","true");
//	
//	// create object of Authenticator
//	BabyOfAuthenticator baby = new 
//			BabyOfAuthenticator();
//	
//	// create object of session
//	Session ses = Session.getInstance
//			(p, baby);
//	
//	// create object of mime-message to denote
//	// actual message to be sent to mail server
//	MimeMessage message = new MimeMessage(ses);
//	
//	// provide a subject
//	message.setSubject("TEST MAIL !!");
//	
//	String[] emails = 
//	"simrangupta2012@gmail.com,simaith2018@gmail.com"
//			.split(",");
//	
//	InternetAddress[] inadrs = new 
//			InternetAddress[emails.length];
//	
//	for(int i = 0; i < emails.length; i++)
//	{
//	// create object of interaddress to
//	// denote email
//	InternetAddress ina = 
//			new InternetAddress(emails[i]);
//	
//	// store this object inside array
//	inadrs[i] = ina;
//	}
//	
//	// lets specify the types of RCVRS
//	message.addRecipients
//	(RecipientType.TO, inadrs);
//	
//	// create some body parts
//	MimeBodyPart body1 = new MimeBodyPart(),
//			body2 = new MimeBodyPart(),
//			body3 = new MimeBodyPart();
//	
//	// associate a text with body1
//	body1.setContent
//	("<p style='color : red;'>Chiththi aayi hai, aayi hai..</p>", 
//			"text/html");
//	
//	File f1 = new File("C:\\Users\\DELL\\Desktop\\wave.jpg");
//	File f2 = new File
//			("C:\\Users\\DELL\\Desktop\\AddTwoNumbers.class");
//	
//	// associate attachments with body2 and body3
//	body2.attachFile(f1);
//	body3.attachFile(f2);
//	
//	// create multipart to store body parts
//	MimeMultipart parts = new MimeMultipart();
//	
//	// store all the body parts inside multipart
//	parts.addBodyPart(body1);
//	parts.addBodyPart(body2);
//	parts.addBodyPart(body3);
//	
//	// store multipart inside message
//	message.setContent(parts);
//	
//	// send this message to the mail server
//	Transport.send(message);
//	
//	System.out.println("Jai ho !!");
//	
//	}
//}
// 