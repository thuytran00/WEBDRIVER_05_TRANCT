package selenium_api;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_05_Button_Radio_Checkbox_Alert {
	WebDriver driver;
  @Test(enabled=false)
  public void TC_01() {
	  driver.get("http://live.guru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	 
	 // driver.findElement(By.xpath("//a[@class='button']")).click();
	 // Assert.assertTrue(driver.findElement(By.xpath("//form[@id='search_mini_form']")).isDisplayed());
	 // Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
	  //javascript
	  clickElementByJavascript("//a[@title='Create an Account']");
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	 
  }
  @Test(enabled=false)
  public void TC_02() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  // khong lay theo text vi ham Assert khong hieu selected
	  //WebElement dualZoneCheckbox =driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	  //dualZoneCheckbox.click();
	  //Assert.assertTrue(dualZoneCheckbox.isSelected());
	  String dualZoneCheckbox="//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
	  clickElementByJavascript(dualZoneCheckbox);
	  Assert.assertTrue(isElementSelected(dualZoneCheckbox));
	  unCheckTheCheckbox(dualZoneCheckbox);
	  Assert.assertFalse(isElementSelected(dualZoneCheckbox));
	  
  }
  @Test(enabled=false)
  public void TC_03() {
	  driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
	  String radioButton="//label[@for='engine3']/preceding-sibling::input";
	  clickElementByJavascript(radioButton);
	  if(isElementSelected(radioButton)==false) {
		  clickElementByJavascript(radioButton);
	  }
	  
	   
  }
  @Test(enabled=false)
  public void TC_04() {
	  driver.get("http://daominhdam.890m.com/");
	  //JS Alert
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  Alert alert=driver.switchTo().alert();
	  String text=alert.getText();
	  Assert.assertEquals(text, "I am a JS Alert");
	  alert.accept();
	  WebElement result= driver.findElement(By.xpath("//p[@id='result']"));
	  Assert.assertEquals(result.getText(), "You clicked an alert successfully");
	  
  }
  @Test
  public void TC_05() {
	  driver.get("http://daominhdam.890m.com/");
	  
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  Alert alert=driver.switchTo().alert();
	  String text=alert.getText();
	  Assert.assertTrue(text.equals("I am a JS Confirm"));
	  alert.dismiss();
	  WebElement result= driver.findElement(By.xpath("//p[@id='result']"));
	  Assert.assertEquals(result.getText(), "You clicked: Cancel");
  }
  @Test
  public void TC_06() {
driver.get("http://daominhdam.890m.com/");
	  
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  Alert alert=driver.switchTo().alert();
	  String text=alert.getText();
	  Assert.assertTrue(text.equals("I am a JS prompt"));
	  alert.sendKeys("thuytran");
	  alert.accept();
	  WebElement result= driver.findElement(By.xpath("//p[@id='result']"));
	  Assert.assertEquals(result.getText(), "You entered: thuytran");
  }
  public void unCheckTheCheckbox(String locator) {
	  if(isElementSelected(locator)) {
		  clickElementByJavascript(locator);
	  }
  }
  public boolean isElementSelected(String locator) {
	  WebElement element=driver.findElement(By.xpath(locator));
	  return element.isSelected();
  }
  public void clickElementByJavascript(String locator) {
	  WebElement element=driver.findElement(By.xpath(locator));
	  
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
