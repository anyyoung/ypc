package application.rebalance;

import application.exception.YpcConfigException;
import application.config.RebalanceConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableConfigurationProperties
public class RebanlanceSelector implements InitializingBean{
    @Autowired
    private RebalanceConfig rebalanceConfig;

    private static Map<String , RebalanceEnum> rebalanceMap = new HashMap<>();
    static {
        rebalanceMap.put(Rebalance.MUR_MUR_HASH, RebalanceEnum.MUR_MUR_HASH_REBALANCE);
    }

    /**
     * 获取rebalance策略
     * @return
     */
    public Rebalance getRebanlance(){
        String rebalance = rebalanceConfig.getRebalance();
        RebalanceEnum rebalanceEnum = rebalanceMap.get(rebalance);
        if (rebalanceEnum==null){
            throw new YpcConfigException(
                    "The configured serialization protocol is not correct ！:" + rebalance);
        }
        //默认的的rebalance策略
        return rebalanceEnum.getRebalance();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("选择的rebanlance是： "+getRebanlance());
    }
}
