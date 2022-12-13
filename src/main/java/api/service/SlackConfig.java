package api.service;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:slack.properties"
})
public interface SlackConfig extends Config {
    String token();
}
