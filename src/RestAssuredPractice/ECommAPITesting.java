package RestAssuredPractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;
import pojo.loginDetails;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ECommAPITesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//login to API
		
		RestAssured.useRelaxedHTTPSValidation();
		
		RequestSpecification setLoginBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		loginDetails LoginDetails = new loginDetails();
		LoginDetails.setUserEmail("jena123@gmail.com");
		LoginDetails.setUserPassword("Sunny_1987");
		
		RequestSpecification loginRequest = given().log().all().spec(setLoginBaseReq).body(LoginDetails);
		LoginResponse loginResponse = loginRequest.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
		
		
		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();
		
		//create product
		
		RequestSpecification createProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authentication", token).build();
		
		RequestSpecification createProductReq = given().log().all().spec(createProductBaseReq).param("productName", "Acer Laptop").param("productAddedBy", userId)
		.param("productCategory", "Electronics").param("productSubCategory", "Laptop").param("productPrice", "10000")
		.param("productDescription", "Aspire 3").param("productFor", "All").multiPart("productImage",new File("C:\\Users\\jeetc\\OneDrive\\Desktop\\AcerLaptop.jpg"));
		
		String createProdResponse = createProductReq.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(createProdResponse);
		String productID = js1.get("productId");
		
		// create order
		
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authentication", token)
		.setContentType(ContentType.JSON).build();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productID);
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(orderDetail);
		
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		
		
		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
		
		String createOrderResponse = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		
		System.out.println(createOrderResponse);
		
		
		//delete order
		
		RequestSpecification deleteProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authentication", token).build();
		
		RequestSpecification deleteProductReq = given().log().all().spec(deleteProductBaseReq).pathParam("productId", productID);
		
		String deleteProductResponse = deleteProductReq.when().delete("/api/ecom/product/delete-product/{productID}").then().log().all().extract().response().asString();
		
		JsonPath js3 = new JsonPath(deleteProductResponse);
		String deleteMsg = js3.get("message");
		
		
		Assert.assertEquals("Product Deleted Successfully", deleteMsg);
		
		
		
	}

}
