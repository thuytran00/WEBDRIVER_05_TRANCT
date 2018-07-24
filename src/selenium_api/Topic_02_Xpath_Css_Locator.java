package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Topic_02_Xpath_Css_Locator {
	WebDriver driver;
  
 @BeforeClass
	 public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
 
 @Test
 public void TC_01_CheckNavigatePage() {
	 
	 driver.get("http://live.guru99.com");
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 String homePageTitle = driver.getTitle();
	 Assert.assertEquals(homePageTitle, "Home page");
	 
	 driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
	 driver.navigate().back();
	 //ve lai page Login thanh cong
	 Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());

	 String loginUrl = driver.getCurrentUrl();
	 Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
	 
	 driver.navigate().forward();
	 Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());

	 String registerUrl = driver.getCurrentUrl();
	 Assert.assertEquals(registerUrl, "http://live.guru99.com/index.php/customer/account/create/");
	 
 }
 @Test(enabled=false)
  public void TC_02_LoginWithUserPassEmpty() {
  
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.id("send2")).click();
	  
	  String usernameEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(usernameEmptyMessage, "This is a required field.");
	  
	  String passwordEmptyMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(passwordEmptyMessage, "This is a required field.");
  }
 @Test(enabled=false)
  public void TC_03_LoginWithEmailInvalid() {
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  //input email
	  driver.findElement(By.cssSelector("#email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.id("send2")).click();
	  
	  String usernameInvalidMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(usernameInvalidMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	  
	
  }
 @Test(enabled=false)
  public void TC_04_LoginWithPassIncorrect() {
  
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  //input email
	  driver.findElement(By.cssSelector("#pass")).sendKeys("12345");
	  driver.findElement(By.id("send2")).click();
	  
	  String passwordInvalidMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(passwordInvalidMessage, "Please enter 6 or more characters without leading or trailing spaces.");
	  
	
  }
 @Test(enabled=false)
  public void TC_05_CreateAnAccount() {
  
	  driver.get("http://live.guru99.com/index.php");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		 
	  //input email
	  driver.findElement(By.cssSelector("#firstname")).sendKeys("Chung");
	  driver.findElement(By.cssSelector("#middlename")).sendKeys("Thuy");
	  driver.findElement(By.cssSelector("#lastname")).sendKeys("Tran");
	  driver.findElement(By.cssSelector("#email_address")).sendKeys("automation" + randomEmail() + "@gmail.com");
	  driver.findElement(By.cssSelector("#password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
	  

	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  
	  String successMessage = driver.findElement(By.cssSelector(".success-msg")).getText();
	  Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
	  driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	  try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  String homepageText = driver.findElement(By.cssSelector(".page-title")).getText();
	  Assert.assertEquals(homepageText, "THIS IS DEMO SITE FOR   ");
	
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int randomEmail() {
	  Random random =new Random();
	  int number = random.nextInt(999999);
	  return number;
  }
}
