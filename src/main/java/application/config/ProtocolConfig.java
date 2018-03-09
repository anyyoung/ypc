package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
@Component
@ConfigurationProperties(prefix = "rpc" , ignoreInvalidFields = true)
@PropertySource("classpath:/application.yml")
public class ProtocolConfig {
    @Value("${protocol:protobuf}")
    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
