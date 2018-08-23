package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_JavascriptExecutor {
	WebDriver driver;
  @Test(enabled=false)
  public void TC_01_JavascriptExecutor() {
	  openAnyUrlByJS("http://live.guru99.com/");
	  
	  String homePageDomain= (String) executeJSForWebBrowser("return document.domain;");
	  Assert.assertEquals(homePageDomain, "live.guru99.com");
	  
	  String homePageUrl= (String) executeJSForWebBrowser("return document.URL;");
	  Assert.assertEquals(homePageUrl, "http://live.guru99.com/");
	  
	  WebElement mobilelink = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  clicktoElementbyJS(mobilelink);
	  
	  WebElement samsungProduct = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]//following-sibling::div[@class='actions']/button"));
	  clicktoElementbyJS(samsungProduct);
	  
	  String samsungAddedMsg= (String)executeJSForWebBrowser("return document.documentElement.innerText;");
	  Assert.assertTrue(samsungAddedMsg.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  WebElement privatelink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  clicktoElementbyJS(privatelink);
	  
	  String privacyPageTitle= (String) executeJSForWebBrowser("return document.title;");
	  Assert.assertEquals(privacyPageTitle, "Privacy Policy");
	  
	  scrollToBottomPage();
	  
	  WebElement wishlistTableContent = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
	  Assert.assertTrue(wishlistTableContent.isDisplayed());
	  
	  driver.get("http://demo.guru99.com/v4/ ");
	  
	  String demoPageDomain= (String) executeJSForWebBrowser("return document.domain;");
	  Assert.assertEquals(demoPageDomain, "demo.guru99.com");
  }
  @Test
  public void TC_02_RemoveAttributeInDOM() {
	  driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
	  String firstname="Automation", lastname="Testing";
	  
	  WebElement resultIframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
	  driver.switchTo().frame(resultIframe);
	  driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(firstname);
	  
	  WebElement lastnameTextbox = driver.findElement(By.xpath("//input[@name='lname']"));
	  removeAnyAttributeinDOM(lastnameTextbox, "disabled");
	  lastnameTextbox.sendKeys(lastname);
	
	  
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  String messageSuccess = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
	  Assert.assertTrue(messageSuccess.contains(firstname) && messageSuccess.contains(lastname));
  }
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public Object removeAnyAttributeinDOM(WebElement element,String attributeName) {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
      
  }
  public Object openAnyUrlByJS(String url) {
	  JavascriptExecutor js= (JavascriptExecutor)driver;
	  return js.executeScript("window.location='"+url+"'");
  }
  public void highlightElement(WebDriver driver, WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.border='6px groove red'", element);
}
  public Object executeJSForWebBrowser(String javaSript) {
      try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript(javaSript);
      } catch (Exception e) {
                  e.getMessage();
                  return null;
      }
}
  public Object clicktoElementbyJS(WebElement element) {
      try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript("arguments[0].click();", element);
      } catch (Exception e) {
                  e.getMessage();
                  return null;
      }
}
 
  public Object scrollToBottomPage() {
      try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
      } catch (Exception e) {
                  e.getMessage();
                  return null;
      }
}

}
