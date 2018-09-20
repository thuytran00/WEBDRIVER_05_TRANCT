package selenium_testng;

import org.testng.annotations.Test;

public class TestNG_03_Priority_Skip {
	@Test(groups="cus", enabled= false)
	public void TC_01_New()
	  {
			 System.out.println("Testcase 01");
			
	  }
	  //@Test(groups="pay")
	  public void TC_02_Edit()
	  {
		  System.out.println("Testcase 02");
	  }
	  @Test(groups="man")
	  public void TC_03_Del()
	  {
		  System.out.println("Testcase 03");
	  }
	  @Test(groups="buy",enabled= false)
	  public void TC_04_Manager()
	  {
		  System.out.println("Testcase 04");
	  }
	  @Test(groups="cus")
	  public void TC_05_Transfer()
	  {
		  System.out.println("Testcase 05");
	  }
}
