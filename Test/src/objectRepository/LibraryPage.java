package objectRepository;
import properties.*;
import java.util.Properties;

import framework.Constants;

public class LibraryPage {
	public static String Album="//div[@class='story-caption-area']";	
	public static String BackToAlbumsLnk="//li[contains(text(),'Back')]";
	public static String AlbumsDropDownMenu="//div[@id='sub_header_container']/div/div/ul[2]/li";
	public static String FriendsAlbumsLnk="//a[@data-value='swmav']";
	public static String MySharedAlbumsLnk="//a[@data-value='msav']";
	public static String MyFavoritesLnk="//a[@data-value='fsav']";
	public static String ClusterViewTab="//li[@data-btabname='tlv']";
	public static String clusterPrintAll="//div[@title='Order prints of this cluster']";
	public static String title=Constants.title;
	
	public static  String AddPhotosBtn="//ul[@id='addPhotosSection']";
	public static String MyDeviceLnk="//li[@class='device-upload mycomputer_link']";
	public static String SelectUploadWindow="name=uploadphotos";
	public static String NoPhotosTxt1="//div[@class='no_photos_text']";
	public static String NoPhotosTxt2="//div[@class='upload_photos_text']";
	public static String FriendsAlbumsDefaultTxt="//div[@class='emptytxtdiv']/p";
	public static String MySharedAlbumsDefaultTxt="//div[@class='emptytxtdiv']/p";
	public static String MyFavoritesAlbumsDefaultTxt1="//div[@class='emptytxtdiv']/p";
	public static String MyFavoritesAlbumsDefaultTxt2="//div[@class='favemptytxt']";
	public static String MyDeviceIcon="//a/div[@class='default_snimg mydevice_img']";
	public static String FacebookIcon="//a/div[@class='default_snimg facebook_img']";
	public static String GoogleIcon="//a/div[@class='default_snimg google_photos_img']";
	public static String InstagramIcon="//a/div[@class='default_snimg instagram_img']";
	public static String FlickrIcon="//a/div[@class='default_snimg flickr_img']";

}
