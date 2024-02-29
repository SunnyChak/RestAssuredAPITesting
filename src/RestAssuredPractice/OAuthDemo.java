package RestAssuredPractice;

import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourseDetails;
import pojo.WebAutomation;

public class OAuthDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//get the access token from Authorization server
		
		
		String resp1 = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(resp1);
		String at = js.getString("access_token");
		System.out.println(at);
		
		//get course details
		
		/* String resp2 = given().queryParam("access_token", at)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(resp2);
		String li = js1.get("linkedIn").toString();
		
		System.out.println(li);*/
		
		//get the course details and linkedIn  by parsing via POJO classes
		
		GetCourseDetails gcd = given().queryParam("access_token", at)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourseDetails.class);
	
		System.out.println(gcd.getLinkedIn());
		System.out.println(gcd.getInstructor());
		System.out.println(gcd.getCourses().getApi().get(1).getPrice());
		
		List<Api> apiCourses = gcd.getCourses().getApi();
		
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
						System.out.println(apiCourses.get(i).getPrice());
					}
		}
		
		//assignment print all course titles of web automation
		
		List<WebAutomation> webAutoCourses = gcd.getCourses().getWebAutomation();
		
		for(int j = 0;j<webAutoCourses.size();j++)
		{
			System.out.println(webAutoCourses.get(j).getCourseTitle());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
