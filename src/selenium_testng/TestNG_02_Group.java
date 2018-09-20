package selenium_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_02_Group {

	@Test(groups="cus")
	public void TC_01_()
	  {
			 System.out.println("Testcase 01");
			
	  }
	  @Test(groups="pay")
	  public void TC_02_()
	  {
		  System.out.println("Testcase 02");
	  }
	  @Test(groups="man")
	  public void TC_03_()
	  {
		  System.out.println("Testcase 03");
	  }
	  @Test(groups="buy")
	  public void TC_04_()
	  {
		  System.out.println("Testcase 04");
	  }
	  @Test(groups="cus")
	  public void TC_05_()
	  {
		  System.out.println("Testcase 05");
	  }

}
