package com.booj.base;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.booj.driver.Driver2;
import com.booj.driver.Driver3;
import com.booj.utilities.ScreenShot;

public abstract class BaseSuperFactory extends ScreenShot {
	
	
	//ExtWebDriver Instance = Initialize();
	


	private static Logger Log = Logger.getLogger(BaseSuperFactory.class
			.getName());

	@BeforeMethod
	public void setupBeforeTestMethod() throws Exception {
		
		//ExtWebDriver ewd = SessionManager.getInstance().setSessionFactory(new Driver3()).getNewSession();
		
		DOMConfigurator.configure("log4j.xml");
		//Driver2.getInstance();
		//driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		Driver3.newDriver().get("http://bairdwarner.com");
		Log.info("Browser Started");
		
		String sessionId = Driver2.getInstance().getSessionId();
		 System.out.println("SessionId" + sessionId);
		System.out.println("Thread id = " + Thread.currentThread().getId());
	}

	@AfterMethod
	public void tearDownAfterTestMethod() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		Driver2.tearDown();
		//WebDriverFactory.dismissAll();
		Log.info("Browser Closed");
		// Email.EmailPDFAfterSuite();
	}
	


	// test methods

	/*
	 * @AfterSuite
	 * 
	 * public void Email() { Email.EmailPDFAfterSuite(); }
	 */

	// After complete execution send pdf report by email

	/*
	 * @AfterSuite
	 * 
	 * public void EmailPDFAfterSuite(){
	 * 
	 * sendPDFReportByGMail("brenden@activewebsite.com", "$am6reRhH$",
	 * "brenden@activewebsite.com", "PDF Report", "");
	 * 
	 * }
	 *//**
	 * 
	 * Send email using java
	 * 
	 * @param from
	 * 
	 * @param pass
	 * 
	 * @param to
	 * 
	 * @param subject
	 * 
	 * @param body
	 */
	/*
	 * 
	 * private static void sendPDFReportByGMail(String from, String pass, String
	 * to, String subject, String body) {
	 * 
	 * Properties props = System.getProperties();
	 * 
	 * String host = "smtp.gmail.com";
	 * 
	 * props.put("mail.smtp.starttls.enable", "true");
	 * 
	 * props.put("mail.smtp.host", host);
	 * 
	 * props.put("mail.smtp.user", from);
	 * 
	 * props.put("mail.smtp.password", pass);
	 * 
	 * props.put("mail.smtp.port", "587");
	 * 
	 * props.put("mail.smtp.auth", "true");
	 * 
	 * Session session = Session.getDefaultInstance(props);
	 * 
	 * MimeMessage message = new MimeMessage(session);
	 * 
	 * try {
	 * 
	 * //Set from address
	 * 
	 * message.setFrom(new InternetAddress(from));
	 * 
	 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 * 
	 * //Set subject
	 * 
	 * message.setSubject(subject);
	 * 
	 * message.setText(body);
	 * 
	 * BodyPart objMessageBodyPart = new MimeBodyPart();
	 * 
	 * objMessageBodyPart.setText("Please Find The Attached Report File!");
	 * 
	 * Multipart multipart = new MimeMultipart();
	 * 
	 * multipart.addBodyPart(objMessageBodyPart);
	 * 
	 * objMessageBodyPart = new MimeBodyPart();
	 * 
	 * //Set path to the pdf report file
	 * 
	 * String filename =
	 * System.getProperty("user.dir")+"/AutomatedTestsRunReport/SmokeTestReport.pdf"
	 * ;
	 * 
	 * //Create data source to attach the file in mail
	 * 
	 * DataSource source = new FileDataSource(filename);
	 * 
	 * objMessageBodyPart.setDataHandler(new DataHandler(source));
	 * 
	 * objMessageBodyPart.setFileName(filename);
	 * 
	 * multipart.addBodyPart(objMessageBodyPart);
	 * 
	 * message.setContent(multipart);
	 * 
	 * Transport transport = session.getTransport("smtp");
	 * 
	 * transport.connect(host, from, pass);
	 * 
	 * transport.sendMessage(message, message.getAllRecipients());
	 * 
	 * transport.close();
	 * 
	 * }
	 * 
	 * catch (AddressException ae) {
	 * 
	 * ae.printStackTrace();
	 * 
	 * }
	 * 
	 * catch (MessagingException me) {
	 * 
	 * me.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */

}
