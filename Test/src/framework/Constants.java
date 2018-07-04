package framework;

import java.io.*;
import java.util.*;


public class Constants {
	static InputStream inputStream;

	public static Properties LngProp;
	public static String title;
	
	public  void LoadProperties() throws Exception{
	try {
		Properties prop = new Properties();
		String propFileName = "USLogin.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		 title= prop.getProperty("LibraryPageTitle");
	} catch (Exception e) {
		System.out.println("Exception: " + e);
	} finally {
		inputStream.close();
	}

}
}
