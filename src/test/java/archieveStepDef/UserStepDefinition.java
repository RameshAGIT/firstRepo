package archieveStepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserStepDefinition {

	WebDriver driver;

	@Given("^Start chrome browser and open the application$")
	public void SetUp()  {
		System.setProperty("webdriver.chrome.driver","R:\\Ramesh\\SupportingFiles\\drivers\\chromedriver_2.40.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		System.out.println("Launch User");
		driver.get("http://apps.qa2qe.cognizant.e-box.co.in/Handling_Reg_Expression"); 
	}

	@When("^Enter \"([^\"]*)\" and click search$")
	public void enter_and_click_search(String userName)  {
		driver.findElement(By.id("userId")).sendKeys(userName);
		driver.findElement(By.id("track")).click();
	}


	@Then("^Validate user name \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" is displayed$")
	public void validate_user_name_is_displayed(String expUsr, String expShpId, String expPhone, String expEmail) {
		String arr[];
		String temp[];
		String actTxt=driver.findElement(By.xpath("//*[@id=\"result\"]")).getText();
		System.out.println("usr: \n"+actTxt);
		
		arr=actTxt.split("\n");
		
		temp=arr[0].split(":");
		String actUsr=temp[1].trim();
		System.out.println("UserName: "+actUsr);
		Assert.assertEquals(expUsr,actUsr); 
		
		temp=arr[1].split(":");
		String actShpId=temp[1].trim();
		System.out.println("ShipmentId: "+actShpId);
		Assert.assertEquals(expShpId,actShpId);
		
		temp=arr[2].split(":");
		String actPhone=temp[1].trim();
		System.out.println("PhoneNumber: "+actPhone);
		Assert.assertEquals(expPhone,actPhone);
		
		temp=arr[3].split(":");
		String actEmail=temp[1].trim();
		System.out.println("actEmail:"+actEmail);
		System.out.println("expEmail:"+expEmail.trim());
		Assert.assertEquals(expEmail.trim(),actEmail);
		//driver.close();
	}

	//@After 
	public void tearDown() { 
		driver.quit(); 
	}



}
