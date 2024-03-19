package config;

import org.junit.BeforeClass;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class TestConfig {
  @BeforeClass
  public static void setup() {
     
    RestAssured.baseURI = "https://dummyjson.com/"; // This is the base URI
      
    RestAssured.requestSpecification = new RequestSpecBuilder()
        .setBaseUri("https://dummyjson.com/")
        .setContentType("application/json")
        .addHeader("Accept", "application/json")
        .build();
    
    RestAssured.responseSpecification = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .build();
   
  }
}
