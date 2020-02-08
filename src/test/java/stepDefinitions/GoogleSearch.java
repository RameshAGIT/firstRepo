package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleSearch {
	public WebDriver driver;

	@Given("^Open browser and launch Google$")
	public void open_browser_and_launch_Google() throws Throwable {
		System.setProperty("webdriver.chrome.driver","R:\\Ramesh\\SupportingFiles\\drivers\\chromedriver_2.40.exe");
		driver= new ChromeDriver();
		System.out.println("Launch Google");
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
	}

	@When("^Enter \"([^\"]*)\" to search$")
	public void enter_search_key_word(String searchTxt) throws Throwable {
		driver.findElement(By.name("q")).sendKeys(searchTxt);
		driver.findElement(By.name("btnK")).submit();
	}

	@Then("^User should able to search$")
	public void user_should_able_to_search() throws Throwable {
		driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > div.r > a > h3")).click();   
	}

	//@After
	public void teardown() {
		driver.quit();
	}

}
