package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_10_WebDriver_Wait {
	WebDriver driver;
	WebDriverWait waitExplicit;
  @Test(enabled=false)
  public void TC_01() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  //wait
	  driver.findElement(By.xpath("//*[@id='start']/button")).click();
	  //wait
	  Assert.assertEquals(driver.findElement(By.xpath("//*[@id='finish']/h4")).getText(), "Hello World!");
  }
  @Test(enabled=false)
  public void TC_02() {
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  //wait picker dung precence hoac visible
	  waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Panel1']/div")));
	  //waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Panel1']/div")));
	  waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='raDiv']")));
	  
	  WebElement todayBefore=driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Label1']"));
	  Assert.assertEquals(todayBefore.getText(), "No Selected Dates to display.");
	  
	  driver.findElement(By.xpath("//a[text()='9']/parent::td")).click();
	  //wait loading
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
	  
	 //waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")));
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")).isDisplayed());
	  
	  WebElement todayAfter= driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Label1']"));
	  Assert.assertEquals(todayAfter.getText(), "Sunday, September 09, 2018");
  }
  @Test
  public void TC_03() {
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  WebElement countdount= driver.findElement(By.xpath("//*[@id='javascript_countdown_time']"));
	// Khởi tạo Fluent wait
	  new FluentWait<WebElement>(countdount)
	             // Tổng time wait là 15s
	             .withTimeout(15, TimeUnit.SECONDS)
	              // Tần số mỗi 1s check 1 lần
	              .pollingEvery(1, TimeUnit.SECONDS)
	             // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
	              .ignoring(NoSuchElementException.class)
	              // Kiểm tra điều kiện
	              .until(new Function<WebElement, Boolean>() {
	                  public Boolean apply(WebElement element) {
	                             // Kiểm tra điều kiện countdount = 00
	                             boolean flag =  element.getText().endsWith("00");
	                             System.out.println("Time = " +  element.getText());
	                             // return giá trị cho function apply
	                             return flag;
	                        }
	                 });
  }
  @BeforeMethod
  public void beforeMethod() {
	  driver=new FirefoxDriver();
	  waitExplicit=new WebDriverWait(driver, 10);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
