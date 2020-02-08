package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MakeMyTripStepDefinition {

	WebDriver driver;

	@Given("^Start chrome browser and open MakeMyTrip application$")
	public void SetUp()  {
		System.setProperty("webdriver.chrome.driver","R:\\Ramesh\\SupportingFiles\\drivers\\chromedriver_2.40.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		System.out.println("Launch MakeMyTrip");
		driver.get("https://www.makemytrip.com/"); 
	}

	@When("^Enter \"([^\"]*)\" and \"([^\"]*)\" airports and click search$")
	public void enter_airport_details_and_click_search(String from, String to) throws InterruptedException  {
		//Select From
		driver.findElement(By.cssSelector("#fromCity")).click();
		driver.findElement(By.cssSelector("#root > div > div.minContainer > div > div > div.fsw.widgetOpen > div.fsw_inner > div.fsw_inputBox.searchCity.inactiveWidget.activeWidget > div:nth-child(2) > div > div > div > input")).sendKeys(from);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#react-autowhatever-1-section-0-item-0 > div > div.calc60 > p.font14.appendBottom5.blackText")).click();
		//Select To
		//driver.findElement(By.cssSelector("#toCity")).click();
		driver.findElement(By.cssSelector("#root > div > div.minContainer > div > div > div.fsw.widgetOpen > div.fsw_inner > div.fsw_inputBox.searchToCity.inactiveWidget.activeWidget > div:nth-child(2) > div > div > div > input")).sendKeys(to);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#react-autowhatever-1-section-0-item-0 > div > div.calc60 > p.font14.appendBottom5.blackText")).click();
		//click on the form to avoid date picker
		driver.findElement(By.cssSelector("#root > div > div.minContainer > div > div > div.fsw.widgetOpen > div.fsw_inner > div.fsw_inputBox.dates.inactiveWidget.activeWidget > div.hsBackDrop")).click();
		//Click Search
		driver.findElement(By.cssSelector("#root > div > div.minContainer > div > div > div.fsw > p > a")).click();
		Thread.sleep(5000);
	}


	@Then("^Validate search details \"([^\"]*)\", \"([^\"]*)\" is displayed$")
	public void validate_SearchFlight_Details(String frm, String to) {
		String actDep=driver.findElement(By.cssSelector("#departure_group > p > span")).getText();
		String actArr=driver.findElement(By.cssSelector("#arrival_group > p > span")).getText();
		String expDep="Departure From "+frm;
		String expArr="Arrival at "+to;
		System.out.println("depature: "+actDep);
		System.out.println("arrival: "+actArr);
		Assert.assertEquals(actDep,expDep);
		Assert.assertEquals(actArr,expArr);
		//driver.close();
	}

	//@After 
	public void tearDown() { 
		driver.quit(); 
	}



}
