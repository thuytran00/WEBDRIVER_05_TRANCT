package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;



public class Topic_04_Textbox_TextArea_Dropdownlist {
	WebDriver driver;
	WebDriverWait wait;
	String name,dob,address,city,pin,phone,email,password,customerID,state=null, newAddress,newCity;
	
	By nameTextbox=By.xpath("//input[@name='name']");
	By genderTextbox=By.xpath("//input[@name='gender']");
	By dobTextbox=By.xpath("//input[@name='dob']");
	By addressTextArea=By.xpath("//textarea[@name='addr']");
	By cityTextbox=By.xpath("//input[@name='city']");
	By pinTextbox=By.xpath("//input[@name='pinno']");
	By phoneTextbox=By.xpath("//input[@name='telephoneno']");
	By emailTextbox=By.xpath("//input[@name='emailid']");
	By passwordTextbox=By.xpath("//input[@name='password']");
	By stateTextbox=By.xpath("//input[@name='state']");
	
	@Test(enabled=false)
	public void TC_01_Dropdownlist() {
	  driver.get("http://daominhdam.890m.com/");
	  
	  Select jobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	  
	  //index: chỉ số 0/1/2/3/4
	  //jobRole.selectByIndex(0);
	  //Giá trị nằm trong attribute value
	 // jobRole.selectByValue("automation");
	  //Text
	  //jobRole.selectByVisibleText("Automation Test");
	  
	  AssertJUnit.assertTrue(!jobRole.isMultiple());
	  
	  jobRole.selectByVisibleText("Automation Tester");
	  
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Automation Tester");
	  
	  jobRole.selectByValue("manual");
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Manual Tester");
	  
	  jobRole.selectByIndex(3);
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Tester");
	  
	  int jobItems=jobRole.getOptions().size();
	  Assert.assertEquals(jobItems, 5);
	  
  }
	@Test
	public void TC_02_Custom_Dropdown() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.xpath("//span[@id='number-button']")).click();
		selectCustomDDL("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		
	}
	public void selectCustomDDL(String dropdown,String listItem, String valueItem)

	{
		driver.findElement(By.xpath(dropdown)).click();
		List <WebElement>allItems=driver.findElements(By.xpath(listItem));
	//	wait.until(ExpectedConditions.visibilityOfAllElements(allItems));
		for(WebElement item : allItems) {
					
			if(item.getText()==valueItem) {
				//scroll
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", item);
				
				item.click();
				break;
			}
			
		}
	}
	@Test(enabled=false)
	public void TC_03_Textbox_TextArea() {
	  driver.get("http://demo.guru99.com/v4/");
	  
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145848");;
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mupYvUb");;
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	  
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  driver.findElement(nameTextbox).sendKeys(name);
	  driver.findElement(dobTextbox).sendKeys(dob);
	  driver.findElement(addressTextArea).sendKeys(address);
	  driver.findElement(cityTextbox).sendKeys(city);
	  driver.findElement(stateTextbox).sendKeys(state);
	  driver.findElement(pinTextbox).sendKeys(pin);
	  driver.findElement(phoneTextbox).sendKeys(phone);
	  driver.findElement(emailTextbox).sendKeys(email);
	  driver.findElement(passwordTextbox).sendKeys(password);
	  
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  
	  customerID=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  //Ktra submit thanh cong
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	 
	  
	  driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	  driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	  
	  //check 3 fields: name/gender/birth disable
	  Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
	  Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
	  Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());
	  
	  //Ktra gia tri inbox
	  Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"),name);
	  Assert.assertEquals(driver.findElement(addressTextArea).getText(),address);
	  
	  //nhap gia tri moi
	  
	  driver.findElement(addressTextArea).clear();
	  driver.findElement(addressTextArea).sendKeys(newAddress);
	  driver.findElement(cityTextbox).clear();
	  driver.findElement(cityTextbox).sendKeys(newCity);
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  //ktra gia tri moi
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
    
	}
  
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  wait=new WebDriverWait(driver, 30);
	  driver.manage().window().maximize();
	 
	  //driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //DATA TEST
	  name="Automation Test";
	  dob="1995-01-02";
	  address="Dia chi";
	  city="HCM";
	  pin="123456";
	  phone="123456789";
	  email="mail"+randomUniqueNumber()+"@gmail.com";
	  password="123123";
	  state="HK";
	  newAddress="new Dc";
	  newCity="new thanh pho";
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public int randomUniqueNumber() {
	  Random rand=new Random();
	  int number=rand.nextInt(999999)+1;
	  return number;
  
  }
}
