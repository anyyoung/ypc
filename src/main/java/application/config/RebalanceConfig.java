package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rpc")
@PropertySource("classpath:/application.yml")
public class RebalanceConfig {
    private String rebalance ="";

    public String getRebalance() {
        System.out.println("获取值为："+rebalance);
        return rebalance;
    }

    public void setRebalance(String rebalance) {
        this.rebalance = rebalance;
    }
}
