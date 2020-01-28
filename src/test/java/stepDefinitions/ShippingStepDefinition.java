package stepDefinitions; 

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given; 
import cucumber.api.java.en.Then; 
import cucumber.api.java.en.When; 
import org.junit.*; 

public class ShippingStepDefinition { 

	WebDriver driver; 

	@Given("^Start chrome browser and open the shipping application$") 
	public void SetUp() throws Throwable { 
		System.setProperty("webdriver.chrome.driver","R:\\Ramesh\\SupportingFiles\\drivers\\chromedriver_2.40.exe");
		System.out.println("Launch Shipping");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("http://apps.qa2qe.cognizant.e-box.co.in/shippingDetails/"); 
	} 

	@When("^Test the text in title tag and the \"([^\"]*)\" for ShipmentID$") 
	public void  testShippingDetails(String arg1) throws Throwable { 
		String H2Text =  driver.findElement(By.tagName("h2")).getText();  
		System.out.println("H2Text: "+H2Text);
		Assert.assertEquals(H2Text,"Shipping Details"); 
		WebElement linkname = driver.findElement(By.linkText(arg1)); 
		Assert.assertTrue(linkname.isDisplayed()); 
		linkname.click(); 

	} 

	@Then("^Validate the Customer name \"([^\"]*)\" is displayed$") 
	public void  validateResult(String arg1) throws Throwable { 
		String name = driver.findElement(By.xpath(".//*[@id='result']/table/tbody/tr[2]/td[1]")).getText(); 
		Assert.assertEquals(arg1, name); 
		//driver.close();
	} 

	//@AfterScenario
	public void tearDown() { 
		driver.close(); 
	} 
} 
