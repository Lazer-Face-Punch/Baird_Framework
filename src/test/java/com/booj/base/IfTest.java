package com.booj.base;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.testng.annotations.Test;

import com.booj.driver.Driver;
import com.booj.utilities.DropBox;
import com.booj.utilities.PropertyFileManager;
import com.booj.utilities.ScreenShot;
import com.dropbox.core.DbxException;

public class IfTest {
	File file;
	DropBox dropbox;
	@Test
	public void fart() throws IOException, DbxException  {
	dropbox = new DropBox();
		PropertyFileManager pfm = null;
		try {
			pfm = new PropertyFileManager();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			Driver.Initialize();
			Driver.Instance.get("http://www.google.com");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
		Date resultdate = new Date(yourmilliseconds);
		//String random = "Screenshot"/* + "-" + sdf.format(resultdate) */+ ".png";
		 String random = "Screenshot"+sdf.format(resultdate) + (new Random().nextInt())+".png";
		String file = (pfm.getProperty("picDir") + "\\" + random);
		System.out.println(file);
		try {
			ScreenShot.takeSnapShot(Driver.getInstance(), file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
		try {
			Driver.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DropBox.main(random);
		//DropBox.DropUrl(random);

		//String url = DropBox.getSharedUrl();
		//System.out.println("FUCKING WORK" + url);
		
		//System.out.println("FUCKING WORK" + url);
		
	}

	@Test(enabled = false)
	public void thtl() throws IOException, ParseException {
		PropertyFileManager pfm = new PropertyFileManager();
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy-HH:mm");
		Date resultdate = new Date(yourmilliseconds);
		String random = "Screenshot"/* + "-" + sdf.format(resultdate) */+ ".png";
		String file = (pfm.getProperty("picDir") + "\\" + random);
		// String file = (pfm.getProperty("picDir") +"\\"+"screenshot"+(new
		// Random().nextInt()))+".png";
		// System.out.println(sdf.format(resultdate));
		System.out.println(file);
	}
}
// String url = null;
/*
 * @Test(enabled = false) public void thig(){ String file =
 * System.getProperty("user.dir"); System.out.println(file); }
 * 
 * @Test(enabled = false) public void thtl(){ String directory =
 * System.getProperty("user.dir"); System.out.println(directory);
 * 
 * if
 * (directory.equals("C:\\Users\\brenden\\workspace\\Maven_Testing\\baird-framework"
 * )){ System.out.println("Local"); String url = new
 * File(directory).toURI().toString(); System.out.println(url); }else if
 * (directory
 * .equals("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace")){
 * System.out.println("Jenkins"); String replaced =
 * directory.replace("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace",
 * "/job/PDF test/ws/"); //URL url = null; try { URL url = new URL("http",
 * "localhost", 8080, replaced); System.out.println(url); } catch
 * (MalformedURLException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * }else{ System.out.println("User Directory Option Not Found"); }
 * DropBox.main(file);
 * 
 * //URL url = null; imdb.setAction(new PdfAction("file:///"+url.toString()));
 * System.out.println(url.toString()); }
 */

/*
 * file = new File
 * (System.getProperty("user.dir")+"/AutomatedTestsRunReport/SmokeTestReport.pdf"
 * ); file = new File("user.dir"); //file = new File(
 * "C:/Users/brenden/workspace/Maven_Testing/baird-framework/AutomatedTestsRunReport/SmokeTestReport.pdf"
 * ); //file = new File(
 * "C:/Users/brenden/.jenkins/jobs/PDF test/workspace/AutomatedTestsRunReport/SmokeTestReport.pdf"
 * ); System.out.println(file); // if file doesn't exists, then create it if
 * (!file.exists()) { try { file.createNewFile(); } catch (IOException e) {
 * e.printStackTrace(); } }else{ file.delete(); try { file.createNewFile(); }
 * catch (IOException e) { e.printStackTrace(); } } try {
 * PdfWriter.getInstance(this.document, new FileOutputStream(file)); } catch
 * (Exception e) { e.printStackTrace(); } this.document.open();
 */