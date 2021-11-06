package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResourcesEnum;
import resources.PayLoad;
import resources.Utilities;
import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

public class PlaceAPI extends Utilities{
	RequestSpecification reqSpecBldr;
	ResponseSpecification respSpecBldr;
	RequestSpecification req;
	Response resp;
	static String placeId;
	@Before
	public void setup() {
		//Instead of getting it from @Before, I am trying to get request & response @ run time through Utilities.java using inheritance

		/*	//Request Spec Builder
		reqSpecBldr=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.addHeader("Content-Type", "application/json")
				.setBody(PayLoad.AddPlace())
				.build();

		//Response Spec Builder
		respSpecBldr=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.expectHeader("Server", "Apache/2.4.18 (Ubuntu)")
				.build();*/
	}

	@Given("^Add Place Payload$")
	public void add_Place_Payload() throws Throwable {
		/*
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String placeId=given().log().all()					//This log().all() only for input
	.queryParam("key", "qaclick123")
	.header("Content-Type", "application/json")	
	.body(PayLoad.AddPlace())							//PayLoad for add a new address. Body content should be in String
	.when().post("maps/api/place/add/json")				//Resource
	.then().log().all()									//This log().all() only for output
	.statusCode(200)					
	.body(containsString("OK"))							//containsString validation comes from hamcrest package
	.body("scope", equalTo("APP"))						//equalTo validation comes from hamcrest package
	.header("Server", "Apache/2.4.18 (Ubuntu)")
	.extract().path("place_id");						//Extract place id from the response and store it in variable
	System.out.println("Place ID: "+placeId);
		 */
		//Request is coming from Utilities.java through inheritance(extends)
		req=given().log().all().spec(requestSpec()).body(PayLoad.AddPlace());
	}

	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Payload_with(String name, String address, String language) throws Throwable {
		//Request is coming from Utilities.java through inheritance(extends)
		req=given().log().all().spec(requestSpec()).body(PayLoad.AddPlace(name,address,language));

	}

	@When("^User calls \"([^\"]*)\" with POST request$")
	public void user_calls_with_POST_request(String resource) throws Throwable {
		//Response is coming from Utilities.java through inheritance(extends)
		resp=req.when().post(resource)
				.then().log().all().spec(responseSpec()).extract().response();
	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" request$")
	public void user_calls_with_request(String resourceKey, String httpMethod) throws Throwable {
		//Reading resource value from enum class
		//Constructor will be called with value of resource which you pass
		APIResourcesEnum apiResource=APIResourcesEnum.valueOf(resourceKey);	
		String resourceVal=apiResource.getResource();
		
		//Response is coming from Utilities.java through inheritance(extends)
		if(httpMethod.equalsIgnoreCase("POST")) {
			resp=req.when().post(resourceVal).then().log().all().spec(responseSpec()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("GET")) {
			resp=req.when().get(resourceVal).then().log().all().spec(responseSpec()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("DELETE")) {
			resp=req.when().delete(resourceVal).then().log().all().spec(responseSpec()).extract().response();
		}
	}

	@Then("^API call got success with status code (\\d+)$")
	public void api_call_got_success_with_status_code(int statusCode) throws Throwable {
		assertEquals(statusCode,resp.getStatusCode());
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String key, String val) throws Throwable {
		//Validate response using default methods
		placeId=resp.getBody().path("place_id");
		assertEquals("Apache/2.4.18 (Ubuntu)",resp.getHeader("Server"));
		assertEquals(val,resp.getBody().path(key));
		assertTrue(resp.getBody().asString().contains("OK"));
		System.out.println("PlaceId : "+placeId);
	}

	@And("^Verify \"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void Verify_in_response_body_is(String key, String expVal) throws Throwable {
		//Validate response using JsonPath() methods
		String actSuccessMsg=getJsonPath(resp,key);
		assertEquals(actSuccessMsg,expVal);
	}
	
	@Then("^Verify placeId created matches to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_placeId_created_matches_to_using(String name, String getPlaceResource) throws Throwable {
		req=given().spec(requestSpec()).queryParam("place_id", placeId);
		user_calls_with_request(getPlaceResource,"GET");
		String actName=getJsonPath(resp,"name");
		assertEquals(actName,name);
	}
	
	@Given("^Delete place payload$")
	public void delete_Place_Payload() throws Throwable {
		req=given().spec(requestSpec()).body(PayLoad.DeletePlace(placeId));
	}
}
