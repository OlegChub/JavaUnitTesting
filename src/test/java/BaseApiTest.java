import api.service.SlackService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    @BeforeAll
    public static void start() {
        SlackService.postNotification("API tests are started");
    }

    @AfterAll
    public static void finish() {
        SlackService.postNotification("API tests are finished");
    }
}
