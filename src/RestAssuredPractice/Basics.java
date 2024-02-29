package RestAssuredPractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// given - all inputs of the request
		//when - submit the API - resource, http method
		//then - validate the response
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		RestAssured.useRelaxedHTTPSValidation();
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
		ResponseSpecification res =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		String output = given().log().all().spec(req)
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\jeetc\\OneDrive\\Desktop\\AddPlace.json"))))
				.when().post("/maps/api/place/add/json")
				.then().log().all().spec(res).extract().response().asString();
	 
		System.out.println(output);
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
