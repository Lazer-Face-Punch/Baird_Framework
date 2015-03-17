package com.booj.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.testng.annotations.Test;

public class IfTest {
	File file;
	
	@Test(enabled = false)
	public void thig(){
		String file = System.getProperty("user.dir");
		System.out.println(file);
	}
	
	@Test(enabled = true)
	public void thtl(){
		String file = System.getProperty("user.dir");
		System.out.println(file);
		
		if (file.equals("C:\\Users\\brenden\\workspace\\Maven_Testing\\baird-framework")){
			System.out.println("Local");
			
		}else if (file.equals("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace")){
			System.out.println("Jenkins");
		}else{ 
			System.out.println("Not Found");
		}
		
		}
	
	
	@Test(enabled = false)
	public void thing (){
		String file = System.getProperty("user.dir")+"\\"+"screenshot"+(new Random().nextInt())+".png";
		System.out.println(file);
		URL url = null;	
		if (System.getProperty("user.dir").equals("C:\\Users\\brenden\\workspace\\Maven_Testing\\baird-framework\\")){
			try {
				url = new URL("http", "file:///", file);
				System.out.println(url);
	
			} catch (MalformedURLException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			
			}} else if
		 (System.getProperty("user.dir").equals("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace\\")){
		String replaced = file.replace("C:\\Users\\brenden\\.jenkins\\jobs\\PDF test\\workspace\\", "/job/PDF test/ws/");
		//URL url = null;	
			try {
				url = new URL("http", "localhost", 8080, replaced);
				System.out.println(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
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