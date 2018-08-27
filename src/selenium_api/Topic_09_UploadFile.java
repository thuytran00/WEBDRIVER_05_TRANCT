package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.FirefoxChromeLauncher.ChromeUrlConvert;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class Topic_09_UploadFile {
	WebDriver driver;
	//tuyet doi
//	String uploadFilePath="C:\\Users\\tranct\\Downloads\\git 0708\\WEBDRIVER_05_TRANCT\\img\\upload.jpg";
	//tuong doi
	String projectDirectory=System.getProperty("user.dir");
	String filename="upload.jpg";
	String filename1="upload1.jpg";
	String uploadFilePath= projectDirectory+"\\img\\"+filename;
	String uploadFilePath1= projectDirectory+"\\img\\"+filename1;
	String chromeUpload= projectDirectory+"\\upload\\chrome.exe";
	String firefoxUpload= projectDirectory+"\\upload\\firefox.exe";
	String ieUpload= projectDirectory+"\\upload\\ie.exe";
	String folderName = "online05"+ randomNumber();
	String email="autoonline"+randomNumber()+"@gmail.com";
	String name ="Automation FC";
	
	
	  @Test(enabled=false)
	  public void TC_01_SendKeyAPI() throws InterruptedException {
		  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		  WebElement uploadElement=driver.findElement(By.xpath("//input[@type='file']"));
		  //upload nhiều file cùng lúc
		  String summaryfile="\""+uploadFilePath+"\" \""+uploadFilePath1+"\"";
		  uploadElement.sendKeys(uploadFilePath);
		 Thread.sleep(3000);
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+filename+"']")).isDisplayed());
		  
		  driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+filename+"']")).isDisplayed());
	  }
	  @Test(enabled=false)
	  public void TC_02_AutoIT() throws Exception {
		  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		  WebElement uploadElement=driver.findElement(By.cssSelector(".fileinput-button"));
		  uploadElement.click();
		 
		  
		  Runtime.getRuntime().exec(new String[] {firefoxUpload, uploadFilePath});
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+filename+"']")).isDisplayed());
		  
		  driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+filename+"']")).isDisplayed());
	  }
	  @Test(enabled=false)
	  public void TC_03_Robot() throws InterruptedException, Exception {
		  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		  WebElement uploadElement=driver.findElement(By.cssSelector(".fileinput-button"));
		  uploadElement.click();
		  
		//Specify the file location with extension
		  StringSelection select = new StringSelection(uploadFilePath);

		  //Copy to clipboard
		  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		  //Click
		  driver.findElement(By.className("fileinput-button")).click();

		  Robot robot = new Robot();
		 
		  //focus to textbox
		  robot.keyPress(KeyEvent.VK_ENTER);//nhấn phím
		  robot.keyRelease(KeyEvent.VK_ENTER);//nhả phím
		  Thread.sleep(3000);
		  robot.keyPress(KeyEvent.VK_CONTROL);
		  robot.keyPress(KeyEvent.VK_V);
		  Thread.sleep(3000);
		  robot.keyRelease(KeyEvent.VK_CONTROL);
		  robot.keyRelease(KeyEvent.VK_V);
		  Thread.sleep(3000);

		  robot.keyPress(KeyEvent.VK_ENTER);
		  robot.keyRelease(KeyEvent.VK_ENTER);
		  
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+filename+"']")).isDisplayed());
		  
		  driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		  Thread.sleep(3000);
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+filename+"']")).isDisplayed());
	  }
	  @BeforeClass
	  public void beforeClass() {
		  
		  //Chrome
		 // System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");
		//  driver= new ChromeDriver();
		  
		 //IE
		 // System.setProperty("webdriver.ie.driver",".\\driver\\IEDriverServer.exe");
		 // driver=new InternetExplorerDriver();
		 
		  driver=new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		 
	  }
	  @Test(enabled=false)
	  public void TC_04_UploadFileChucker() throws InterruptedException, Exception {
		  
		  driver.get("https://encodable.com/uploaddemo/");
		  driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePath);
		  WebElement uploadDropdown = driver.findElement(By.xpath("//select[@name='subdir1']"));
		  Select select =new Select(uploadDropdown);
		  select.selectByVisibleText("/uploaddemo/files/");
		  driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(folderName);
		  driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);
		  driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(name);
		  driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='Email Address: "+email+"']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='First Name: "+name+"']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dt[contains(text(),'File 1 of 1:')]/a[text()='"+filename+"']")).isDisplayed());
		  
		  
		  driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		  driver.findElement(By.xpath("//a[text()='"+folderName+"']")).click();
		  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());
		  
	  }
	 
	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
	  public int randomNumber() {
		  Random random=new Random();
		  return random.nextInt(999999);
	  }
	}
