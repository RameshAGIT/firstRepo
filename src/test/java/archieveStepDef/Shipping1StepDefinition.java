package archieveStepDef;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.firefox.FirefoxDriver; 
 
import cucumber.api.java.After; 
import cucumber.api.java.Before; 
import cucumber.api.java.en.Given; 
import cucumber.api.java.en.Then; 
import cucumber.api.java.en.When; 


public class Shipping1StepDefinition {
	
	WebDriver driver; 
	String result; 
	 
	@Before 
	public void SetUp() throws Throwable { 
	System.setProperty("webdriver.chrome.driver","R:\\Ramesh\\SupportingFiles\\drivers\\chromedriver_2.40.exe");
	driver = new ChromeDriver();
	//System.setProperty("webdriver.firefox.marionette","R:\\Ramesh\\SupportingFiles\\drivers\\geckodriverv0.26.0.exe"); 
	//driver = new FirefoxDriver(); 
	driver.manage().window().maximize(); 
	 
	} 
	 
	@Given("^Load the application from the given URL$") 
	public void loadURL() throws Throwable { 
	driver.get("http://apps.qa2qe.cognizant.e-box.co.in/CostCalculation/"); 
	} 
	 
	@When("^Test the cost calculation by giving inputs \"([^\"]*)\" and \"([^\"]*)\"$") 
	public void testCalculateCost(String arg1, String arg2) throws Throwable { 
	driver.findElement(By.id("weight")).sendKeys(arg2); 
	driver.findElement(By.id(arg1)).click(); 
	driver.findElement(By.id("calculate")).click(); 
	} 
	 
	@Then("^Validates results of the test$") 
	public void validateResult() throws Throwable { 
	result = driver.findElement(By.id("result")).getText(); 
	System.out.println("Result: "+result); 
	} 
	 
	@After 
	public void tearDown() throws Throwable { 
	driver.close(); 
	 
	} 
	
}
