
package snapfish;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.*;

import objectRepository.*;
import properties.*;

public class WebTest1 {	
	public static String expectedTitle, actualTitle;
	public static WebDriver driver;	
	 public static String chromeDriverPath = "D:/chromedriver_win32/";
	public static String ClosePrintPopup="//map[@title='close']";
	public static String PrintPopup="//map[@id='monetate_lightbox_contentMap']";
	
	public static String EmailAddress2="//input[@id='EmailAddress' or @id='email']";
	public static String Password2="//input[@id='Password' or @id='pwd']";
	public static String SignIn2="//a[@id='loginsubmit'] | //input[@id='loginButton']";	
	public static String ldapURL;
	
	public static long waitTime=5000;
	static String sTestdataPath="D:\\EclipseWorkspace\\Test\\src\\testdata\\Photo-Org-TestData.xlsx";
	static String  sbaseUrl,service,testPlan,sSubscriber,cobrand,EmailId,Password;
	static int sTestCase_Id;
	public static String winHandleBefore;
	
	
	static WebElement EmailAddress,password,SignIn,SAV,SelectIcon, LibTitle, AddPhotosBTN;
	
	 public static  void readRow(int rownum) throws Exception

	    {   
		 XSSFWorkbook wb = null;
		   System.out.println("Entered into Read "+rownum+" method");
		  InputStream data = new FileInputStream(sTestdataPath);
		    wb= new XSSFWorkbook(data);
		    XSSFSheet sheet =wb.getSheetAt(0);
		    XSSFRow row=sheet.getRow(rownum); 
		    sTestCase_Id=(int)row.getCell(0).getNumericCellValue();		    
		    service=row.getCell(1).toString();		    
		    testPlan=row.getCell(2).toString();		     
		    sSubscriber=row.getCell(3).toString();		    
		    cobrand=row.getCell(4).toString();
		    EmailId=row.getCell(5).toString();
	        Password=row.getCell(6).toString();
	        sbaseUrl=row.getCell(7).toString();
	        System.out.println(" Readed values sTestCase_Id= " + sTestCase_Id + ", service= "+service + " sbaseUrl= " +sbaseUrl );
	        System.out.println("End of read row");
	    }
	
	public static void login(String SiteBaseUrl,String UserEmailAddress1, String UserPassword1) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(SiteBaseUrl); 
		System.out.println("Site Base URL is  "+ SiteBaseUrl);
		Thread.sleep(waitTime);
		 WebElement SignInButton = driver.findElement(By.xpath(GlobalHeader.HeaderSignIn));	
		 try{
		  	if( driver.findElement(By.xpath(PrintPopup)).isDisplayed())		  		
		  	{		  		
		  		driver.findElement(By.xpath(ClosePrintPopup)).click();
		  	}
		 }
		 catch(Exception e){
			 System.out.println("Exception is "+e);
			 
		 }
			                         SignInButton.click();
			                         System.out.println(driver.getTitle());	
		                             closeForeseelayer();

			                         //Thread.sleep(waitTime);
		                             EmailAddress = driver.findElement(By.xpath(EmailAddress2));		           
		                             EmailAddress.clear();		              
		                             EmailAddress.sendKeys(UserEmailAddress1);             
		               	              
