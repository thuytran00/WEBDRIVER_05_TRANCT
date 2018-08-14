package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
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
  @Test
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
		  
		  driver.switchTo().defaultContent();;;;;;;;;;
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
  
  @Test
  public void TC_02() {
	  
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
  	
}
