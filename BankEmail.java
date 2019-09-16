package Project_Bank;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;

class BabyOfAuthenticator extends Authenticator
{
	public PasswordAuthentication getPasswordAuthentication()
	{
		PasswordAuthentication pa = new PasswordAuthentication("simrangupta2050@gmail.com","7505164511");
		return pa;
	}
}

public class BankEmail 
{

	public  int main(String args) 
	throws Exception
	{

		Class.forName("com.mysql.jdbc.Driver");
		Connection co = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
		Statement st = co.createStatement();
		
		 String s= "not Found";
		 ResultSet rs2 = st.executeQuery("Select Password from UserInfo where Email = '"+args+"'");
		 while(rs2.next())
		 {
			 s= rs2.getString("Password");
		 }
		
		
		Properties p = new Properties();
		p.put("mail.smtp.host","smtp.gmail.com");
		p.put("mail.smtp.port","587");
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.ssl.trust","smtp.gmail.com");
		p.put("mail.debug","true");
		
		BabyOfAuthenticator baby = new BabyOfAuthenticator();
		Session ses = Session.getInstance(p, baby);
		MimeMessage message = new MimeMessage(ses);
		message.setSubject("Password of Bank email ID");
		String[] emails = {"simrangupta2012@gmail.com", args};
		

		InternetAddress[] inadrs = new 
				InternetAddress[emails.length];
		
		for(int i = 0; i < emails.length; i++)
		{
		// create object of interaddress to
		// denote email
		InternetAddress ina = 
				new InternetAddress(emails[i]);
		
		// store this object inside array
		inadrs[i] = ina;
		}
		
		// lets specify the types of RCVRS
		message.addRecipients
		(RecipientType.TO, inadrs);
		
		MimeBodyPart body1 = new MimeBodyPart();
		
		body1.setContent
		("Your Password Of Bank Email is "+s+"", 
				"text/html");
		
		MimeMultipart parts = new MimeMultipart();
		parts.addBodyPart(body1);
		
		message.setContent(parts);
		
		// send this message to the mail server
		Transport.send(message);
		
		System.out.println("Jai ho !!");
		
	
		return 1;
		
	
	}

}
