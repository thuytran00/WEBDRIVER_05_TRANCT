package selenium_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class TestNG_05_Parallel {
	WebDriver driver;
	  @Test
	  public void TC_01_LoginToSystem() {
		  driver.findElement(By.xpath("//*[@id='email']")).sendKeys("automationvalid_01@gmail.com");
		  driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
		  driver.findElement(By.xpath("//*[@id='send2']")).click();
		  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  }
	  @Parameters("browser")
	  @BeforeClass
	  public void beforeClass(String browserName) throws InterruptedException {
		 
		 driver = new FirefoxDriver();
		 if(browserName.equals("chrome")) {
			 System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			 driver = new ChromeDriver();
		 }else if (browserName.equals("firefox")) {
			 driver = new FirefoxDriver();
			 
		 }else {
			 System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			 driver= new InternetExplorerDriver();
			 
		 }
		 
		 driver.get("http://live.guru99.com/index.php/customer/account/login/");
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @AfterClass
	  public void afterClass() {
		  
		  driver.quit();
	  }
	  @BeforeMethod
	  public void beforeMethod() {
		 // driver= new FirefoxDriver();
		 // driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  }

	  @AfterMethod
	  public void afterMethod() {
		 
		
	  }

}
