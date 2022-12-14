package api.service;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class SlackService {
    private static final SlackConfig config = ConfigFactory.create(SlackConfig.class, System.getProperties());

    public static void postNotification(String message) {
        JSONObject request = new JSONObject();
        request.put("text", message);
        String postUrl = String.format("https://hooks.slack.com/services/%s", config.token());
        given()
                .header("Content-type", "application/json")
                .body(request.toJSONString())
                .when()
                .post(postUrl)
                .then()
                .log().all();
    }
}