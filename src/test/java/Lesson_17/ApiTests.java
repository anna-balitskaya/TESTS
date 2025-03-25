package Lesson_17;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    void GetMethod() {

        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("https://postman-echo.com/get");
        response.then().statusCode(200);
        response.then()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    void PostMethodText() {

        String requestBody = "This is expected to be sent back as part of response body.";
        Response response = given()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .post("https://postman-echo.com/post");
        response.then().statusCode(200);
        response.then()
                .body("data", equalTo(requestBody));
    }

    @Test
    void PostMethodFormData() {

        Response response = given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("https://postman-echo.com/post");

        System.out.println("Статускод: " + response.getStatusCode());
        System.out.println("Тело ответа: " + response.getBody().asString());

        response.then().statusCode(200);
        response.then()
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"));
    }

    @Test
    void PutMethod(){
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .put("https://postman-echo.com/put");

        response.then().statusCode(200);

        response.then()
                .body("data", equalTo(requestBody));
    }

    @Test
    void PatchMetchod(){
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .patch("https://postman-echo.com/patch");

        response.then().statusCode(200);

        response.then()
                .body("data", equalTo(requestBody));
    }

    @Test
    void DeleteMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .delete("https://postman-echo.com/delete");

        response.then().statusCode(200);

        response.then()
                .body("data", equalTo(requestBody));
    }
}
