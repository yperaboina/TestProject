package snapfish;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.lang.Runtime.*;



public class StartBatchFile {
	
	public static WebDriver driver;	
	
	public static void launchFirefoxBrowser() throws Exception{

		 System.out.println("Entered into launchFirefoxBrowser method");
		//if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
		System.setProperty("webdriver.gecko.driver", "D:/geckodriver-v0.19.1-win64/geckodriver.exe");

		//Now you can Initialize marionette driver to launch firefox
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		//WebDriver driver = new MarionetteDriver(capabilities); 
		driver=new FirefoxDriver(capabilities);
		 System.out.println("end of launchFirefoxBrowser method");
	}
	




	

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//launchFirefoxBrowser();
		System.out.println("program started");
		Process batch = Runtime.getRuntime().exec("D:\\interview\\startJenkins.bat");
		System.out.println("program in progress " + batch);
		batch.waitFor();
		System.out.println("program ended");

	}

}
