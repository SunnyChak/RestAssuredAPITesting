package files;

public class payload3 {

	
	public static String placeAdd()
	{
		String loc = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -18.383494,\r\n"
				+ "    \"lng\": 13.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 99,\r\n"
				+ "  \"name\": \"Stack house\",\r\n"
				+ "  \"phone_number\": \"(+1) 437 893 3937\",\r\n"
				+ "  \"address\": \"550, Jarvis st, ontario 516\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
		return loc;
		
	}
	
	public static String bookAdd(String isbn, String aisle)
	{
		String book1 = "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Tom Doe\"\r\n"
				+ "}";
		return book1;
	}
	
	
	
	
	
	
	
	
	
	
}
