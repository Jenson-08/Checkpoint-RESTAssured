import org.junit.Test;
import config.TestConfig;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import java.io.InputStream;
import static junit.runner.Version.id;


import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;



import java.io.InputStream;
import org.json.simple.JSONObject;
/**
 * Unit test for simple App.
 */
public class AppTest extends TestConfig {
    private static final String PRODUCTS_ENDPOINT = "products/{productId}";
    private static final String QUOTE_ENDPOINT = "quotes/{id}";
    private static final String IDPRODUCTS_ENDPOINT = "15";
     private static final String SCHEMA_PATH = "/main/resources/productSchema.json"; 
    //private static final String ADDQUOTES_ENDPOINT = "quotes/add";
    /**
     * Rigorous Test :-)
     */
    @Test
    public void get() {
        given()
            .contentType("application/json")
            .header("Accept", "application/json")
            .pathParam("productId", "1")
            
        .when()
            .get(PRODUCTS_ENDPOINT)
            //.get("https://dummyjson.com/quotes") // If we didn't have a Base/Config class
            // we would make the request like this
        .then()
            .log().all()
            .statusCode(200) // Assert status code is 200
            .time(lessThan(5000L)) // Assert response time is less than 5000 milliseconds
            .header("Content-Type", containsString("application/json")) // Assert specific header exists
            .body("id", equalTo(1)); // Assert specific data field
            //.body("author", equalTo("Rumi")); // Assert specific data field
            
            
    }
    
    @Test
    public void delete() {
         
        given()
            .contentType("application/json")
            .header("Accept", "application/json")
            .pathParam("productId", "1")
        .when()
            .delete(PRODUCTS_ENDPOINT)
            //.get("https://dummyjson.com/quotes") // If we didn't have a Base/Config class
            // we would make the request like this
        .then()
            //
            //.assertThat()
            .statusCode(200) // Assert status code is 200
            .time(lessThan(5000L)) // Assert response time is less than 5000 milliseconds
            .header("Content-Type", containsString("application/json"))
            .log().all(); // Assert specific header exists
            //.body("id", equalTo(1)) // Assert specific data field
            //.body("author", equalTo("Rumi")); // Assert specific data field
              
    }
    
    @Test
    public void update() {
       
        // Crear un objeto JSON con los datos actualizados del producto
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Iphone 14");
        requestBody.put("price", 900);

        given()
            .contentType("application/json")
            .header("Accept", "application/json")
            .pathParam("productId", "1") // Pasar el ID del producto como parámetro de la ruta
            .body(requestBody.toString()) // Convertir el objeto JSON a una cadena
        .when()
            .put(PRODUCTS_ENDPOINT) // Usar el endpoint para actualizar el producto
        .then()
            .log().all()
            .statusCode(200) // Assert status code is 200
            .time(lessThan(5000L)) // Assert response time is less than 5000 milliseconds
            .header("Content-Type", containsString("application/json"))
            .body("id", equalTo(1)); // Assert specific data field; 

              
    }
    
    @Test
    public void getSchema() {
        given()
            .contentType("application/json")
            .header("Accept", "application/json")
            .pathParam("productId", "1")
        .when()
            .get(PRODUCTS_ENDPOINT)
        .then()
            .assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("productSchema.json"));
    
    }

    
    
}
