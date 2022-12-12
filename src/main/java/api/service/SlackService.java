package api.service;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class SlackService {
    public static void postNotification(String message) {
        JSONObject request = new JSONObject();
        request.put("text", message);
        given()
                .header("Content-type", "application/json")
                .body(request.toJSONString()).
                when()
                .post("https://hooks.slack.com/services/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")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
