package resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities {

	static RequestSpecification reqSpecBldr;				//Variable is static, so that it will NOT initiate every execution
	public RequestSpecification requestSpec() throws IOException {
		if(reqSpecBldr==null) {
			PrintStream log=new PrintStream(new FileOutputStream("logs.txt"));
			
			//Request Spec Builder
			reqSpecBldr=new RequestSpecBuilder()
					//.setBaseUri("https://rahulshettyacademy.com")
					.setBaseUri(getPropertyValue("baseURL"))			//Reading this value from global.properties file
					.addQueryParam("key", "qaclick123")
					.addHeader("Content-Type", "application/json")
					.addFilter(RequestLoggingFilter.logRequestTo(log))		//Log your request to a file
					.addFilter(ResponseLoggingFilter.logResponseTo(log))	//Log your response to a file	
					//.setBody(PayLoad.AddPlace())
					.build();
			return reqSpecBldr;
		}
		return reqSpecBldr;
	}

	public ResponseSpecification responseSpec() {
		ResponseSpecification respSpecBldr;
		//Response Spec Builder
		respSpecBldr=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.expectHeader("Server", "Apache/2.4.18 (Ubuntu)")
				.build();
		return respSpecBldr;
	}

	public String getPropertyValue(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("R:\\Ramesh\\Automation\\eclipse-workspace\\CucumberSeleniumArt\\src\\test\\java\\resources\\Global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response resp, String key) {
		String respStr=resp.asString();
		JsonPath jp=new JsonPath(respStr);
		String value=jp.get(key).toString();
		return value;
	}
}
