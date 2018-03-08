package application.rebalance;

import application.config.RebalanceConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import static application.rebalance.Rebalance.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties
public class RebanlanceSelector implements InitializingBean{
    @Autowired
    private RebalanceConfig rebalanceConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        switch (rebalanceConfig.getRebalance()){
            case MUR_MUR_HASH:
                System.out.println("初始化murmurHash");
                break;
                default:
                    System.out.println("没有配置此项，默认配置murmurHash");
        }
    }
}
