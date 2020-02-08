package stepDefinitions; 

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given; 
import cucumber.api.java.en.Then; 
import cucumber.api.java.en.When; 
import org.junit.*; 

public class WordPressStepDefinition { 

	WebDriver driver; 

	@Given("^Start chrome browser and open the Wordpress application$") 
	public void OpenBrowser() throws Throwable { 
		System.setProperty("webdriver.chrome.driver","R:\\Ramesh\\SupportingFiles\\drivers\\chromedriver_2.40.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		System.out.println("Launch WordPress");
		driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php"); 
	} 

	@When("^Enter UserName and Password and Click on Login button$") 
	public void  LogiIntoWordpressApplication() { 
		driver.findElement(By.xpath("//*[@id=\"user_login\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"user_login\"]")).sendKeys("opensourcecms");
		driver.findElement(By.xpath("//*[@id=\"user_pass\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"user_pass\"]")).sendKeys("opensourcecms");
		driver.findElement(By.xpath("//*[@id=\"wp-submit\"]")).click();
	} 

	@Then("^Validate the landing page text \"([^\"]*)\" is displayed$") 
	public void  validateResult(String expTxt) throws Throwable { 
		String actTxt = driver.findElement(By.xpath("//*[@id=\"welcome-panel\"]/div/h2")).getText(); 
		Assert.assertEquals(expTxt, actTxt); 
		//driver.close();
	} 

	//@AfterScenario
	public void tearDown() { 
		driver.close(); 
	} 
} 
