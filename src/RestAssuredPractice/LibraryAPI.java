package RestAssuredPractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload3;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryAPI {

	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		//add a book
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String resp = given().log().all().header("Content-Type", "application/json").body(payload3.bookAdd(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(resp);
		String id = js.getString("ID");
		System.out.println(id);
		
		//delete the book	
		
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"sshhe", "7843"}, {"agsfl", "2139"}, {"podi", "9012"}};

	}
	
	
	
	
	
	
}
	
