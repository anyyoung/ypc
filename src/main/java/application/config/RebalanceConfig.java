package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rpc" ,ignoreInvalidFields = true)
@PropertySource("classpath:/application.yml")
public class RebalanceConfig {
    @Value("${rebalance:MurMurHash}")
    private String rebalance ;

    public String getRebalance() {
        return rebalance;
    }

    public void setRebalance(String rebalance) {
        this.rebalance = rebalance;
    }
}
