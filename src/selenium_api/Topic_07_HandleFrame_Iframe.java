package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_07_HandleFrame_Iframe  {
	WebDriver driver;
	WebDriverWait wait;
  @Test(enabled=false)
  public void TC_01() {
	  driver.get("https://www.hdfcbank.com/");
	  
	  //switch to iframe
	  List<WebElement> notificationIframe=driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	 // WebElement notificationIframe=driver.findElement(By.xpath("//iframe[@id='vizury-notification-template']"));
	  //driver.switchTo().frame(notificationIframe);
	  if(notificationIframe.size() > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		WebElement closeIcon=  driver.findElement(By.xpath("//div[@id='div-close']"));
		  JavascriptExecutor js=(JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", closeIcon);
		  System.out.println("Closed popup");
		  
		  driver.switchTo().defaultContent();
	  }
	  
	  //close popup
	  //WebElement imageClose= driver.findElement(By.xpath("//div[@id='container-div']/img"));
	  //if(imageClose.isDisplayed()) {
	//  driver.findElement(By.xpath("//div[@id='div-close']")).click();
	  //}
	  
	  //step 03
	  WebElement lookingForIframe=driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(lookingForIframe);
	  String lookingForText=driver.findElement(By.xpath("//span[@id='messageText']")).getText();
	  AssertJUnit.assertEquals(lookingForText, "What are you looking for?");
	  driver.switchTo().defaultContent();
	  //step 04
	  WebElement bannerIframe=driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(bannerIframe);
	  
	  By bannerImagesXpath=By.xpath("//div[@id='productcontainer']//img");
	  List<WebElement> bannerImages=driver.findElements(By.xpath("//div[@id='productcontainer']//img"));
	  int bannerImageNumber = bannerImages.size();
	 
	  AssertJUnit.assertEquals(bannerImageNumber, 6);

		  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImagesXpath));
		  driver.switchTo().defaultContent();    
		  
		  //Step5
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed(), true);
		  
		  List<WebElement> flipBannerImages=driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		  int flipBannerImageNumber = flipBannerImages.size();
		  Assert.assertEquals(flipBannerImageNumber, 8);
		  int i=0;
		  
		  for(WebElement image : flipBannerImages) {
			  Assert.assertTrue(image.isDisplayed());
			  i++; 
			  System.out.println("Image [" + i +"] displayed");
			  
		  }
  }
  
  @Test(enabled=false)
  public void TC_02_Windows() {
	  driver.get("http://daominhdam.890m.com/");
	  String parentGUID=driver.getWindowHandle();
	  System.out.println("Title before= "+driver.getTitle());
	  
	  driver.findElement(By.xpath("//a[text()='Click Here']")).click();
	  switchToChildWindowGUID(parentGUID);
	  
	  String googleTilte=driver.getTitle();
	  System.out.println("Title after: "+googleTilte);
	  Assert.assertEquals(googleTilte, "Google");
	  closeAllWithoutParentWindows(parentGUID);
	  Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	  
  }
  @Test
  public void TC_03_BankingWindows() {
	  driver.get("http://www.hdfcbank.com/");
	  String parentGUID=driver.getWindowHandle();
	  
	  overrideGlobalWait(5);
	  //switch to iframe
	  List<WebElement> notificationIframe=driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	 // WebElement notificationIframe=driver.findElement(By.xpath("//iframe[@id='vizury-notification-template']"));
	  //driver.switchTo().frame(notificationIframe);
	  if(notificationIframe.size() > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		WebElement closeIcon=  driver.findElement(By.xpath("//div[@id='div-close']"));
		  JavascriptExecutor js=(JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", closeIcon);
		  System.out.println("Closed popup");
		  
		  driver.switchTo().defaultContent();
	  }
	  driver.findElement(By.xpath("//a[text()='Agri']")).click();
	  switchToChildWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	  
	  //click account detail
	  driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	  switchToChildWindowByTitle("Welcome to HDFC Bank NetBanking");
	  
	  //switch to footer frame
	  WebElement footerframe = driver.findElement(By.xpath("//frame[@name='footer']"));
	  driver.switchTo().frame(footerframe);
	  driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
	  switchToChildWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  
	  //switch CSR
	  driver.findElement(By.xpath("//a[text()='CSR']")).click();
	  closeAllWithoutParentWindows(parentGUID);
	  Assert.assertEquals(driver.getTitle(), "HDFC Bank: Personal Banking Services");
  }
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  wait=new WebDriverWait(driver, 10);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  	public void switchToChildWindowGUID(String parentID) {
  		//Get all current window
  		Set <String> allWindows = driver.getWindowHandles();
  		
  		//Duyet tat ca cas windows/tabs
  		for(String runWindow : allWindows) {
  			//Neu window/tab ID nao khac vs parent ID thi switch qua
  			if(!runWindow.equals(parentID)) {
  				driver.switchTo().window(runWindow);
  				break;
  			}
  		}
  	}
  	
  	//TH nhieu hon 2 window cung mo
  	public void switchToChildWindowByTitle(String expectedTitle) {
  		//Get all current window
  		Set <String> allWindows = driver.getWindowHandles();
  		
  		//Duyet tat ca cas windows/tabs
  		for(String runWindow : allWindows) {
  			//switch vao tung window
  			driver.switchTo().window(runWindow);
  			//get ra title da switch qua
  			String actualTitle=driver.getTitle();
  			//Kiem tra title hien tai vaf mong muon
  			if(actualTitle.equals(expectedTitle)) {
  			//	driver.switchTo().window(runWindow);
  				break;
  			}
  		}
  	}
  	//Dong tat ca window ngoai tru parent window
  	public boolean closeAllWithoutParentWindows(String parentGUID) {
  		//Get all current window
  		Set <String> allWindows = driver.getWindowHandles();
  		
  		//Duyet tat ca cas windows/tabs
  		for(String runWindow : allWindows) {
  			
   			//Kiem tra title hien tai vaf mong muon
  			if(!runWindow.equals(parentGUID)) {
  				driver.switchTo().window(runWindow);
  				driver.close();
  			}
  		}
  		driver.switchTo().window(parentGUID);
  		//kiem tra xem con dung 1 window hay ko
  		if(driver.getWindowHandles().size()==1)
  			return true;
  		else
  			return false;
  	}
  	public void overrideGlobalWait(long timeout) {
  		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
  	}
}
