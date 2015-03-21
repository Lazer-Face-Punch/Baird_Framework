package com.booj.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;

public class DropBox {
	private static String sharedURL = "";
	// private PropertyFileManager pfm;
	/*
	 * private final String DROP_BOX_APP_KEY =
	 * pfm.getProperty("DROP_BOX_APP_KEY"); private final String
	 * DROP_BOX_APP_SECRET = pfm.getProperty("DROP_BOX_APP_SECRET"); private
	 * final String rootDir = pfm.getProperty("rootDir"); private final String
	 * accessToken = pfm.getProperty("accessToken");
	 */
	DbxClient dbxClient;

	public DbxClient authDropbox(String dropBoxAppKey, String dropBoxAppSecret)
			throws IOException, DbxException {

		PropertyFileManager pfm = new PropertyFileManager();

		DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropBoxAppSecret);
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig(
				"BoojBaird/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(
				dbxRequestConfig, dbxAppInfo);
		String authorizeUrl = dbxWebAuthNoRedirect.start();
		System.out.println("1. Authorize: Go to URL and click Allow : "
				+ authorizeUrl);
		System.out
				.println("2. Auth Code: Copy authorization code and input here ");
		// String dropboxAuthCode = new BufferedReader(new InputStreamReader(
		// System.in)).readLine().trim();
		// DbxAuthFinish authFinish =
		// dbxWebAuthNoRedirect.finish(dropboxAuthCode);
		// String accessToken = authFinish.accessToken;
		dbxClient = new DbxClient(dbxRequestConfig,
				pfm.getProperty("accessToken"));
		System.out.println("Dropbox Account Name: "
				+ dbxClient.getAccountInfo().displayName);

		return dbxClient;

		/*
		 * File inputFile = new File(rootDir +"Screenshots\\"+ file);
		 * FileInputStream inputStream = new FileInputStream(inputFile); try {
		 * 
		 * DbxEntry.File uploadedFile = client.uploadFile("/Screenshot.png",
		 * DbxWriteMode.add(), inputFile.length(), inputStream); String
		 * sharedUrl = client.createShareableUrl("/Screenshot.png");
		 * System.out.println("Uploaded: " + uploadedFile.toString() + " URL " +
		 * sharedUrl); } finally { inputStream.close(); }
		 */
	}

	public void uploadToDropbox(String fileName) throws DbxException,
			IOException {
		PropertyFileManager pfm = new PropertyFileManager();
		File inputFile = new File(pfm.getProperty("rootDir") + "Screenshots//"
				+ fileName);
		FileInputStream fis = new FileInputStream(inputFile);
		try {
			DbxEntry.File uploadedFile = dbxClient.uploadFile(
					"/" + pfm.getProperty("dropBoxFolder") + "/" + fileName,
					DbxWriteMode.add(), inputFile.length(), fis);
			String sharedUrl = dbxClient.createShareableUrl("/" + pfm.getProperty("dropBoxFolder") + "/" + fileName);
			System.out.println("Uploaded: " + uploadedFile.toString() + " URL "
					+ sharedUrl);
			sharedURL = sharedUrl;
		} finally {
			fis.close();
		}
	}
	
	public static String getSharedUrl(){
		return sharedURL;
	}

	public void testSharableUrl(String fileName) throws DbxException,
			IOException {
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig(
				"BoojBaird/1.0", Locale.getDefault().toString());
		PropertyFileManager pfm = new PropertyFileManager();
		dbxClient = new DbxClient(dbxRequestConfig,
				pfm.getProperty("accessToken"));
		String DropURL = dbxClient.createShareableUrl("/" + fileName);
		//DropUrl = DropURL;
		System.out.println(DropURL);
		
	}
	

	public static void main(String fileName) throws IOException, DbxException {
		
		PropertyFileManager pfm = new PropertyFileManager();
		DropBox dropbox = new DropBox();
		dropbox.authDropbox(pfm.getProperty("DROP_BOX_APP_KEY"),
				pfm.getProperty("DROP_BOX_APP_SECRET"));
		
		dropbox.uploadToDropbox(fileName);
		//dropbox.testSharableUrl(fileName);
		/*
		 * System.out.println("Dropbox Size: " + dropbox.getDropboxSize() +
		 * " GB");
		 */
		
		/*
		 * dropbox.createFolder("tutorial"); dropbox.listDropboxFolders("/");
		 * dropbox.downloadFromDropbox("happy.png");
		 */

	}

	/*public static void DropUrl(String fileName) throws DbxException,
			IOException {

		DropBox dropbox = new DropBox();
		dropbox.testSharableUrl(fileName);

	}*/
	


	


}
