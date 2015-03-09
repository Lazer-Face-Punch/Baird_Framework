package com.booj.base;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.booj.driver.Driver;
import com.booj.utilities.ScreenShot;
import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfAction;

public class testte {
	
	FileOutputStream fop = null;
	File file;

	public void onStart(ITestContext context){
		file = new File("C:/Users/brenden/.jenkins/jobs/PDF test/workspace/AutomatedTestsRunReport/SmokeTestReport.pdf");
	}
	
	
	public void onTestFailure(ITestResult result){
		String file = System.getProperty("user.dir")+"\\"+"screenshot"+(new Random().nextInt())+".png";
		try{
			ScreenShot.takeSnapShot(Driver.getInstance(), file);
		} catch (Exception e) {
			
		}
	Chunk imdb = new Chunk("[SCREEN SHOT]", new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE));
	imdb.setAction(new PdfAction("file:///"+file));
	file.replace("C:/Users/brenden/.jenkins/","localhost:8080/");
	
}
	
	@Test
	public void test() throws MalformedURLException, URISyntaxException{
		//String file = System.getProperty("user.dir")+"\\"+"screenshot"+(new Random().nextInt())+".png";
		System.out.println(file);
		String file = new String("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace\\screenshot1512330295.png");
		String replaced = file.replace("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace\\", "/job/PDF test/ws/");
		//URIUtil.encodeString("http","localhost:8080", file);
		URI uri = null;
		URL url = null;	
			url = new URL("http", "localhost", 8080, replaced);
			
				System.out.println(url);
				
	}
	
		
	/*	try {
			String url = URLEncoder.encode("http://localhost:8080/job/PDF test/ws/", "UTF-8");
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		*/
	
		
	}

