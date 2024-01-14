package seleautomation.com.RampUpAuto;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
    @Test
    public void GetUsersApi() {
        Response response = (Response) given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        int userCount = response.jsonPath().getList("id").size();
        System.out.println( "The Number of Ids on the response is "+userCount);

        for (int i = 0; i < userCount; i++) {
            int userId = response.jsonPath().getInt("[" + i + "].id");
            String userName = response.jsonPath().getString("[" + i + "].name");
            System.out.println("User ID: " + userId + ", User Name: " + userName);

        }
    }

    @Test
    public void GetPostsApi() {
        given()
                .when()
                .get("/posts")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[16].title", equalTo("fugit voluptas sed molestias voluptatem provident"))
                .assertThat().body("id", not(empty()))
                .assertThat().body("userId", not(empty()))
                .assertThat().body("title", not(empty()))
                .assertThat().body("body", not(empty()));

    }
}