		                             password = driver.findElement(By.xpath(Password2));		              
		                             password.clear();		             
		                             password.sendKeys(UserPassword1);
		                             driver.findElement(By.xpath(SignIn2)).click();
		                             System.out.println(driver.getTitle());
		                             driver.getTitle().equalsIgnoreCase("Photo");	
		                             System.out.println("User sucessfully navigated to home page");
		                            // Thread.sleep(waitTime);
		                             //Thread.sleep(waitTime);
		                             closeForeseelayer();
		
		
	}
		
	public static void closeForeseelayer() throws Exception{
		 try {
			 System.out.println("\n Entered into closeForeseelayer() method");
        	 if((driver.findElement(By.xpath(ForeSeeLayer.NoThanksBTN)).isDisplayed()))
        	 System.out.println("\n Entered into if section");
        	 driver.findElement(By.xpath(ForeSeeLayer.NoThanksBTN)).click();
        	 System.out.println("\n ForSee layer displayed and clicked on 'No THanks' button");
         }
		 catch(Exception e){
			 System.out.println("\n No such element "+e.getMessage());		
		 }	
		 System.out.println("\n exit from closeForeseelayer() method as not found Foreseelayer");
	}

	
	public static void openLibraryPage() throws Exception{
		try{
		//Thread.sleep(waitTime);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement MYPHOTOS=driver.findElement(By.xpath(GlobalHeader.MyPhotos));
		closeForeseelayer();
		MYPHOTOS.click();
		System.out.println(driver.getTitle());
		//driver.getTitle().equalsIgnoreCase("Photo Library");
		driver.getTitle().equalsIgnoreCase(LibraryPage.title);
		System.out.println("User sucessfully navigated to Library page");
		//driver.navigate().back();
		//Thread.sleep(waitTime);
		//System.out.println("User navigated back  to home page" + driver.getTitle());
		//driver.navigate().forward();
		//Thread.sleep(waitTime);
		System.out.println("User navigated  to library  page" + driver.getTitle());
		}
		catch(Exception e){
			System.out.println("Exception  in openLibraryPage()Method is: "+ e.getMessage());
		}
		
	}
	
	public static void verifyDefaultMessages() throws Exception{
		try{
		driver.findElement(By.xpath(LibraryPage.NoPhotosTxt1)).getText().matches("No photos? No problem.");
		System.out.println("displayed text on the web page is "+ driver.findElement(By.xpath(LibraryPage.NoPhotosTxt1)).getText());	
		driver.findElement(By.xpath(LibraryPage.NoPhotosTxt2)).getText().matches("No photos? No problem.");
		System.out.println("displayed text on the web page is "+ driver.findElement(By.xpath(LibraryPage.NoPhotosTxt2)).getText());	
		Thread.sleep(waitTime);		
		driver.findElement(By.xpath(LibraryPage.AlbumsDropDownMenu)).click();
		driver.findElement(By.xpath(LibraryPage.FriendsAlbumsLnk)).click();
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(LibraryPage.FriendsAlbumsDefaultTxt)).getText().matches("When friends share albums with you, they will appear here.");
		System.out.println("displayed text on the web page is "+ driver.findElement(By.xpath(LibraryPage.FriendsAlbumsDefaultTxt)).getText());
		Thread.sleep(waitTime);		
		driver.findElement(By.xpath(LibraryPage.AlbumsDropDownMenu)).click();
		driver.findElement(By.xpath(LibraryPage.MySharedAlbumsLnk)).click();
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(LibraryPage.MySharedAlbumsDefaultTxt)).getText().matches("When you share albums with your friends, they will appear here.");
		System.out.println("displayed text on the web page is "+ driver.findElement(By.xpath(LibraryPage.MySharedAlbumsDefaultTxt)).getText());
		Thread.sleep(waitTime);	
		driver.findElement(By.xpath(LibraryPage.AlbumsDropDownMenu)).click();
		driver.findElement(By.xpath(LibraryPage.MyFavoritesLnk)).click();
		Thread.sleep(waitTime);	
		driver.findElement(By.xpath(LibraryPage.MyFavoritesAlbumsDefaultTxt1)).getText().matches("Show your photos some love!");
		driver.findElement(By.xpath(LibraryPage.MyFavoritesAlbumsDefaultTxt2)).getText().matches("Pick your favorites and we’ll keep them right here for you.");
		System.out.println("displayed text on the web page is "+ driver.findElement(By.xpath(LibraryPage.MyFavoritesAlbumsDefaultTxt1)).getText() + "\n" + driver.findElement(By.xpath(LibraryPage.MyFavoritesAlbumsDefaultTxt2)).getText());
		closeForeseelayer();
		
		}
		catch(Exception e){
			System.out.println("Exception  in default messages is: "+ e.getMessage());
		}
	}
	
	public static void verifyMydeviceIconInNoPhotosPage() throws Exception{
		String uploadPageTitle = "";
		try{
			System.out.print("\n Entered into verifyMydeviceIconInNoPhotosPage()");
            closeForeseelayer();
			driver.findElement(By.xpath(LibraryPage.MyDeviceIcon)).click();
			System.out.print("\n My device icon clicked on default page");
			Thread.sleep(waitTime);
			
			//to get the current/parent window
			String parentWindowContact = driver.getWindowHandle();

			//to switch to the new window from current/parent window
			Set<String> handleswindow =driver.getWindowHandles();

			for(String windowHandle : handleswindow)
			{
			   driver.switchTo().window(windowHandle);
			  
				//driver.findElement(By.xpath(UploadLayer.CancelBtn)).click();
			}
			 uploadPageTitle = driver.getTitle();
			 if(uploadPageTitle.contains("Uploader")){
			 System.out.print("\n User is on Uploader page " );
			 }else{System.out.print("\n page title not matches with Upload page and current page title is: "+ uploadPageTitle);}
			 
			//to close the new window
			driver.close();

			//to switch back to the parent window
			driver.switchTo().window(parentWindowContact);
			
			
			Thread.sleep(waitTime);
			System.out.print("\n Current page title is: "+ driver.getTitle());
			//driver.switchTo().window("win_ser_local");
			driver.findElement(By.xpath(LibraryPage.FacebookIcon)).isDisplayed();
			System.out.println("\n Facebook icon displayed");
			System.out.print("\n User is on Library page and page title is: "+ driver.getTitle());
		}
		catch(Exception e){
			System.out.println("Exception  in verifyMydeviceIconInNoPhotosPage()Method is: "+ e.getMessage());
		}
		
	}
	public static void verifyFacebookInNoPhotosPage() throws Exception{
		String FacebookPageTitle = "";
		try{
			System.out.print("\n Entered into verifyFacebookInNoPhotosPage()");
            closeForeseelayer();
			driver.findElement(By.xpath(LibraryPage.FacebookIcon)).click();
			System.out.print("\n Facebook icon clicked on default page");
			Thread.sleep(waitTime);
			
			//to get the current/parent window
			String parentWindowContact = driver.getWindowHandle();

			//to switch to the new window from current/parent window
			Set<String> handleswindow =driver.getWindowHandles();

			for(String windowHandle : handleswindow)
			{
			   driver.switchTo().window(windowHandle);		   
				
			}

			FacebookPageTitle = driver.getTitle();
			if(FacebookPageTitle.contains("Facebook")){
				 System.out.print("\n User is on Facebook page " );
				 }else{System.out.print("\n page title not matches with Facebook and current page title is: "+ FacebookPageTitle);}
			//to close the new window
			driver.close();

			//to switch back to the parent window
			driver.switchTo().window(parentWindowContact);
			
			
			Thread.sleep(waitTime);
			System.out.print("\n After closed Facebook window, Current page title is: "+ driver.getTitle());
			//driver.switchTo().window("win_ser_local");
			driver.findElement(By.xpath(LibraryPage.InstagramIcon)).isDisplayed();
			System.out.println("\n Instagram displayed");
			System.out.print("\n User is on Library page and page title is: "+ driver.getTitle());
		}
		catch(Exception e){
			System.out.println("Exception  in verifyFacebookInNoPhotosPage()Method is: "+ e.getMessage());
		}
		
	}
	public static void verifyGoogleInNoPhotosPage() throws Exception{
		String GooglePageTitle = "";
		try{
			System.out.print("\n Entered into verifyGoogleInNoPhotosPage()");
            closeForeseelayer();
			driver.findElement(By.xpath(LibraryPage.GoogleIcon)).click();
			System.out.print("\n Google icon clicked on default page");
			Thread.sleep(waitTime);
			
			//to get the current/parent window
			String parentWindowContact = driver.getWindowHandle();

			//to switch to the new window from current/parent window
			Set<String> handleswindow =driver.getWindowHandles();

			for(String windowHandle : handleswindow)
			{
			   driver.switchTo().window(windowHandle);		   
				
			}

			GooglePageTitle = driver.getTitle();
			if(GooglePageTitle.contains("Google")){
				 System.out.print("\n User is on Google page " );
				 }else{System.out.print("\n page title not matches with Google and current page title is: "+ GooglePageTitle);}
			//to close the new window
			driver.close();

			//to switch back to the parent window
			driver.switchTo().window(parentWindowContact);
			
			
			Thread.sleep(waitTime);
			System.out.print("\n After closed Google window, Current page title is: "+ driver.getTitle());
			//driver.switchTo().window("win_ser_local");
			driver.findElement(By.xpath(LibraryPage.InstagramIcon)).isDisplayed();
			System.out.println("\n Instagram displayed");
			System.out.print("\n User is on Library page and page title is: "+ driver.getTitle());
		}
		catch(Exception e){
			System.out.println("Exception  in verifyGoogleInNoPhotosPage()Method is: "+ e.getMessage());
		}
		
	}
	public static void verifyInstagramInNoPhotosPage() throws Exception{
		String InstagramPageTitle = "";
		try{
			System.out.print("\n Entered into verifyInstagramInNoPhotosPage()");
            closeForeseelayer();
			driver.findElement(By.xpath(LibraryPage.InstagramIcon)).click();
			System.out.print("\n Instagram icon clicked on default page");
			Thread.sleep(waitTime);
			
			//to get the current/parent window
			String parentWindowContact = driver.getWindowHandle();

			//to switch to the new window from current/parent window
			Set<String> handleswindow =driver.getWindowHandles();

			for(String windowHandle : handleswindow)
			{
			   driver.switchTo().window(windowHandle);		   
				
			}

			InstagramPageTitle = driver.getTitle();
			if(InstagramPageTitle.contains("Instagram")){
				 System.out.print("\n User is on Instagram page " );
				 }else{System.out.print("\n page title not matches with Instagram and current page title is: "+ InstagramPageTitle);}
			//to close the new window
			driver.close();

			//to switch back to the parent window
			driver.switchTo().window(parentWindowContact);
			
			
			Thread.sleep(waitTime);
			System.out.print("\n After closed Instagram window, Current page title is: "+ driver.getTitle());
			//driver.switchTo().window("win_ser_local");
			//driver.findElement(By.xpath(LibraryPage.InstagramIcon)).isDisplayed();
			//System.out.println("\n Instagram displayed");
			System.out.print("\n User is on Library page and page title is: "+ driver.getTitle());
		}
		catch(Exception e){
			System.out.println("Exception  in verifyInstagramInNoPhotosPage()Method is: "+ e.getMessage());
		}
		
	}
	public static void verifyFlickrInNoPhotosPage() throws Exception{
		String FlickrPageTitle = "";
		try{
			System.out.print("\n Entered into verifyFlickrInNoPhotosPage()");
            closeForeseelayer();
			driver.findElement(By.xpath(LibraryPage.FlickrIcon)).click();
			System.out.print("\n Flickr icon clicked on default page");
			Thread.sleep(waitTime);
			
			//to get the current/parent window
			String parentWindowContact = driver.getWindowHandle();

			//to switch to the new window from current/parent window
			Set<String> handleswindow =driver.getWindowHandles();

			for(String windowHandle : handleswindow)
			{
			   driver.switchTo().window(windowHandle);		   
				
			}

			FlickrPageTitle = driver.getTitle();
			if(FlickrPageTitle.contains("Flickr")){
				 System.out.print("\n User is on Flickr page " );
				 }else{System.out.print("\n page title not matches with Flickr and current page title is: "+ FlickrPageTitle);}
			//to close the new window
			driver.close();

			//to switch back to the parent window
			driver.switchTo().window(parentWindowContact);
			
			
			Thread.sleep(waitTime);
			System.out.print("\n After closed Flickr window, Current page title is: "+ driver.getTitle());
			//driver.switchTo().window("win_ser_local");
			//driver.findElement(By.xpath(LibraryPage.FlickrIcon)).isDisplayed();
			//System.out.println("\n Flickr displayed");
			System.out.print("\n User is on Library page and page title is: "+ driver.getTitle());
		}
		catch(Exception e){
			System.out.println("Exception in verifyFlickrInNoPhotosPage()Method is: "+ e.getMessage());
		}
		
	}
	public static void verifyMyDeviceFBGoogleInstaFlickr() throws Exception{
		
		verifyMydeviceIconInNoPhotosPage();
		verifyFacebookInNoPhotosPage();		
		verifyInstagramInNoPhotosPage();
		
		if(!sbaseUrl.contains("walgreens")){
			verifyGoogleInNoPhotosPage();
			verifyFlickrInNoPhotosPage();
			}
	}
	public static void openUploadWindow() throws Exception{
		Thread.sleep(waitTime);
		WebElement ADDPHOTOS=driver.findElement(By.xpath(LibraryPage.AddPhotosBtn));
		ADDPHOTOS.click();
		System.out.println(driver.getTitle());
		//driver.getTitle().equalsIgnoreCase("Photo Library");
		driver.getTitle().equalsIgnoreCase(LibraryPage.title);
		System.out.println("User sucessfully navigated to Library page");		
		
	}
	
	public static void scrollDownThePage() throws Exception{
	
	Thread.sleep(waitTime);
	
	//JavascriptExecutor jse = (JavascriptExecutor)driver;
	//jse.executeScript("window.scrollBy(0,250)", "");
	//jse.executeScript("scroll(0, 500);");
	
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	System.out.println("Page scrolled down");
	
	
	
	/*
	Actions dragger = new Actions(driver);
     WebElement draggablePartOfScrollbar = driver.findElement(By.xpath("//p[contains(text(),'album 01/08/2016')]"));

     // drag downwards
     int numberOfPixelsToDragTheScrollbarDown = 50;
     for (int i=10;i<500;i=i+numberOfPixelsToDragTheScrollbarDown){
         try{
     // this causes a gradual drag of the scroll bar, 10 units at a time
     dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
     
     System.out.println("Page scrolled down");
     Thread.sleep(1000L);
         }catch(Exception e1){}
     } 

	*/
	}
	
	public static void openSingleAlbumPage() throws Exception{
		try{
		Thread.sleep(waitTime);
		WebElement UserAlbum=driver.findElement(By.xpath(LibraryPage.Album));
		UserAlbum.click();
		Thread.sleep(waitTime);
		 SAV= driver.findElement(By.xpath(SingleAlbumView.SingleAlbumViewPhoto));
		if(SAV.isDisplayed())	{	
		System.out.println("User sucessfully navigated to single album view page and photo displayed");	
		}
		else{
			System.out.println("photo not displayed in single album view");
		}
		}
		catch(Exception e){
			System.out.println("single album view not displayed and error is: "+e);
		}
	}
	
	public static void savImageOperations() throws Exception{
		try{
		Thread.sleep(waitTime);
		 WebElement photo1 = driver.findElement(By.xpath(SingleAlbumView.SingleAlbumViewPhoto));
		  Actions action = new Actions(driver);	
		  //To click on Rotate link
	      action.moveToElement(photo1).build().perform();
	      System.out.println("\n Mouse hovered on first image");
	            Thread.sleep(waitTime);
				driver.findElement(By.xpath(SingleAlbumView.settingsIconOnThumbnail)).click();
				System.out.println("\n clicked on first image settings icon");
				Thread.sleep(waitTime);
				driver.findElement(By.xpath(SingleAlbumView.rotateLnk)).click();
				System.out.println("\n clicked on rotate link");
				
				//to click on download link 
				action.moveToElement(photo1).build().perform();
			      System.out.println("\n Mouse hovered on first image");
			            Thread.sleep(waitTime);
						driver.findElement(By.xpath(SingleAlbumView.settingsIconOnThumbnail)).click();
						System.out.println("\n clicked on first image settings icon");
						Thread.sleep(waitTime);
						driver.findElement(By.xpath(SingleAlbumView.downloadPhotoLnk)).click();
						System.out.println("\n clicked on download photo link");
						
						//to click on download link 
						action.moveToElement(photo1).build().perform();
					      System.out.println("\n Mouse hovered on first image");
					            Thread.sleep(waitTime);
								driver.findElement(By.xpath(SingleAlbumView.settingsIconOnThumbnail)).click();
								System.out.println("\n clicked on first image settings icon");
								Thread.sleep(waitTime);
								driver.findElement(By.xpath(SingleAlbumView.deletePhotoLnk)).click();
								System.out.println("\n clicked on delete photo link");
		
		}
		catch(Exception e){
			System.out.println("Error in single album view page is: "+e);
		}
	}
	
	public static void openSingleAlbumPageWithExplicitWait() throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LibraryPage.Album)));
		driver.findElement(By.xpath(LibraryPage.Album)).click();
		//UserAlbum.click();
		Thread.sleep(waitTime);
		 SAV= driver.findElement(By.xpath(SingleAlbumView.SingleAlbumViewPhoto));
		if(SAV.isDisplayed())	{	
		System.out.println("User sucessfully navigated to single album view page and photo displayed");	
		}
		else{
			System.out.println("photo not displayed in single album view");;
		}
		
	}
	
	public static void openSingleAlbumPageRightClick() throws Exception{
		Thread.sleep(waitTime);
		WebElement UserAlbum=driver.findElement(By.xpath(LibraryPage.Album));
		UserAlbum.click();
		Thread.sleep(waitTime);
		 SAV= driver.findElement(By.xpath(SingleAlbumView.SingleAlbumViewPhoto));
		if(SAV.isDisplayed())	{	
		System.out.println("User sucessfully navigated to single album view page and photo displayed");	
		
		
		}
		else{
			System.out.println("photo not displayed in single album view");;
		}
		driver.navigate().back();
		Actions action=new Actions(driver);
		action.moveToElement(UserAlbum);
		action.contextClick(UserAlbum).build().perform(); /* this will perform right click */
	}
	
	public static void rightClickExample() throws Exception{
		String URL = "http://medialize.github.io/jQuery-contextMenu/demo.html";
		driver.navigate().to(URL);
		System.out.println("Page title is " + driver.getTitle());
		Thread.sleep(waitTime);
		WebElement element=driver.findElement(By.cssSelector(".context-menu-one"));
		Actions action=new Actions(driver);
		action.contextClick(element).build().perform();
		System.out.println("Sucessfully Right clicked on the element");
		
	}
	
	public static void backToAlbumsPage() throws Exception{
		Thread.sleep(waitTime);
		WebElement Back=driver.findElement(By.xpath(LibraryPage.BackToAlbumsLnk));
		Back.click();
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(LibraryPage.Album));		
		System.out.println("User sucessfully navigated back to Albums page");	
		
	}

	public static void NavToFriendsAlbumsPage() throws Exception{
		Thread.sleep(waitTime);
		WebElement AlbumsDropDown= driver.findElement(By.xpath(LibraryPage.AlbumsDropDownMenu));
		AlbumsDropDown.click();		
		Thread.sleep(waitTime);
		WebElement FriendsAlbumDropDown = driver.findElement(By.xpath(LibraryPage.FriendsAlbumsLnk));		
		FriendsAlbumDropDown.click();
		System.out.println("Navigated to Friends Albums page");				
	}
	
	public static void NavToMySharedAlbumsPage() throws Exception{
		Thread.sleep(waitTime);
		WebElement AlbumsDropDown= driver.findElement(By.xpath(LibraryPage.AlbumsDropDownMenu));
		AlbumsDropDown.click();		
		Thread.sleep(waitTime);
		WebElement MySharedAlbumDropDown = driver.findElement(By.xpath(LibraryPage.MySharedAlbumsLnk));		
		MySharedAlbumDropDown.click();
		System.out.println("Navigated to My Shared Albums page");				
	}
	public static void NavToFavoritePhotosPage() throws Exception{
		Thread.sleep(waitTime);
		WebElement AlbumsDropDown= driver.findElement(By.xpath(LibraryPage.AlbumsDropDownMenu));
		AlbumsDropDown.click();		
		Thread.sleep(waitTime);
		WebElement MyFavoritesDropDown = driver.findElement(By.xpath(LibraryPage.MyFavoritesLnk));		
		MyFavoritesDropDown.click();
		System.out.println("Navigated to My Favorites photos page");				
	}
	
	public static void NavToClusterViewPage() throws Exception{
		Thread.sleep(waitTime);
		WebElement PhotosTab = driver.findElement(By.xpath(LibraryPage.ClusterViewTab));		
		PhotosTab.click();
		System.out.println("Navigated to photos page/Cluster view/TimeLine view");
		Thread.sleep(waitTime);
		Thread.sleep(waitTime);
		if( driver.findElement(By.xpath(LibraryPage.clusterPrintAll)).isDisplayed()){
			System.out.println("Print all displayed in cluster view");
		}else
		{
			System.out.println("Print all is not displayed in cluster view");
		}
	}
	
	public static void SelectPhotosFromCluster() throws Exception {
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(ClusterViewPage.SelectIcon)).click();
		Thread.sleep(waitTime);
		if(driver.findElement(By.xpath(Tray.SelectedTnlImage)).isDisplayed()){
			System.out.println("Photo in selection tray is displayed/individual photo selection is success");
		}
		else{
			System.out.println("Photo in selection tray is NOT displayed");
		}
		if(!driver.findElement(By.xpath(ClusterViewPage.SelectAllIcon)).isSelected())
		{	
			System.out.println("Select all icon is not selected yet");
			driver.findElement(By.xpath(ClusterViewPage.SelectAllIcon)).click();
			System.out.println("Clicked on Select all icon for a cluster");
		}		
			
		
	}
	
	public static void openSelectedPhotosOverlayAndDeselectAll() throws Exception{
		try{
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(Tray.SelectedTnlImage)).click();
		System.out.println("Clicked on Selected Photo in tray");	
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(Tray.DeSelectAllLnk)).click();
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(Tray.BtnCancelDeselectAll)).click();
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(Tray.RemoveXIconOnPhoto)).click();
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(Tray.DeSelectAllLnk)).click();
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(Tray.BtnDeSelectAll)).click();
		Thread.sleep(waitTime);
		}
		catch(Exception e){
			System.out.println("Have an issue with selected photos overlay");
			
		}
		
	}
	public static void openUploaderWindow() throws Exception{
		Thread.sleep(waitTime);
		driver.findElement(By.xpath(LibraryPage.AddPhotosBtn)).click();
		Thread.sleep(waitTime);
		winHandleBefore = driver.getWindowHandle();
		
		driver.findElement(By.xpath(LibraryPage.MyDeviceLnk)).click();
		Thread.sleep(waitTime);
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}	
			
		System.out.println("Current page title is : "+driver.getTitle());
		System.out.println("Current URL in the browser is : "+driver.getCurrentUrl());
		driver.findElement(By.xpath(UploadLayer.SelectPhotosBtn)).click();
		
		driver.switchTo().window(winHandleBefore);
		System.out.println("Main window title is : "+driver.getTitle());
		
	}
	
	
	public static void reArrangePhotos() throws Exception{
		Thread.sleep(waitTime);		
		
		
		driver.findElement(By.xpath(SingleAlbumView.reArrangeIcon)).click();
		Thread.sleep(waitTime);	
		if(driver.findElement(By.xpath(SingleAlbumView.reArrangeCancel)).isDisplayed())
		{
			WebElement source = driver.findElement(By.xpath(SingleAlbumView.fifthImage));
			WebElement target = driver.findElement(By.xpath(SingleAlbumView.secondImage));
			(new Actions(driver)).dragAndDrop(source, target).perform();
			System.out.println("shuffeled the photos");
			driver.findElement(By.xpath(SingleAlbumView.reArrangeDone));
			Thread.sleep(waitTime);	
			System.out.println("Re-arrange is done successfully");
		}	
		
	}
	
	public static void verifyPhotosCount() throws Exception{
		
		Thread.sleep(waitTime);
		WebElement PhotosCountOnHeroImage = driver.findElement(By.xpath(SingleAlbumView.getPhotosCountOnHeroImage));
		System.out.println("Photos count on Hero image is : "+ PhotosCountOnHeroImage.getText());
		//By PhotosInSingleAlbumView = By.xpath(SingleAlbumView.getPhotosCountFromSingleAlbumView);
		//Integer getCount = By.xpath(count(SingleAlbumView.getPhotosCountFromSingleAlbumView));
		//System.out.println("Photos count in single album view is : "+ getCount);
		
		
		
		
	}
	
	public static void launchChromeBrowser() throws Exception{
		
				System.out.println("launching chrome browser");
				System.setProperty("webdriver.chrome.driver", chromeDriverPath+"chromedriver.exe");
				driver = new ChromeDriver();
				//driver.navigate().to("http://google.com");
				System.out.println("launched chrome browser");
	}
	
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
	
	public static void ldapLogin() throws Exception {
		
		driver.navigate().to(sbaseUrl);
        ldapURL = driver.getCurrentUrl();
		System.out.println("URL in the browser is "+ ldapURL+  "\n broswer title is:  "+driver.getTitle());
		//ldap-login
		try{
		  if (ldapURL.contains("ldap-login")){
			  closeForeseelayer();
			  driver.findElement(By.xpath(LdapLogin.ldapUserNameTxtbox)).sendKeys("yperaboina12");;
			  driver.findElement(By.xpath(LdapLogin.ldapPasswordTxtbox)).sendKeys("June*2018");;
			  driver.findElement(By.xpath(LdapLogin.ldapLoginBtn)).click();
			  Thread.sleep(waitTime);
			  System.out.println("broswer title after ldaplogin  "+driver.getTitle());
		  }
		}
		catch(Exception e){
			System.out.println("browser is not asking ldap login and broswer title is:  "+driver.getTitle() + "Exception is: " + e);
		}
		
	}
	
 public static void saveProject(int numberOfProjectstoSave) throws Exception {
		
		String canvasBuilderUrl= "https://us.sf-qa.com/create/builder?sku=CommerceProduct_10206&category=singlesurface&projectId=14226229060";
				driver.navigate().to(canvasBuilderUrl);
        
		System.out.println("\n URL in the browser is "+ driver.getCurrentUrl()+  "\n broswer title is:  "+driver.getTitle());
		 Thread.sleep(12000);
		for (int i=0;i<=numberOfProjectstoSave;i++){
		try{
		  if (driver.findElement(By.xpath(CanvasBuilderPage.CanvasSaveIcon)).isDisplayed()){
			  //closeForeseelayer();
			  driver.findElement(By.xpath(CanvasBuilderPage.CanvasSaveIcon)).click();
			  driver.findElement(By.xpath(CanvasBuilderPage.CanvasSaveAsLnk)).click();
			  driver.findElement(By.xpath(CanvasBuilderPage.CanvasProjectNameTxtBox)).clear();
			  driver.findElement(By.xpath(CanvasBuilderPage.CanvasProjectNameTxtBox)).sendKeys(i+"ABC");
			  driver.findElement(By.xpath(CanvasBuilderPage.CanvasSaveBtn)).click();
			  Thread.sleep(3000);
			  System.out.println("\n Project is saved and title is: "+driver.getTitle());
		  }
		}
		catch(Exception e){
			System.out.println("\n Project is not saved and " + "Exception is: " + e);
		}
		}
		
	}
 
 //This deleteProject() method is not working due to element issue on ProjectsPage.DeleteIconOnFirstProject
 public static void deleteProject(int numberOfProjectsToDelete) throws Exception {
		
		String ProjectsPageUrl= "https://us.sf-qa.com/library/projects?website=snapfish_us&cobrand=snapfish&locale=en_US";
				driver.navigate().to(ProjectsPageUrl);
     
		System.out.println("\n URL in the browser is "+ driver.getCurrentUrl()+  "\n broswer title is:  "+driver.getTitle());
		for (int i=0;i<=numberOfProjectsToDelete;i++){
		try{
		  if (driver.findElement(By.xpath(ProjectsPage.FirstProject)).isDisplayed()){
			  closeForeseelayer();
			  WebElement element = driver.findElement(By.xpath(ProjectsPage.FirstProject));
			  Actions action = new Actions(driver);			  
		      action.moveToElement(element).build().perform();
		        
		     // WebElement projectDeleteIcon = driver.findElement(By.xpath(ProjectsPage.DeleteIconOnFirstProject));
		      
			  driver.findElement(By.xpath(ProjectsPage.DeleteIconOnFirstProject)).click();
			  driver.findElement(By.xpath(ProjectsPage.DeleteBtnOnConfirmationLayer)).click();
			  
			  Thread.sleep(waitTime);
			  System.out.println("\n Project is Deleted successfully and title is: "+driver.getTitle());
		  }
		}
		catch(Exception e){
			System.out.println("\n Unable to delete the project and " + "Exception is: " + e);
		}
		}
		
	}
	
	public static void logout() throws Exception {
		driver.findElement(By.xpath(GlobalHeader.UserMenuDropdown)).click();
		driver.findElement(By.xpath(GlobalHeader.UserMenuDropdown_SignOutLnk)).click();
		Thread.sleep(waitTime);
		if(driver.findElement(By.xpath(GlobalHeader.HeaderSignIn)).isDisplayed()){
			System.out.println("User successfully logged out");
		}
		else{
			System.out.println("User not logged out yet and current title is: "+driver.getTitle() + " URL in the browser is: "+ driver.getCurrentUrl());
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		 long startTime = System.currentTimeMillis();
		 System.out.println("Start time is: "+ startTime);
		readRow(8);
		//launchChromeBrowser();
		launchFirefoxBrowser();
		//driver.manage().window().maximize();
		ldapLogin();
		login(sbaseUrl,EmailId,Password);
		//openLibraryPage();
		//verifyMyDeviceFBGoogleInstaFlickr();
		//verifyFacebookInNoPhotosPage();
		//verifyDefaultMessages();
		//openLibraryPage();
		//logout();
	    //openSingleAlbumPageWithExplicitWait();
		//openSingleAlbumPageRightClick();
		//scrollDownThePage();
		 //openSingleAlbumPage();
		//verifyPhotosCount();
		//reArrangePhotos();
      //openSingleAlbumPage();
     // savImageOperations();
      /*backToAlbumsPage();
		NavToFriendsAlbumsPage();
		openSingleAlbumPage();
		backToAlbumsPage();
		NavToMySharedAlbumsPage();
		openSingleAlbumPage();
		backToAlbumsPage();
		NavToFavoritePhotosPage();
		backToAlbumsPage();  */
		//NavToClusterViewPage();
		//SelectPhotosFromCluster();
		//openSelectedPhotosOverlayAndDeselectAll();
		 //openUploaderWindow();
		//rightClickExample();
		//saveProject(53); //- readrow(8);
		//deleteProject(0);
		
		 long stopTime = System.currentTimeMillis();
		 System.out.println("\n stop time is "+stopTime);
		 long elapsedTime = stopTime - startTime;
	      System.out.println("Execution Time in ms "+elapsedTime);
	      
	     System.out.println("mm:ss:SSS= " + new SimpleDateFormat("mm:ss:SSS").format(new Date(elapsedTime)));
	     System.out.println("total time in minutes is: " + ((elapsedTime)/1000)/60);	      
		//driver.quit();
	}

}
