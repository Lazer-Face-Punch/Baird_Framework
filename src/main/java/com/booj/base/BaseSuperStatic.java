package com.booj.base;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.booj.driver.Driver;
import com.booj.utilities.ScreenShot;

public class BaseSuperStatic extends ScreenShot {

	private static Logger Log = Logger.getLogger(BaseSuperStatic.class.getName());
	
	@BeforeMethod
	
	public void setupBeforeTestMethod() throws Exception{
		DOMConfigurator.configure("log4j.xml");
		Driver.Initialize();
		Log.info("Browser Started");
	}
	
	
	@AfterMethod
	
	public void tearDownAfterTestMethod() throws Exception{
		DOMConfigurator.configure("log4j.xml");
		Driver.tearDown();
		Log.info("Browser Closed");
	}
	
	 //After complete execution send pdf report by email

    @AfterSuite

    public void tearDown(){

        sendPDFReportByGMail("brenden@activewebsite.com", "$am6reRhH$", "brenden@activewebsite.com", "PDF Report", "");

        }

    

    /**

     * Send email using java

     * @param from

     * @param pass

     * @param to

     * @param subject

     * @param body

     */

    private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {

Properties props = System.getProperties();

String host = "smtp.gmail.com";

props.put("mail.smtp.starttls.enable", "true");

props.put("mail.smtp.host", host);

props.put("mail.smtp.user", from);

props.put("mail.smtp.password", pass);

props.put("mail.smtp.port", "587");

props.put("mail.smtp.auth", "true");

Session session = Session.getDefaultInstance(props);

MimeMessage message = new MimeMessage(session);

try {

    //Set from address

message.setFrom(new InternetAddress(from));

message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//Set subject

message.setSubject(subject);

message.setText(body);

BodyPart objMessageBodyPart = new MimeBodyPart();

objMessageBodyPart.setText("Please Find The Attached Report File!");

Multipart multipart = new MimeMultipart();

multipart.addBodyPart(objMessageBodyPart);

objMessageBodyPart = new MimeBodyPart();

//Set path to the pdf report file

String filename = System.getProperty("user.dir")+"/AutomatedTestsRunReport/SmokeTestReport.pdf";

//Create data source to attach the file in mail

DataSource source = new FileDataSource(filename);

objMessageBodyPart.setDataHandler(new DataHandler(source));

objMessageBodyPart.setFileName(filename);

multipart.addBodyPart(objMessageBodyPart);

message.setContent(multipart);

Transport transport = session.getTransport("smtp");

transport.connect(host, from, pass);

transport.sendMessage(message, message.getAllRecipients());

transport.close();

}

catch (AddressException ae) {

ae.printStackTrace();

}

catch (MessagingException me) {

me.printStackTrace();

}

}

}  

