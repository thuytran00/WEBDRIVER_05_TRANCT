package selenium_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_01_Annotations {

	WebDriver driver;
	@Test()
  public void TC_01_CheckUrl()
  {
		 System.out.println("Testcase 01");
		 String loginUrl = driver.getCurrentUrl();
		 Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
  }
  @Test()
  public void TC_02_CheckTitle()
  {
	  System.out.println("Testcase 02");
	  String title = driver.getTitle();
		 Assert.assertEquals(title, "Customer Login");
  }
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }
  @BeforeMethod
  public void beforeMethod() {
	 
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("After Method");
	
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Before Class");
	  System.out.println("Before Method");
	  driver= new FirefoxDriver();
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("After Class");
	  driver.quit();
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After Test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Before suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("After suite");
  }

}
