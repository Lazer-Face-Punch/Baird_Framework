package com.booj.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.testng.annotations.Test;

import com.lowagie.text.pdf.PdfAction;

public class IfTest {
	File file;
	URL url = null;	
	@Test(enabled = false)
	public void thig(){
		String file = System.getProperty("user.dir");
		System.out.println(file);
	}
	
	@Test(enabled = true)
	public void thtl(){
		String directory = System.getProperty("user.dir");
		System.out.println(directory);
		
		if (directory.equals("C:\\Users\\brenden\\workspace\\Maven_Testing\\baird-framework")){
			System.out.println("Local");
			//URL url = null;	
			try {
				url = new File(directory.toString()).toURI().toURL();
				System.out.println(url);
	
			} catch (MalformedURLException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if (directory.equals("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace")){
			System.out.println("Jenkins");
			String replaced = directory.replace("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace", "/job/PDF test/ws/");
			//URL url = null;	
				try {
					url = new URL("http", "localhost", 8080, replaced);
					System.out.println(url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		}else{ 
			System.out.println("User Directory Option Not Found");
		}
		
		//URL url = null;	
	/*imdb.setAction(new PdfAction("file:///"+url.toString()));
	System.out.println(url.toString());*/
		}
	
}

		
/*file = new File (System.getProperty("user.dir")+"/AutomatedTestsRunReport/SmokeTestReport.pdf");
file = new File("user.dir");
//file = new File("C:/Users/brenden/workspace/Maven_Testing/baird-framework/AutomatedTestsRunReport/SmokeTestReport.pdf");
//file = new File("C:/Users/brenden/.jenkins/jobs/PDF test/workspace/AutomatedTestsRunReport/SmokeTestReport.pdf");
System.out.println(file);
// if file doesn't exists, then create it
if (!file.exists()) {
	try {
		file.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
}else{
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
this.document.open();*/