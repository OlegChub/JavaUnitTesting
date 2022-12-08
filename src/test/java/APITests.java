import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class APITests {
    @Test
    public void test1() {
        Response response = get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void test2() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

    @Test
    public void test3() {
        given().get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[1]", equalTo(8))
                .body("data.first_name", hasItems("Michael", "Lindsay", "Tobias"))
                .log().all();
    }

    @Test
    public void testPOST() {
        JSONObject request = new JSONObject();
        request.put("name", "Lera");
        request.put("job", "logistics");

        given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString()).
                when()
                .post("https://reqres.in/api/users").
                then()
                .statusCode(201);
    }

    @Test
    public void testPUT() {
        JSONObject request = new JSONObject();
        request.put("name", "Lera");
        request.put("job", "logistics");

        given()
                .body(request.toJSONString()).
                when()
                .put("https://reqres.in/api/users/2").
                then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testPATCH() {
        JSONObject request = new JSONObject();
        request.put("job", "logistics");

        given()
                .body(request.toJSONString()).
                when()
                .patch("https://reqres.in/api/users/2").
                then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testDELETE() {
        when()
                .delete("https://reqres.in/api/users/2").
                then()
                .statusCode(204)
                .log().all();
    }

}
