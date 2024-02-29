package RestAssuredPractice;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.locTypes;

public class SerialDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("44, side layout, Ajax 905");
		ap.setLanguage("Spanish");
		ap.setName("SoumojeetC");
		ap.setPhone_number("+1 4379827991");
		ap.setWebsite("https://www.linkedin.com/feed/");
		
		List<String> myList = new ArrayList<String>();
		myList.add("rolex");
		myList.add("gucci");
		
		ap.setTypes(myList);
		
		locTypes lt = new locTypes();
		lt.setLat(-39.383494);
		lt.setLng(30.897023);
		
		ap.setLocation(lt);
		
		
		
		
		String resp1 = given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json").body(ap)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(resp1);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
