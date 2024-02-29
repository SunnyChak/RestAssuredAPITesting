package RestAssuredPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload3;

public class practice101 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		
		// place add to API
		
		String resp = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload3.placeAdd())
		.when().post("/maps/api/place/add/json")
		.then().log().all().body("scope", equalTo("APP")).statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(resp);
		String reg_id = js.getString("place_id");
		System.out.println(reg_id);
	
		
		//update place to API
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+reg_id+"\",\r\n"
				+ "\"address\":\"44 falby crt, Ajax\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//retrieve the place
		
		String output = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", reg_id).header("Content-Type","application/json")
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(output);
		String addr1 = js1.getString("address");
		System.out.println(addr1);
		
		Assert.assertEquals(addr1, "44 falby crt, Ajax");
		
		
	}

}
