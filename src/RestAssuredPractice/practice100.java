package RestAssuredPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload2;

public class practice100 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Add a region
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(payload2.addRegion())
		.when().post("/maps/api/place/add/json")
		.then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");
		
		System.out.println(placeID);
		
		//update a region
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\"70 winter walk, CA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get the place
		
		String placeResp = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		JsonPath js1 = new JsonPath(placeResp);
		String actualAddr = js1.get("address");
		
		String expAddr = "70 winter walk, CA";
		
		Assert.assertEquals(actualAddr, expAddr);
		
		
	}

}
