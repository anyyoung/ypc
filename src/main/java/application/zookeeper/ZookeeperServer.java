package application.zookeeper;

import application.config.ServerConfig;
import application.protocol.Protocol;
import application.protocol.ProtocolSelector;
import application.zookeeper.address.NodeAddress;
import application.zookeeper.address.ServerURI;
import application.zookeeper.pojo.ZkService;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * say some thing
 *
 * @version v1.0
 * @author 17120050
 * @date 3/9/2018
 */
@Service
@EnableAutoConfiguration
public class ZookeeperServer implements InitializingBean{
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperServer.class);

    private Map<String, NodeAddress[]> serverRegists = new ConcurrentHashMap<>();
    private Protocol protocol;
    @Autowired
    private ProtocolSelector protocolSelector;

    @Autowired
    private ServerConfig serverConfig;

    public void init(){
        List<ZkService> registesConfig = serverConfig.getServiceRegist();
        if (registesConfig!=null && registesConfig.size() > 0){
            for (ZkService zkService : registesConfig){
                //registServer(zkService);
            }
        }
    }

    public void createNode(ZooKeeper zK, ServerURI serverURI , Map<String, Object> map) {
        try {

           /* String path = zK.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            LOGGER.debug("create zookeeper node ({} => {})",path , data);*/
        }catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        protocol = protocolSelector.getDecoder();

    }

}
