import api.service.SlackService;
import com.epam.reportportal.junit5.ReportPortalExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ReportPortalExtension.class)
public class BaseApiTest {
    private static final Logger LOGGER = LogManager.getLogger(BaseApiTest.class);

    @BeforeAll
    public static void start() {
        LOGGER.info("API tests are started");
        SlackService.postNotification("API tests are started");
    }

    @AfterAll
    public static void finish() {
        LOGGER.info("API tests are finished");
        SlackService.postNotification("API tests are finished");
    }
}