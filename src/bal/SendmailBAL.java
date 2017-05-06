/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;
import java.util.Date;
import java.util.Properties;
 



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import connection.Connect;
/**
 *
 * @author waseem
 */
public class SendmailBAL {

	final static Logger logger = Logger.getLogger(SendmailBAL.class);
	
	
    public static void sendEmail(String password,String email){
        System.out.println(password+" "+email);
    final String username = "noornizamenergy@gmail.com";
		final String newpassword = "noor123_";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, newpassword);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Your New password ");
			message.setText("Dear User here is it your new password,"
				+ "\n\n No spam to my email, please!"
                                + "new password:"+password);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
    }
    
    
    public static void sendEmailRegister(String email,String password,String name,String position){
        final String username = "noornizamenergy@gmail.com";
		final String newpassword = "noor123_";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, newpassword);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Acount Registration ");
			message.setText("Dear,"+name+ "\n\n Your acount has been Registered Successfully as ,"+position
				+ "\n\n Your Login id: "+email
                                + " \n\n Password:"+password+"\n\n Regards \n\n Team Noor Nizam Energy");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
        
    
    
    }
    
    public static void sendEmailRegisterSolarPayGo(String email,String password,String name,String position){
        final String username = "support@solarpaygo.com";
		final String newpassword = "support@123";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.host", "mocha6007.mochahost.com");
		props.put("mail.smtp.port", "2525");
		
		System.out.println("at port 2525");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, newpassword);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Acount Registration ");
			message.setText("Dear,"+name+ "\n\n Your acount has been Registered Successfully as ,"+position
				+ "\n\n Your Login id: "+email
                                + " \n\n Password:"+password+"\n\n Regards \n\n Team Noor Nizam Energy");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }    
    
    public static void sendEmailForNewPassword(String email,String password){
        final String username = "support@solarpaygo.com";
		final String newpassword = "support@123";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.host", "mocha6007.mochahost.com");
		props.put("mail.smtp.port", "2525");
		
		System.out.println("at port 2525");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, newpassword);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Acount Registration ");
			message.setText("Dear User, Your new Password is "+password);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    
    public static String sendCode(String email){
    	String code = ""+(int)(Math.random()*10000);
    	//String code
    	if(code.length()==1){
    		code="000"+code;
    	}else if(code.length()==2){
    		code="00"+code;
    	}else if(code.length()==3){
    		code="0"+code;
    	}
        final String username = "support@solarpaygo.com";
		final String newpassword = "support@123";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.host", "mocha6007.mochahost.com");
		props.put("mail.smtp.port", "2525");
		
		System.out.println("at port 2525");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, newpassword);
			}
		  });
 
		try {
			
		//	String code = "3214";
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Generated Code ");
			message.setText(code);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
        return code;   
    }
    
    public static void Nosms_mail(String email){
    	final String username = "support@solarpaygo.com";
		final String newpassword = "support@123";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.host", "mocha6007.mochahost.com");
		props.put("mail.smtp.port", "2525");
		
		System.out.println("at port 2525");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, newpassword);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("saad.hamid@mobilink.net"));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("javed@nizamenergy.com"));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("saad.hamid@mobilink.net"));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("saad@nizamenergy.com"));
			
			
			message.setSubject("No Moblink SMS service  ");
			Date date=new Date();
			message.setText("Dear Team, \n Moblink SMS services is not working due to some problem , since  "+date+"\n\n\n\n Regards \n Team SPG");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    public static void main(String[] args) {
    	String r = ""+(int)(Math.random()*10000);
    	//String code
    	if(r.length()==1){
    		r="000"+r;
    	}else if(r.length()==2){
    		r="00"+r;
    	}else if(r.length()==3){
    		r="0"+r;
    	}
    	System.out.println(r);
    	
		/*final String username = "noornizamenergy@gmail.com";
		final String password = "noor123_";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("waseem.saeed415@yahoo.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			logger.error("",e);
			throw new RuntimeException(e);
		}*/
    	//sendCode("dnshraza2@gmail.com");
	}
    
    
}
