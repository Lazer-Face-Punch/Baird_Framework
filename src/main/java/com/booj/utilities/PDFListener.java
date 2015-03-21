package com.booj.utilities;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.booj.driver.Driver;
import com.dropbox.core.DbxException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * PDFListener
 */
public class PDFListener implements ITestListener {
	protected URL url = null;
	/**
	 * Document
	 */
	private Document document = null;

	/**
	 * PdfPTables
	 */
	PdfPTable statTable = null, chartTable = null, successTable = null,
			failTable = null, skipTable = null;

	/**
	 * throwableMap
	 */
	private HashMap<Integer, Throwable> throwableMap = null;

	/**
	 * nbExceptions
	 */
	private int nbExceptions = 0;

	/**
	 * nbTotalTime
	 */
	private long nbTotalTime = 0;

	FileOutputStream fop = null;
	static File file;

	/**
	 * PDFListener
	 */
	public PDFListener() {
		log("PDFListener()");

		this.document = new Document();
		this.throwableMap = new HashMap<Integer, Throwable>();
	}

	/**
	 * getResults(ITestResult result)
	 */
	public ITestResult getResults(ITestResult result) {
		return result;
	}

	/**
	 * @see org.testng.ITestListener#onTestSuccess(com.beust.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		log("onTestSuccess(" + result + ")");

		if (successTable == null) {
			this.successTable = new PdfPTable(
					new float[] { .3f, .3f, .1f, .3f });

		}

		Paragraph p = new Paragraph("PASSED TESTS  -"
				+ result.getTestClass().toString(), new Font(Font.FontFamily.TIMES_ROMAN,
				Font.DEFAULTSIZE, Font.BOLD));
		p.setAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.GREEN);
		this.successTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("Class"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Method"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Time (ms)"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Status"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.successTable.addCell(cell);

		cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(result.getMethod().toString()));
		this.successTable.addCell(cell);

		long duration = result.getEndMillis() - result.getStartMillis();
		nbTotalTime += duration;
		cell = new PdfPCell(new Paragraph("" + duration));

		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("PASSED"));
		this.successTable.addCell(cell);
		// Change messages to steps for use in GUI based WebDriver tests.
		// The messages are sent via the org.Testng.Report.log method
		p = new Paragraph("TEST MESSAGES");
		new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
		p.setAlignment(Element.ALIGN_CENTER);
		cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.successTable.addCell(cell);
		// p = new Paragraph("" + Reporter.getOutput());
		p.setAlignment(Element.ALIGN_LEFT);
		List unorderedList = new List(List.UNORDERED);
		for (String item : Reporter.getOutput(result)) {
			unorderedList.add(item);
		}
		cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.addElement(unorderedList);
		this.successTable.addCell(cell);
	}

	/**
	 * @see com.beust.testng.ITestListener#onTestFailure(com.beust.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		log("onTestFailure(" + result + ")");
		PropertyFileManager pfm = null;
		try {
			pfm = new PropertyFileManager();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		/*String file = System.getProperty("user.dir") + "\\" + "screenshot"
				+ (new Random().nextInt()) + ".png";*/
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM_dd_yyyy_HHmm");
		Date resultdate = new Date(yourmilliseconds);
		//String screenshotFile = "Screenshot" /*+ "_" + sdf.format(resultdate) */+ ".png";
		String screenshotFile = result.getName() + "_" + sdf.format(resultdate) + (new Random().nextInt())+".png";
		// String random = "Screenshot"+(new Random().nextInt())+".png";
		String file = (pfm.getProperty("picDir") + "\\" + screenshotFile);
		System.out.println(file);
		try {
			ScreenShot.takeSnapShot(Driver.getInstance(), file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
		try {
			DropBox.main(screenshotFile);
		} catch (IOException | DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = DropBox.getSharedUrl();
	
		if (this.failTable == null) {
			this.failTable = new PdfPTable(new float[] { .3f, .3f, .1f, .3f });
			this.failTable.setTotalWidth(20f);

		}

		Paragraph p = new Paragraph("FAILED TEST -"
				+ result.getTestClass().toString(), new Font(Font.FontFamily.TIMES_ROMAN,
				Font.DEFAULTSIZE, Font.BOLD));
		p.setAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.RED);
		this.failTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("Class"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Method"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Time (ms)"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Status"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.failTable.addCell(cell);

		cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(result.getMethod().toString()));
		this.failTable.addCell(cell);

		long duration = result.getEndMillis() - result.getStartMillis();
		nbTotalTime += duration;
		cell = new PdfPCell(new Paragraph("" + duration));

		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("FAILED"));
		this.failTable.addCell(cell);
		p = new Paragraph("Exception", new Font(Font.FontFamily.TIMES_ROMAN,
				Font.DEFAULTSIZE, Font.BOLD));
		p.setAlignment(Element.ALIGN_CENTER);
		cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.failTable.addCell(cell);

		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
			this.nbExceptions++;

			Chunk imdb = new Chunk("[SCREEN SHOT]", new Font(Font.FontFamily.TIMES_ROMAN,
					Font.DEFAULTSIZE, Font.UNDERLINE));
			String directory = System.getProperty("user.dir");
			System.out.println(directory);

			/*
			 * if (directory.equals(
			 * "C:\\Users\\brenden\\workspace\\Maven_Testing\\baird-framework"
			 * )){ System.out.println("Local"); //URL url = null; try { url =
			 * new URL("file","localhost/",directory); System.out.println(url);
			 * 
			 * } catch (MalformedURLException e) { //TODO Auto-generated catch
			 * block e.printStackTrace(); } }else if (directory.equals(
			 * "C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace")){
			 * System.out.println("Jenkins"); String replaced = file.replace(
			 * "C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace",
			 * "/job/PDF test/ws/"); //URL url = null; try { url = new
			 * URL("http", "localhost", 8080, replaced);
			 * System.out.println(url); } catch (MalformedURLException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 * 
			 * }else{ System.out.println("User Directory Option Not Found"); }
			 */

			/*
			 * String replaced = file.replace(
			 * "C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace\\",
			 * "/job/PDF test/ws/"); //URI uri = null; URL url = null; try { url
			 * = new URL("http", "localhost", 8080, replaced); } catch
			 * (MalformedURLException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			// URL url = null;
			imdb.setAction(new PdfAction(url /* "file:///"+ url.toString()*/));
			System.out.println(url);
			// "C:\\Users\\brenden\\workspace\\Maven_Testing\\baird-framework\\"
			// "C:\\Users\\brenden\\.jenkins\\"

			Paragraph excep = new Paragraph(throwable.toString());
			excep.add(imdb);
			new Chunk(throwable.toString(), new Font(Font.FontFamily.TIMES_ROMAN,
					Font.DEFAULTSIZE, Font.UNDERLINE)).setLocalGoto(""
					+ throwable.hashCode());
			p.setAlignment(Element.ALIGN_LEFT);
			cell = new PdfPCell(excep);
			cell.setColspan(4);
			this.failTable.addCell(cell);
		}

		p = new Paragraph("TEST STEPS", new Font(Font.FontFamily.TIMES_ROMAN,
				Font.DEFAULTSIZE, Font.BOLD));
		p.setAlignment(Element.ALIGN_CENTER);
		cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.failTable.addCell(cell);
		p = new Paragraph("" + Reporter.getOutput());
		p.setAlignment(Element.ALIGN_LEFT);
		List unorderedList = new List(List.UNORDERED);
		for (String item : Reporter.getOutput(result)) {
			unorderedList.add(item);
		}
		cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.addElement(unorderedList);
		this.failTable.addCell(cell);
	}

	/**
	 * @see com.beust.testng.ITestListener#onTestSkipped(com.beust.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		log("onTestSkipped(" + result + ")");
		if (this.skipTable == null) {
			this.skipTable = new PdfPTable(new float[] { .3f, .3f, .1f, .3f });
			this.skipTable.setTotalWidth(20f);
		}

		Paragraph p = new Paragraph("SKIPPED TESTS  -"
				+ result.getMethod().getDescription(), new Font(
				Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD));
		p.setAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.YELLOW);
		this.skipTable.addCell(cell);

		cell = new PdfPCell(new Paragraph(result.getTestClass().toString()
				+ "." + result.getMethod()));
		cell.setColspan(4);
		this.skipTable.addCell(cell);
	}

	/**
	 * @see com.beust.testng.ITestListener#onStart(com.beust.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext context) {
		log("onStart(" + context + ")");
		file = new File(System.getProperty("user.dir")
				+ "/AutomatedTestsRunReport/SmokeTestReport.pdf");
		/* file = new File("user.dir"); */
		// file = new
		// File("C:/Users/brenden/workspace/Maven_Testing/baird-framework/AutomatedTestsRunReport/SmokeTestReport.pdf");
		// file = new
		// File("C:/Users/brenden/.jenkins/jobs/PDF test/workspace/AutomatedTestsRunReport/SmokeTestReport.pdf");
		System.out.println(file);
		// if file doesn't exists, then create it
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PdfWriter.getInstance(this.document, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.document.open();

		Paragraph p = new Paragraph(context.getName() + " TESTNG RESULTS",
				FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLUE));

		try {
			this.document.add(p);
			this.document.add(new Paragraph(new Date().toString()));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * @see com.beust.testng.ITestListener#onFinish(com.beust.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
		log("onFinish(" + context + ")");

		if (statTable == null) {
			this.statTable = new PdfPTable(new float[] { .3f, .2f, .2f, .2f,
					.3f });
		}

		if (chartTable == null) {
			this.chartTable = new PdfPTable(new float[] { .3f, .3f, .1f, .3f });
		}

		Paragraph p = new Paragraph("STATISTICS", new Font(Font.FontFamily.TIMES_ROMAN,
				Font.DEFAULTSIZE, Font.BOLD));
		p.setAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell(p);
		cell.setColspan(5);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.statTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("Passed"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Skipped"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Failed"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Percent"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Total Time"));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.statTable.addCell(cell);
		int passed = context.getPassedTests().size();
		int skipped = context.getSkippedTests().size();
		int failed = context.getFailedTests().size();
		double total = passed + skipped + failed;
		double percent = (passed / total) * 100;
		cell = new PdfPCell(new Paragraph("" + passed));
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + skipped));
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + failed));
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + percent));
		this.statTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + nbTotalTime));
		this.statTable.addCell(cell);

		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("Failed", failed);
		dataSet.setValue("Skipped", skipped);
		dataSet.setValue("Passed", passed);
		p = new Paragraph("Data Chart", new Font(Font.FontFamily.TIMES_ROMAN,
				Font.DEFAULTSIZE, Font.BOLD));
		p.setAlignment(Element.ALIGN_CENTER);
		cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		this.chartTable.addCell(cell);

		JFreeChart chart = ChartFactory.createPieChart3D("Test Success Rate",
				dataSet, true, true, false);

		java.awt.Image originalImage = chart.createBufferedImage(500, 300);
		com.itextpdf.text.Image image1 = null;
		try {
			image1 = com.itextpdf.text.Image.getInstance(originalImage,
					Color.white);
		} catch (BadElementException e4) {
			e4.printStackTrace();
		} catch (IOException e4) {
			e4.printStackTrace();
		}

		cell = new PdfPCell(p);
		cell.setColspan(4);
		cell.addElement(image1);

		this.chartTable.addCell(cell);

		try {
			if (this.statTable != null) {
				log("Added Statistics table");
				this.statTable.setSpacingBefore(15f);
				this.document.add(this.statTable);
				this.statTable.setSpacingAfter(15f);
			}

			if (this.chartTable != null) {
				log("Added chart table");
				this.chartTable.setSpacingBefore(15f);
				this.document.add(this.chartTable);
				this.chartTable.setSpacingAfter(15f);
			}

			if (this.failTable != null) {
				log("Added fail table");
				this.failTable.setSpacingBefore(15f);
				this.document.add(this.failTable);
				this.failTable.setSpacingAfter(15f);
			}

			if (this.successTable != null) {
				log("Added success table");
				this.successTable.setSpacingBefore(15f);
				this.document.add(this.successTable);
				this.successTable.setSpacingBefore(15f);
			}

			if (this.skipTable != null) {
				log("Added skip table");
				this.skipTable.setSpacingBefore(15f);
				this.document.add(this.skipTable);
				this.skipTable.setSpacingBefore(15f);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		p = new Paragraph("EXCEPTIONS SUMMARY", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.RED));
		try {
			this.document.add(p);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}

		Set<Integer> keys = this.throwableMap.keySet();

		assert keys.size() == this.nbExceptions;

		for (Integer key : keys) {
			Throwable throwable = this.throwableMap.get(key);

			Chunk chunk = new Chunk(throwable.toString(), FontFactory.getFont(
					FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.RED));

			chunk.setLocalDestination("" + key);
			Paragraph throwTitlePara = new Paragraph(chunk);
			try {
				this.document.add(throwTitlePara);
			} catch (DocumentException e3) {
				e3.printStackTrace();
			}

			StackTraceElement[] elems = throwable.getStackTrace();
			String exception = "";
			for (StackTraceElement ste : elems) {
				Paragraph throwParagraph = new Paragraph(ste.toString());
				try {
					this.document.add(throwParagraph);
				} catch (DocumentException e2) {
					e2.printStackTrace();
				}
			}
		}

		this.document.close();
		try {
			Email.EmailPDFAfterSuite();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * log
	 * 
	 * @param o
	 */
	public static void log(Object o) {
		// System.out.println("[JyperionListener] " + o);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	/*
	 * public static void EmailPDFAfterSuite(ITestResult result){
	 * log("onTestSuccess("+result+")");
	 * sendPDFReportByGMail("brenden@activewebsite.com", "$am6reRhH$",
	 * "brenden@activewebsite.com", result.getMethod().toString() +
	 * "PDF Report", "");
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
