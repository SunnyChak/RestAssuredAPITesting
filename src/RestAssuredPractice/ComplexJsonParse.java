package RestAssuredPractice;

//import org.testng.Assert;

import files.payload1;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JsonPath js2 = new JsonPath(payload1.coursePrice());
		int count = js2.getInt("courses.size()");
		System.out.println(count);
		
		int totalPurchaseAmnt = js2.getInt("dashboard.purchaseAmount");
		System.out.println(totalPurchaseAmnt);
		
		String firstCourseName = js2.getString("courses[0].title");
		System.out.println(firstCourseName);
		
		for(int i=0;i<count;i++)
		{
			System.out.println(js2.getString("courses["+i+"].title"));
			int priceOfCourse = js2.getInt("courses["+i+"].price");
			System.out.println(priceOfCourse);
		}

		for(int j=0;j<count;j++)
		{
			String courseTitle = js2.getString("courses["+j+"].title");
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				int copiesRPA = js2.getInt("courses["+j+"].copies");
				System.out.println(copiesRPA);
				break;
			}
		}
		int totalPrice = 0;
		
		for(int k=0;k<count;k++)
		{
			int a = js2.getInt("courses["+k+"].price");
			int b = js2.getInt("courses["+k+"].copies");
			int c = a * b;
			totalPrice = totalPrice + c;
			
		}
		System.out.println(totalPrice);
		
		//Assert.assertEquals(totalPrice, totalPurchaseAmnt);
		
		if(totalPrice == totalPurchaseAmnt)
		{
			System.out.println("Test is PASS");
		}
		else
		{
			System.out.println("Test is FAIL");
		}
		
	}

}
