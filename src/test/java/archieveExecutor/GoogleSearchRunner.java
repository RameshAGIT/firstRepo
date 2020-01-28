package archieveExecutor;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", 
					glue="stepDefinitions",
					plugin = { "pretty", "html:target/cucumber-reports" },
					 monochrome = true)

public class GoogleSearchRunner extends AbstractTestNGCucumberTests{

}

