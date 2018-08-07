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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Topic_06_UserInteractions {
	WebDriver driver;
  @Test(enabled=false)
  public void TC_01_HoverMouse() throws InterruptedException {
	  driver.get("http://daominhdam.890m.com/");
	  
	  WebElement hoverText=driver.findElement(By.xpath("//a[text()='Hover over me']"));
	 // hoverText.click();
	  Actions action = new Actions(driver);
	  //tuong tu click element
	  //action.click(hoverText);
	  action.moveToElement(hoverText).perform();
	  Thread.sleep(5000);
	  AssertJUnit.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']/div[@class='tooltip-inner']")).getText(),"Hooray!");
	  
  }
  
  @Test(enabled=false)
  public void TC_02_ClickHold()  {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List<WebElement>selectable=driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
	  
	 // Actions action=new Actions(driver);
	 // action.clickAndHold(selectable.get(0)).moveToElement(selectable.get(3)).release().perform();
	  
	 // List<WebElement>selectableSelected=driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	 // Assert.assertEquals(selectableSelected.size(), 4);
	  
	  Actions action=new Actions(driver);
	  //Giả lập nhấn phím Ctrl xuống
	  action.keyDown(Keys.CONTROL).build().perform();
	  
	  selectable.get(0).click();
	  selectable.get(2).click();
	  selectable.get(4).click();
	  selectable.get(6).click();
	  // nhả phím Ctrl
	  action.keyUp(Keys.CONTROL).build().perform();
  }
  @Test(enabled=false)
  public void TC_03_DoubleClick() throws InterruptedException  {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement doubleClicktext=driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  Actions action=new Actions(driver);
	  
	  action.doubleClick(doubleClicktext).perform();
	  Thread.sleep(5000);
	  Alert alert=driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
  }
  @Test(enabled=false)
  public void TC_04_RightClick()  {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  WebElement rightclickButton= driver.findElement(By.xpath("//span[text()='right click me']"));
	  Actions action=new Actions(driver);
	  //Right click
	  action.contextClick(rightclickButton);
	  
	  WebElement quitBefore=driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
	  //Hover to Quit
	  action.moveToElement(quitBefore).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit context-menu-visible')]")).isDisplayed());
	  //Click to Quit
	  
	  action.click(quitBefore).perform();
	  	  
	  Alert alert=driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "clicked: quit");
	  alert.accept();
  }
  @Test(enabled=false)
  public void TC_05_DragDrop() throws InterruptedException  {
	  
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  
	  WebElement sourceElement=driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetElement=driver.findElement(By.xpath("//div[@id='droptarget']"));
	  Assert.assertEquals(targetElement.getText(),"Drag the small circle here.");
	  
	  Actions action=new Actions(driver);
	  action.dragAndDrop(sourceElement, targetElement).perform();
	  Thread.sleep(4000);
	  Assert.assertEquals(targetElement.getText(),"You did great!");
	  
  }
  @Test(enabled=true)
  public void TC_06_DragDrop() throws InterruptedException  {
      driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
	  
	  WebElement sourceElement=driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetElement=driver.findElement(By.xpath("//div[@id='droppable']"));
	  Assert.assertEquals(targetElement.getText(),"Drop here");
	  
	  Actions action=new Actions(driver);
	  action.dragAndDrop(sourceElement, targetElement).perform();
	  Thread.sleep(4000);
	  Assert.assertEquals(targetElement.getText(),"Dropped!");
  }
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
