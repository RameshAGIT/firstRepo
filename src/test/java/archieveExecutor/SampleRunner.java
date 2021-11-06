package archieveExecutor;

import java.io.File;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests; 

@RunWith(Cucumber.class) 
@CucumberOptions(features= {"src/test/resources/features/Sample.feature"},
					glue={"stepDefinitions"},
					tags= {"@SmokeSuite, @RegressionSuite"},
					plugin = {"com.cucumber.listener.ExtentCucumberFormatter:test-output/cucumber-reports/report.html"},
					monochrome = true
				) 


//public class Runner {
public class SampleRunner extends AbstractTestNGCucumberTests {
	@AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("config/report.xml"));
    
   }

}
