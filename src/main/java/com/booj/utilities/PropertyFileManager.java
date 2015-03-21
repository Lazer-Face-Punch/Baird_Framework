package com.booj.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;

import com.booj.driver.IOOperations;

public class PropertyFileManager {
	Properties properties = null;
	Log log;

	public PropertyFileManager() throws IOException {
		this("config.properties", IOOperations.READ);
	}

	public PropertyFileManager(String fileName, IOOperations ioOperations)
			throws IOException {
		this.properties = new Properties();
		File inputFile = new File(fileName);
		if (!inputFile.exists())
			throw new IOException("File does not exist");
		InputStream is = new FileInputStream(inputFile.getAbsolutePath());
		this.properties.load(is);
		is.close();
	}

	public String getProperty(String propName) {
		if (this.properties == null)
			throw new NullPointerException("Properties is null");
		return this.properties.getProperty(propName);
	}

	public void setProperty(String propName, String propValue) throws Exception {
		properties.setProperty(propName, propName);
		throw new Exception("Yet to be implemented");
	}
}
