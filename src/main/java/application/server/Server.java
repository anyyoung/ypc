package application.server;

import application.config.ServerConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date
 */
@Service
@EnableConfigurationProperties
public class Server implements InitializingBean{
    @Autowired
    private ServerConfig serverConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("serverConfig is : "+serverConfig.getYpcServerConf());
        System.out.println("zk config is : "+serverConfig.getZkServerConf());
        System.out.println("serviceRegist is : "+serverConfig.getServiceRegist());
    }
}
