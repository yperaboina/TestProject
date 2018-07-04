package snapfish;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;


public class TestExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			File file = new File("D:\\EclipseWorkspace\\Test\\src\\properties\\USLogin.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			/*
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}*/
			
			//System.out.println(enuKeys.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
