package archieveStepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleSearch1 {
	public WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver","R:\\Ramesh\\SupportingFiles\\drivers\\chromedriver_2.40.exe");
		driver= new ChromeDriver();
	}

	@Given("^Open browser and launch Google$")
	public void open_browser_and_launch_Google() throws Throwable {
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
	}

	@When("^Enter search key word$")
	public void enter_search_key_word() throws Throwable {
		driver.findElement(By.name("q")).sendKeys("thatstamil");
		driver.findElement(By.name("btnK")).submit();
		System.out.println("When");
	}

	@Then("^User should able to search$")
	public void user_should_able_to_search() throws Throwable {
		driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > div.r > a > h3")).click();   
		System.out.println("Then");
	}

	@After
	public void teardown() {
		driver.quit();
	}

}
